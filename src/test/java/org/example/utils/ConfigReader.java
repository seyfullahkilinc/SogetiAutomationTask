package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("test-data.properties")) {

            if (input == null) throw new RuntimeException("test-data.properties not found");
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load test-data.properties", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) throw new RuntimeException("Key not found: " + key);
        return value;
    }
}
