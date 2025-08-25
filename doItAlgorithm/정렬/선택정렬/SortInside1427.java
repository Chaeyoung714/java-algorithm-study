package org.example.doItAlgorithm.정렬.선택정렬;

// https://www.acmicpc.net/problem/1427

import java.io.*;
import java.util.*;

/**
 * 200,000,000, N <= 1,000,000,000
 * O(n)도 어려움 => 엥..?!
 * -> O(logn) 필요!
 */
public class SortInside1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numberInput = br.readLine().split("");

        int[] numbers = new int[numberInput.length];
        for (int i = 0; i < numberInput.length; i++) {
            numbers[i] = Integer.parseInt(numberInput[i]);
        }

        Arrays.sort(numbers);

        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            sb.append(num);
        }

        System.out.println(sb.reverse());

    }
}
