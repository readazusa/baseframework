package cn.com.oceansoft.base.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by smc on 2015/11/25.
 * 对称加密
 */
public class DESUtils {

    private static Key key;
    private static String KEY_STR = "12345678";// 密钥
    private static String ALGORITHM = "DES";// 加密类型

    private static final Logger log = LoggerFactory.getLogger(DESUtils.class);

    public static String encrypt(String source, String keyStr) {
        String keyS = null;
        String result = null;
        if (StringUtils.isNotBlank(keyStr)) {
            if (keyStr.length() < 8) {
                log.error("秘钥不正确，必须使用长度大于8");
            }
            keyS = keyStr;
        } else {
            keyS = KEY_STR;
        }
        try {
            DESKeySpec desKeySpec = new DESKeySpec(keyS.getBytes());
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            BASE64Encoder base64encoder = new BASE64Encoder();
            byte[] bytes = cipher.doFinal(source.getBytes());
            result = base64encoder.encode(bytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String source, String keyStr) {
        String keyS = null;
        String result = null;
        if (StringUtils.isNotBlank(keyStr)) {
            if (keyStr.length() < 8) {
                log.error("秘钥不正确，必须使用长度大于8");
            }
            keyS = keyStr;
        } else {
            keyS = KEY_STR;
        }
        try {
            DESKeySpec desKeySpec = new DESKeySpec(keyS.getBytes());
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = null;
            try {
                bytes = cipher.doFinal(decoder.decodeBuffer(source));
                result = new String(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对str进行DES加密
     *
     * @param source 加密字符串源
     * @return 密文字符串
     */
    public static String encrypt(String source) {
        return encrypt(source, null);
    }

    /**
     * 对str进行DES解密
     * @param source 待解密字符串
     * @return 明文字符串
     */
    public static String decrypt(String source) {
        return decrypt(source, null);
    }


    public static void main(String[] args) {
        System.out.println(encrypt("123456"));

        System.out.println(decrypt("ED5wLgc3Mnw="));
    }
}
