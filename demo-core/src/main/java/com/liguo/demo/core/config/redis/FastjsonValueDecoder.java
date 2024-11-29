package com.liguo.demo.core.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alicp.jetcache.support.AbstractValueDecoder;
import com.alicp.jetcache.support.DecoderMap;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 22:58
 * @since 0.0.1
 */
@Deprecated
public class FastjsonValueDecoder extends AbstractValueDecoder {
    @SuppressWarnings("deprecation")
    public static final FastjsonValueDecoder INSTANCE = new FastjsonValueDecoder(true);

    public FastjsonValueDecoder(boolean useIdentityNumber) {
        super(useIdentityNumber);
    }

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        DecoderMap.register(FastjsonValueEncoder.IDENTITY_NUMBER, FastjsonValueDecoder.INSTANCE);
    }

    @Override
    public Object doApply(byte[] buffer) {
        if (useIdentityNumber) {
            byte[] bs = new byte[buffer.length - 4];
            System.arraycopy(buffer, 4, bs, 0, bs.length);
            return JSON.parse(bs);
        } else {
            return JSON.parse(buffer);
        }
    }
}
