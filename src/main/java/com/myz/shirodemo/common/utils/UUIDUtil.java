package com.myz.shirodemo.common.utils;

import java.util.UUID;

/**
 * Created by myz on 2017/7/12.
 */
public class UUIDUtil {

    /**
     *获取一个UUID
     */
    public static String getOneUUID(){
        //获取UUID
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }
    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] ss = new String[number];
        for(int i=0;i<number;i++){
            ss[i] = getOneUUID();
        }
        return ss;
    }
}
