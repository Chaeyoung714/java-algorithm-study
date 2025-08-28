package org.example.doItAlgorithm.정렬.기수정렬;

//Referneced

import java.io.*;
import java.util.*;

public class SortNumbers10989 {
    /**
     * 수는 최대 5자리!
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int[] result = new int[n];
        int length = 1;
        int count = 0;
        while (count <= 5) {
            int[] bucket = new int[10];
            for (int i = 0; i < n; i++) {
                bucket[(numbers[i] / length) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                result[bucket[(numbers[i] / length % 10)] - 1] = numbers[i];
                bucket[(numbers[i] / length) % 10]--;
            }

            for (int i = 0; i < n; i++) {
                numbers[i] = result[i];
            }

            length = length * 10;
            count++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(numbers[i] + "\n");
        }
        System.out.println(sb);
    }
}
