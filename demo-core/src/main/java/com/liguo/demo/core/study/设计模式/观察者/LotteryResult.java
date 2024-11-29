package com.liguo.demo.core.study.设计模式.观察者;

import lombok.Data;

import java.util.Date;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/26 23:21
 * @since 0.0.1
 */
@Data
public class LotteryResult {
    private String uId;
    private String lottery;
    private Date date;
    private String msg;

    public LotteryResult(String uId, String lottery, Date date, String msg) {
        this.uId = uId;
        this.lottery = lottery;
        this.date = date;
        this.msg = msg;
    }
}
