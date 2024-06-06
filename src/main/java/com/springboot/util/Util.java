package com.springboot.util;

import java.text.MessageFormat;

public class Util {


    /**
     * @param expression
     * @return
     */
    public static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

    public static String containsIgnoreCase(String expression) {
        return MessageFormat.format("%{0}%", expression.toLowerCase());
    }

    /**
     * @param expression
     * @return
     */
    public static String startWith(String expression) {
        return MessageFormat.format("{0}%", expression);
    }
}
