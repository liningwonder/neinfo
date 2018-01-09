package com.cmiot.neinfo;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * description:
 * date 2018/1/9
 *
 * @author lining1
 * @version 1.0.0
 */
public class MemoryTest {

    public static void main(String[] args) {
        double kb = 1024.0;
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String osName = System.getProperty("os.name");
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize();
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize());
        System.out.println(totalMemorySize / kb / kb / kb);
        System.out.println(osName);
        System.out.println("totalMemory" + totalMemory / kb / kb);
        System.out.println("freeMemory" + freeMemory / kb / kb);
    }
}
