
package com.abdulloh.utils;

import java.util.UUID;

public final class TestDataGenerator {

    private TestDataGenerator() {
    }

    public static String randomEmailName() {
        return "user" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10);
    }

    public static String randomPassword() {
        return "Aa1!" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12);
    }

    public static String defaultEmailDomain() {
        return "example";
    }
}
