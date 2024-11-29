package com.liguo.demo.core.mappercheck;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 自动扫描执行mapper接口中sql
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/23 16:03
 * @since 0.0.1
 */
@Slf4j
public class MapperAutoTest extends BaseTest {

    @Resource
    private ApplicationContext context;
    @Resource
    private DataSourceTransactionManager txManager;


    // 编程式事务的定义
    private static final DefaultTransactionDefinition def = new DefaultTransactionDefinition();

    static {
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    }

    /**
     * 执行所有 dao 的所有方法
     */
    public Collector invokeAllDaos() {
        return invokeAllDaos(null);
    }

    /**
     * 执行所有 dao 的所有方法
     *
     * @param excludedMethodRegex 不执行的方法名的正则表达式. 如 ".*(IDayChangeDao.dayChange|IDao.method2)"
     */
    public Collector invokeAllDaos(String excludedMethodRegex) {
        Collector collector = new Collector();
        for (Object dao : scanAllDao()) {
            invokeSingleDao(dao, collector, excludedMethodRegex);
        }
        return collector;
    }

    /**
     * 获取所有Mapper实例对象
     *
     * @return
     */
    protected List<Object> scanAllDao() {
        List<Object> list = new ArrayList<Object>();
        for (String beanName : context.getBeanDefinitionNames()) {

            if (!beanName.endsWith("Mapper")) {
                continue;
            }
            log.info("beanName：{}", beanName);
            Object dao = context.getBean(beanName);
            if (dao.getClass().getInterfaces().length == 0) {
                continue;
            }
            if (!dao.getClass().getInterfaces()[0].getName().startsWith("com.liguo.demo.core.dao.")) {
                continue;
            }

            list.add(dao);
        }
        return list;
    }

    /*
     * 执行一个 dao 的所有方法
     */
    protected void invokeSingleDao(Object dao, Collector collector, String regex) {
        Class<?>[] interfaces = dao.getClass().getInterfaces();
        if (interfaces.length > 0) {
            Method[] methods = interfaces[0].getDeclaredMethods();
            for (Method method : methods) {
                if (StringUtils.isEmpty(regex) || !(interfaces[0].getName() + "." + method.getName()).matches(regex)) {
                    /*if (!"queryAmountTotal".equals(method.getName())) {
                        continue;
                    }*/
                    invokeSingleMethod(dao, method, interfaces[0], collector);
                }
            }
        }
    }

    /*
     * 执行 dao 中的指定方法. 执行结束, 回滚事务
     */
    protected void invokeSingleMethod(Object dao, Method method, Class<?> daoInterface, Collector collector) {
        CollectorItem item = new CollectorItem();
        item.setDao(daoInterface.getName());
        item.setMethod(method.getName());
        long startTime = System.currentTimeMillis();

        TransactionStatus status = txManager.getTransaction(def);
        try {
            log.info("start to invoke {}.{}", daoInterface.getName(), method.getName());
            method.invoke(dao, instantiateParameters(method));

            item.setSuccess(true);
        } catch (Exception e) {
            if (e.getCause() instanceof DuplicateKeyException) {
                // 主键重复也认为 sql 无误
                log.warn(e.getCause().getMessage());
                item.setSuccess(true);
            } else {
                log.error("execute dao error. dao: " + dao.getClass().getInterfaces()[0] + ", method: " + method.getName() + ", e: "
                        + e.getCause().getMessage());

                item.setError(e.getCause());
            }
        } finally {
            txManager.rollback(status);

            item.setElapse(System.currentTimeMillis() - startTime);
            collector.getItems().add(item);
        }
    }

    /**
     * 实例化方法中的参数
     *
     * @param method 方法
     * @return 实例化后参数对象
     */
    protected Object[] instantiateParameters(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> genericType = getGenericType(method);
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (Collection.class.isAssignableFrom(parameterTypes[i])) {
                parameters[i] = instantiateCollection(parameterTypes[i], genericType);
            } else {
                parameters[i] = instantiate(parameterTypes[i]);
            }
        }

        return parameters;
    }

    /**
     * 获取方法参数中的泛型类型. TODO: 暂只支持一个泛型
     *
     * @param method 方法
     * @return 参数泛型类型
     */
    protected Class<?> getGenericType(Method method) {
        for (Type genericParameterType : method.getGenericParameterTypes()) {
            if ((genericParameterType instanceof ParameterizedType)) {
                for (Type parameterArgType : ((ParameterizedType) genericParameterType).getActualTypeArguments()) {
                    return (Class<?>) parameterArgType;
                }
            }
        }
        return null;
    }

    /*
     * 实例化集合类
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected Object instantiateCollection(Class<?> type, Class<?> genericType) {
        try {
            // Set
            if (Set.class.isAssignableFrom(type)) {
                Set set = new HashSet();
                set.add(instantiate(genericType));
                return set;
            }
            // List
            if (List.class.isAssignableFrom(type)) {
                List list = new ArrayList();
                list.add(instantiate(genericType));
                return list;
            }
            // 其他
            log.warn("unsupported type: " + type);
            return null;
        } catch (Exception e) {
            log.warn("can not instantiate, type: " + type, e.getMessage());
            return null;
        }

    }

    @Test
    public void checkAllMapperSql() {
        // 1 获取所有mapper实例
        Collector collector = invokeAllDaos();
        // 2 获取mapper所有方法
        collector.print();
    }
}

