package com.hudson.test;

import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        //random image location
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random rd = new Random();

        for (int i = 0; i < tempArr.length; i++) {
            int randomIndex = rd.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[randomIndex];
            tempArr[randomIndex] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            System.out.println(tempArr[i]);
        }

        //create Two-dimensional array and add tempArr in 4x4 Two-dimensional array
        int[][] data = new int[4][4];

        /*        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }*/

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = tempArr[index];
                index++;
            }
        }
    }
}
