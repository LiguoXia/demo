package com.liguo.demo.core.study.java基础;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 13:39
 * @since 0.0.1
 */
@Slf4j
public class 重写Eq {


    public static class ObjEq {
        public String str;
        public int i;

        @Override
        public boolean equals(Object obj) {

            // 1 使用 == 检查参数是否为这个对象的引用。
            if (obj == this) return true;
            // 2 使用instanceof 检查参数是否为正确类型。
            if (!(obj instanceof ObjEq)) return false;

            // 3 把参数转化成正确类型
            ObjEq objEq = (ObjEq) obj;

            // 4 对于该类中的关键字段，检查参数中的字段是否与该对象中对应的字段匹配。
            return Objects.equals(str, objEq.str) &&
                    Objects.equals(i, objEq.i);
        }

        /**
         * 重写hashCode
         *
         * @return
         */
        @Override
        public int hashCode() {
            return Objects.hash(str, i);
        }

        public static void main(String[] args) {
            ObjEq objEq1 = new ObjEq();
            objEq1.str = "hello";
            objEq1.i = 1;

            ObjEq objEq2 = new ObjEq();
            objEq2.str = "hello";
            objEq2.i = 1;

            log.info("eq:{}", objEq1.equals(objEq2));
        }
    }
}
