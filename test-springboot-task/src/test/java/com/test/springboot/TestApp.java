package com.test.springboot;

public class TestApp {

    public static void main(String[] args) {

        System.out.println(isPalin("aa"));
    }

    public static boolean isPalin(String data) {
        int len = data.length();
        for (int i = 0; i < len/2; i++) {
            if (data.charAt(i) != data.charAt(len-1-i)) {
                return false;
            }
        }
        return true;
    }
}
