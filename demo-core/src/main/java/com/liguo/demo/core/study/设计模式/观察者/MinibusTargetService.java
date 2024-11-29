package com.liguo.demo.core.study.设计模式.观察者;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/26 23:30
 * @since 0.0.1
 */
public class MinibusTargetService {
    /**
     * 模拟摇号，但不不是摇号算法
     *
     * @param uId ⽤用户编号
     * @return 结果
     */
    public String lottery(String uId) {
        return Math.abs(uId.hashCode()) % 2 == 0 ? "恭喜你，编 码 ".concat(uId).concat(" 在本次摇号中签 ") : " 很遗憾，编 码 ".concat(uId).concat(" 在本次摇号未中签或摇号资格已过期 ");
    }
}
