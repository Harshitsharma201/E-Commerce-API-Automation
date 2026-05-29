package com.api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            // 🌟 1. Read the environment system property from the terminal flag (-Denv=...)
            // If no flag is passed, default to "qa"
            String env = System.getProperty("env", "qa").toLowerCase();
            
            String filePath = "src/test/resources/config/" + env + ".properties";
            
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInputStream);
            
            System.out.println("🌍 Framework initialized targeting environment: [" + env.toUpperCase() + "]");
        } catch (IOException e) {
            throw new RuntimeException("💥 Failed to load environment properties configuration file! Check path or env flag.", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}