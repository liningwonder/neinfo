package com.cmiot.neinfo.service;

/**
 * description:
 * date 2018/1/8
 *
 * @author lining1
 * @version 1.0.0
 */
public class Bytes {

    public static String substring(String src, int start_idx, int end_idx){
        byte[] b = src.getBytes();
        String tgt = "";
        for(int i=start_idx; i<=end_idx; i++){
            tgt +=(char)b[i];
        }
        return tgt;
    }
}
