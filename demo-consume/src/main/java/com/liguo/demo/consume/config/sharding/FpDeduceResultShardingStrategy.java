

package com.liguo.demo.consume.config.sharding;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;


/**
 * 资金计划推导结果表的分片查找策略
 *
 * @author duyubo
 * @time 2020年9月12日 上午10:55:43
 */

public class FpDeduceResultShardingStrategy implements ComplexKeysShardingAlgorithm<String> {

    // 资金计划推导结果表可分片列的数组,按优先级排列
    String[] colPriorityTable = {"order_id"};

    /**
     * 资金计划推导结果表的分片查找方法
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     * @author duyubo
     * @time 2020年9月2日 下午8:04:16
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<String> shardingValue) {
        return CommonShardingUtil.complexRoute(colPriorityTable, availableTargetNames, shardingValue);
    }

}

