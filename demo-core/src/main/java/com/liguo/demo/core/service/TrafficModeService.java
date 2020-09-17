package com.liguo.demo.core.service;

import com.liguo.demo.core.enums.TrafficCodeEnum;

public interface TrafficModeService {
    TrafficCodeEnum getCode();
    Long getFee();
}
