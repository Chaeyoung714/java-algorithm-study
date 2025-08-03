package org.example.doItAlgorithm.온보딩;

// https://www.acmicpc.net/problem/11720
// solved Bronze2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NumberSum11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int sum = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(sum);
    }
}
