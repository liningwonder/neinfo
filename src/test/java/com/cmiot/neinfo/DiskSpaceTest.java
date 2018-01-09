package com.cmiot.neinfo;

import java.io.File;

/**
 * description:
 * date 2018/1/9
 *
 * @author lining1
 * @version 1.0.0
 */
public class DiskSpaceTest {

    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File _file : roots) {
            System.out.println(_file.getPath());
            //System.out.println(_file.getName());
            System.out.println("Free space = " + _file.getFreeSpace());
            System.out.println("Usable space = " + _file.getUsableSpace());
            System.out.println("Total space = " + _file.getTotalSpace());
            System.out.println("used space  = " + (_file.getTotalSpace()-_file.getFreeSpace()));
            System.out.println();
        }
        File win = new File("C:\\WINDOWS");
        System.out.println(win.getPath());
        System.out.println(win.getName());
        System.out.println("Free space = " + win.getFreeSpace());
        System.out.println("Usable space = " + win.getUsableSpace());
        System.out.println("Total space = " + win.getTotalSpace());
        System.out.println();
    }
/*        File diskPartition = new File("C:");

        long totalCapacity = diskPartition.getTotalSpace();
        long freePartitionSpace = diskPartition.getFreeSpace();
        long usablePatitionSpace = diskPartition.getUsableSpace();

        System.out.println("**** Sizes in Mega Bytes ****\n");
        System.out.println("Total C partition size : " + totalCapacity / (1024*1024) + " MB");
        System.out.println("Usable Space : " + usablePatitionSpace / (1024 *1024) + " MB");
        System.out.println("Free Space : " + freePartitionSpace / (1024 *1024) + " MB");

        System.out.println("\n**** Sizes in Giga Bytes ****\n");

        System.out.println("Total C partition size : " + totalCapacity / (1024*1024*1024) + " GB");
        System.out.println("Usable Space : " + usablePatitionSpace / (1024 *1024*1024) + " GB");
        System.out.println("Free Space : " + freePartitionSpace / (1024 *1024*1024) + " GB");*/

}

