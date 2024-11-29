package com.liguo.demo.consume.config.sharding;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 查找分片表的通用工具类
 *
 * @author duyubo
 * @time 2020年9月12日 上午10:53:20
 */


@Slf4j
public class CommonShardingUtil {
/**
     * 复合分片路由方法
     *
     * @param colPriorityTable
     * @param availableTargetNames
     * @param shardingValue
     * @return
     * @author duyubo
     * @time 2020年9月12日 上午10:51:44
     */
    public static Collection<String> complexRoute(String[] colPriorityTable,
                                                  Collection<String> availableTargetNames, ComplexKeysShardingValue<String> shardingValue) {
        Collection<String> targetTables = new HashSet<>();
        String logicTagle = shardingValue.getLogicTableName();
        String columnName = null;
        String columnValue = null;
        String shardValue = null;
        Map<String, Collection<String>> columnValMap = shardingValue.getColumnNameAndShardingValuesMap();
        for (String col : colPriorityTable) {
            Collection<String> colVals = columnValMap.get(col);
            if (CollectionUtils.isNotEmpty(colVals)) {
                columnName = col;
                Object obj = colVals.iterator().next();
                if (obj instanceof Long){
                    columnValue =  String.valueOf((Long)obj);
                    break;
                }
                columnValue = String.valueOf(obj);
                break;
            }
        }

        // 取最后4位
        shardValue = getLastPart(columnValue, 4);
        if (StringUtils.isBlank(shardValue)) {
            throw new UnsupportedOperationException(logicTagle + "表获取不到分片值！");
        }

        Integer shardValInt = Integer.parseInt(shardValue);
        // 取模,确定分片位置
        int shardPosition = shardValInt % availableTargetNames.size();
        String targetTable = logicTagle + "_" + shardPosition;
        if (!availableTargetNames.contains(targetTable)) {
            throw new UnsupportedOperationException("表分配错误,请检查表名:" + targetTable);
        }
        log.info("逻辑表:{},分片列:{},值为:{},分到的表为:{}", logicTagle, columnName, columnValue, targetTable);
        targetTables.add(targetTable);
        return targetTables;
    }

    public static String getLastPart(String value, int lastLength) {
        value = StringUtils.leftPad(value, lastLength, "0");
        String lastPart = value.substring(value.length() - lastLength);
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean isDig = pattern.matcher(lastPart).matches();
        if (!isDig || lastPart.indexOf("-") >= 0) {
            String lastPartHash = String.valueOf(lastPart.hashCode());
            lastPart = lastPartHash.substring(lastPartHash.length() - lastLength);
        }

        return lastPart;
    }

}


