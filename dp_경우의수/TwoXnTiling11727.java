package org.example.dp_경우의수;

//https://www.acmicpc.net/problem/11727
// solved Silver3

import java.util.Scanner;

public class TwoXnTiling11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] DP = new int[n + 1];

        DP[1] = 1;

        if (n > 1) {
            DP[2] = 3;
        }

        if (n > 2) {
            for (int i = 3; i <= n; i++) {
                DP[i] = (DP[i - 1] + DP[i - 2] * 2) % 10007;
            }
        }

        System.out.println(DP[n]);
    }
}
