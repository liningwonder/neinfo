package com.cmiot.neinfo;

import java.net.InetAddress;

/**
 * description:
 * date 2018/1/9
 *
 * @author lining1
 * @version 1.0.0
 */
public class IpTest {

    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostName());//主机名
        System.out.println(address.getCanonicalHostName());//主机别名
        System.out.println(address.getHostAddress());//获取IP地址
        System.out.println("===============");
    }
}
