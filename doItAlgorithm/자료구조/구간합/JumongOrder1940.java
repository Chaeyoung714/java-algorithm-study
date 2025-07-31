package org.example.doItAlgorithm.자료구조.구간합;

// https://www.acmicpc.net/problem/1940
// silver4 solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumongOrder1940 {

    static int[] ingredients;
    static int ingredientCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ingredientCount = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        ingredients = new int[ingredientCount];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < ingredientCount; i++) {
            ingredients[i] = Integer.parseInt(st2.nextToken());
        }

        int result = countPairs(m);

        System.out.println(result);

        br.close();
    }

    private static int countPairs(int M) {
        int count = 0;

        for (int in1 : ingredients) {
            for (int in2 : ingredients) {
                if (in1 >= in2) {
                    continue;
                }

                if (in1 + in2 == M) {
                    count++;
                }
            }
        }
        return count;
    }
}
