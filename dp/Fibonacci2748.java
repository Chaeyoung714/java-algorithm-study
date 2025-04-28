package org.example.dp;

import java.util.Scanner;

//https://www.acmicpc.net/problem/2748
//Solved Bronze1

public class Fibonacci2748 {

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
