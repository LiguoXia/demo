package com.liguo.demo.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/2/27 21:16
 * @since 0.0.1
 */
@Slf4j
public class EncodeURIUtil {
    public static void main(String[] args)  throws UnsupportedEncodingException{
        log.info("解密:{}",  URLEncoder.encode("WVpEWV6Z176g2rq3XabVjZJPSfMSdNBmS7YaTXYSNAkPmuZjLgouJj86e4FH/xmHFVO5eL4XBiXnDkFj2mJhs049YZZ1ULT5/W6+q0Ebl2fzKLE8Bsz/V9WibmgXn2jGkgSr5FtNFiKH/vTKswDTC12sUfVt99PJ9PW0er8Xx0w=", "UTF-8"));
    }

    public static String encodeURI(String str)
            throws UnsupportedEncodingException
    {
        return URLEncoder.encode(str, "UTF-8");
    }
}
