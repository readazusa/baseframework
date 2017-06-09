package cn.com.oceansoft.base.util;


import java.util.UUID;

/**
 * Created by smc on 2015/11/19.
 */
public class UuidUtils {

    public static String getUpperUuid(){
        return getUuid().toUpperCase();
    }

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static void main(String[] args){
         for(int i=0;i<10;i++){
             System.out.println(getUuid());
         }
    }
}
