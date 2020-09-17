package com.liguo.demo.core.service.impl;

import com.liguo.demo.core.enums.TrafficCodeEnum;
import com.liguo.demo.core.service.TrafficModeService;
import org.springframework.stereotype.Service;

@Service("busModeService")
public class BusModeServiceImpl implements TrafficModeService {
    @Override
    public TrafficCodeEnum getCode() {
        return TrafficCodeEnum.BUS;
    }

    @Override
    public Long getFee() {
        return 10000L;
    }
}
