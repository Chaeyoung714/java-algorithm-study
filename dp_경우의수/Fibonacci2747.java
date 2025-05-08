package org.example.dp_경우의수;

//https://www.acmicpc.net/problem/2747
//solved Bronze2

import java.util.Scanner;

public class Fibonacci2747 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        long[] DP = new long[n + 1];

        DP[0] = 0;
        DP[1] = 1;

        for (int i = 2; i <= n; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }
        System.out.println(DP[n]);
    }
}
