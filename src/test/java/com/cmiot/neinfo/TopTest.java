package com.cmiot.neinfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * description:
 * date 2018/1/9
 *
 * @author lining1
 * @version 1.0.0
 */
public class TopTest {

    public static void main(String[] args) throws Exception {
        double cpuUsed = 0;
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("top -b -n 1");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;
            while ((str = in.readLine()) != null) {
                int m = 0;
                // 只分析正在运行的进程，top进程本身除外
                if (str.indexOf(" R ") != -1 && str.indexOf("top") == -1) {
                    strArray = str.split(" ");
                    for (String tmp : strArray) {
                        if (tmp.trim().length() == 0) continue;
                        // 第9列为CPU的使用百分比(RedHat 9)
                        if (++m == 9) {
                            cpuUsed += Double.parseDouble(tmp);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        System.out.println(cpuUsed);
    }
}
