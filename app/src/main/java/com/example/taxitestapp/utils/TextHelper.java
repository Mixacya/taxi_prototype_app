package com.example.taxitestapp.utils;

/**
 * Created by mihai on 2/16/2018.
 */

public class TextHelper {

    public static String parceToString(int value) {
        return String.valueOf(value);
    }

    public static String parceToString(double value) {
        return String.valueOf(value);
    }

    public static int parceToInt(String line) {
        return Integer.valueOf(line);
    }

    public static double parceToDouble(String line) {
        return Double.valueOf(line);
    }

}
