package org.example.dp_LISLCS다차원DP;

// https://www.acmicpc.net/problem/11055

import java.util.Arrays;
import java.util.Scanner;

public class LIS11055 {
    static int[] DP;
    static int[] numbers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        DP = new int[n + 1];
        numbers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int result = calculateMaxIncreasingSequence();
        System.out.println(result);
    }

    private static int calculateMaxIncreasingSequence() {
        DP[1] = numbers[1];
        for (int i = 2; i < DP.length; i++) {
            DP[i] = numbers[i];
            for (int j = 1; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    DP[i] = Math.max(DP[i], DP[j] + numbers[i]);
                }
            }
        }
        return Arrays.stream(DP).max().getAsInt();
    }
}
