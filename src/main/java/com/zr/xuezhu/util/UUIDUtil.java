package com.zr.xuezhu.util;

import com.zr.xuezhu.duanxin.DuanXinUtil;

import java.util.Random;
import java.util.UUID;


public class UUIDUtil {
    public static String getCode() {
        /*String[] split = UUID.randomUUID().toString().split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
        }
        String code = sb.substring(0, 4);*/

        Random r = new Random();
        String code = r.nextInt(9000)+1000+"";

        return code;
    }

    public static String getOrderNum() {
        String[] split = UUID.randomUUID().toString().split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i]);
        }
        String code = sb.substring(0, 20);
        return code;
    }


}
