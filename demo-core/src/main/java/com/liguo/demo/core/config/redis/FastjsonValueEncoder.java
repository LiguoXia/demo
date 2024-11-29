package com.liguo.demo.core.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alicp.jetcache.support.AbstractValueEncoder;
import com.alicp.jetcache.support.CacheEncodeException;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 22:55
 * @since 0.0.1
 */
@Deprecated
public class FastjsonValueEncoder extends AbstractValueEncoder {
    @SuppressWarnings("deprecation")
    public static final FastjsonValueEncoder INSTANCE = new FastjsonValueEncoder(true);

    protected static int IDENTITY_NUMBER = 0x4A953A81;

    public FastjsonValueEncoder(boolean useIdentityNumber) {
        super(useIdentityNumber);
    }

    @Override
    public byte[] apply(Object value) {
        try {
            byte[] bs1 = JSON.toJSONBytes(value, SerializerFeature.WriteClassName);
            if (useIdentityNumber) {
                byte[] bs2 = new byte[bs1.length + 4];
                writeHeader(bs2, IDENTITY_NUMBER);
                System.arraycopy(bs1, 0, bs2, 4, bs1.length);
                return bs2;
            } else {
                return bs1;
            }
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Fastjson Encode error. ");
            sb.append("msg=").append(e.getMessage());
            throw new CacheEncodeException(sb.toString(), e);
        }
    }
}
