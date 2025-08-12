package org.example.doItAlgorithm.자료구조.구간합;

//https://www.acmicpc.net/problem/11659

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartAdd11659 {

    static long[] sums;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        sums = new long[n + 1];

        st = new StringTokenizer(bufferedReader.readLine());
        sums[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i <= n; i++) {
            sums[i] = Integer.parseInt(st.nextToken()) + sums[i - 1];
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            long sum = countSum(start, end);
            System.out.println(sum);
        }
    }

    private static long countSum(int start, int end) {
        if (start == 1) {
            return sums[end];
        }
        return sums[end] - sums[start - 1];
    }
}
