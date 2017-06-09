package cn.com.oceansoft.base.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by smc on 2015/11/25.
 * 基于md5 或者sha6的加密
 */
public final class MHUtils {

    public static String md5(String target){
        return DigestUtils.md5Hex(target);
    }

}
