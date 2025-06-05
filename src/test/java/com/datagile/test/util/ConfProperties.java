package com.datagile.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfProperties {
    private static final Properties properties;

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/conf.properties");
                InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)){
            properties = new Properties();
            properties.load(reader);
            if (fis == null) {
                throw new RuntimeException("Failed to load config. Check conf.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
