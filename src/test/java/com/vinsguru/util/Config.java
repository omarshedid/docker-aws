package com.vinsguru.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static final String DEFAULT_PROPERTIES = "config/default.properties";
    public static final Logger log = LoggerFactory.getLogger(Config.class);
    public static Properties properties;

    public static void initialize(){
        properties = loadProperties();

//        for(String key:System.getProperties().stringPropertyNames()){
//            System.out.println(System.getProperty(key));
//        }
        for(String key:properties.stringPropertyNames()){
            if(System.getProperties().stringPropertyNames().contains(key)){
                log.info("Key {} was reset from system env to {} ",key,System.getProperty(key));
                properties.setProperty(key,System.getProperty(key));
            }

        }

        log.info("Testing Properties");
        log.info("--------------------------");
        for(String key:properties.stringPropertyNames()){
            log.info("{} - {}",key,properties.getProperty(key));
        }
        log.info("--------------------------");

    }
    public static Properties loadProperties(){

        Properties properties = new Properties();

        try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            properties.load(stream);
        }catch(Exception e){
            log.info("Unable to read properties file {}",DEFAULT_PROPERTIES);
        }
        return properties;

    }

    public static String get(String key){
        return properties.getProperty(key);
    }

}
