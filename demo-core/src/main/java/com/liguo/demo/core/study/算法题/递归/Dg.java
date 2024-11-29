package com.liguo.demo.core.study.算法题.递归;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/26 15:36
 * @since 0.0.1
 */
@Slf4j
public class Dg {
    public static void main(String[] args) {
        ThreadUtil.sleep(100000);
        log.info("n:{}", recur(30000));
    }

    /* 递归 */
    public static int recur(int n) {
        // 终止条件
        if (n == 1)
            return 1;
        // 递：递归调用
        int res = recur(n - 1);
        // 归：返回结果
        return n + res;
    }
}
