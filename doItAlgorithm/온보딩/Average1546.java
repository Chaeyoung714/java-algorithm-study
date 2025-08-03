package org.example.doItAlgorithm.온보딩;

// https://www.acmicpc.net/problem/1546
// solved bronze1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Average1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        int maxNumber = numbers.stream()
                .max(Integer::compare)
                .get();

        double newSum = numbers.stream()
                .mapToDouble(number -> (double) number / maxNumber * 100)
                .sum();

        double newAverage = (double) newSum / n;

        System.out.println(newAverage);

    }
}
