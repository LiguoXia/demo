package com.liguo.demo.core.service.impl;

import com.liguo.demo.core.enums.TrafficCodeEnum;
import com.liguo.demo.core.service.TrafficModeService;
import org.springframework.stereotype.Service;

@Service("trainModeService")
public class TrainModeServiceImpl implements TrafficModeService {
    @Override
    public TrafficCodeEnum getCode() {
        return TrafficCodeEnum.TRAIN;
    }

    @Override
    public Long getFee() {
        return 9000L;
    }
}
