package ua.challenge.security.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by d.bakal on 28.12.2016.
 */
public class TokenGenerator {
    public static String generateSimple() {
        return RandomStringUtils.randomAlphanumeric(20).toUpperCase();
    }
}
