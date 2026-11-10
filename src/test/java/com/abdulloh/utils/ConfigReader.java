
package com.abdulloh.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "config.properties file not found"
                );
            }

            properties.load(input);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to load config.properties", e
            );
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        String value = System.getProperty(
                key,
                properties.getProperty(key)
        );

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing configuration property: " + key
            );
        }

        return value.trim();
    }

    public static boolean getBoolean(String key) {
        String value = get(key);

        if (!value.equalsIgnoreCase("true")
                && !value.equalsIgnoreCase("false")) {
            throw new IllegalArgumentException(
                    "Invalid boolean configuration: " + key
            );
        }

        return Boolean.parseBoolean(value);
    }
}
