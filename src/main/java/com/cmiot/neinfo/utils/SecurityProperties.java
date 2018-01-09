package com.cmiot.neinfo.utils;

import com.cmiot.neinfo.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * description:
 * date 2018/1/8
 *
 * @author lining1
 * @version 1.0.0
 */
public class SecurityProperties {

    private static final Logger log = LoggerFactory.getLogger(SecurityProperties.class);

    private static Map<String, String> propertiesMap;

    private SecurityProperties(){
        propertiesMap = readProperties();
    }

    public Map<String, String> getPropertiesMap(){
        return propertiesMap;
    }

    /**
     * 获取实例
     */
    private static class PropertiesInstance{
        private static final SecurityProperties securityProperties = new SecurityProperties();
    }

    public static SecurityProperties getInstance(){
        return PropertiesInstance.securityProperties;
    }

    private Map<String,String> readProperties() {
        propertiesMap = new HashMap<>();
        Properties properties = new Properties();
        InputStream in = SecurityProperties.class.getClassLoader().getResourceAsStream(Constant.CONFIG_PATH);
        try {
            properties.load(in);
            Enumeration en = properties.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = properties.getProperty(key);
                propertiesMap.put(key, value);
            }
        } catch(Exception e) {
            log.warn("读取配置文件出错");
        } finally {
            try {
                if(null != in){
                    in.close();
                }
            } catch (IOException e) {
                log.warn("关闭文件流出错");
            }
        }
        return propertiesMap;
    }
}
