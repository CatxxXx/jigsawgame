package com.hudson.util;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {
    public static String getCode() {
        ArrayList<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }
        Random rd = new Random();
        for (int i = 0; i < 4; i++) {
            int index = rd.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
        sb.append(rd.nextInt(10));
        char[] arr = sb.toString().toCharArray();
        int randomIndex = rd.nextInt(arr.length);
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        return new String(arr);
    }
}
