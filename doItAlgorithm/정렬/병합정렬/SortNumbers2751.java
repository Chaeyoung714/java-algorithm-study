package org.example.doItAlgorithm.정렬.병합정렬;

// https://www.acmicpc.net/problem/2751
// solved silver5

import java.util.*;
import java.io.*;

/**
 * 200,000,000, N <= 1,000,000
 * O(n^2) 불가능
 * O(nlogn) = 1,000,000 * 3.3 * 6 = 20,000,000 -> 가능!
 */
public class SortNumbers2751 {
    /**
     * 중복되지 않는 음/양수 정렬
     * 오름차순 정렬
     */

    static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int size = 1; //한 그룹의 크기
        while (size <= n) {
            sorted = new int[n];

            //문제 : 2345 / 1
            //size만큼은 잘라야함. tempSize x
            /**
             * 1234 5678 9 10 11
             * 1234 5678 9101112 13 14 15
             */

            for (int start = 0; start < n; start += 2 * size) { //n이 짝수면 i는 n - 1까지만 돈다
                // size = 2
                // n = 16 -> 0,1,2,3  4,5,6,7  8,9,10,11  12,13,14,15로 떨어짐
                // n = 15 -> 0,1,2,3  4,5,6,7  8,9,10,11  12,13,14
                int twoGroupsSize = 2 * size;
                if (n % twoGroupsSize != 0 && n - start < twoGroupsSize) { //딱 떨어지지 않을 때 마지막 병합
                    if (n - start <= size) {
                        int tempSize = (n - start) / 2;
                        doMergeSort(numbers,
                                start,
                                start + tempSize - 1,
                                start + tempSize,
                                n - 1
                        );
                    } else {
                        doMergeSort(numbers,
                                start,
                                start + size - 1,
                                start + size,
                                n - 1
                        );
                    }
                    break;
                }

                //start ~ start + size - 1 : start + size ~ start + size + size - 1
                doMergeSort(numbers,
                        start,
                        start + size - 1,
                        start + size,
                        start + twoGroupsSize - 1
                );
            }

            size *= 2;
            numbers = Arrays.copyOf(sorted, sorted.length); //깊은 복사
        }

        StringBuilder sb = new StringBuilder();
        for (int num : sorted) {
            sb.append(num + "\n");
        }
        System.out.println(sb);
    }

    // 두개의 그룹을 병합 정렬한다.
    private static void doMergeSort(int[] numbers, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        /**
         * first[i]와 second[j]를 비교한다.
         * 더 작은 것이 포인터 자리에 들어간다.
         * 포인터자리에 들어가고 나면 i++ 혹은 j++한다. pointer도 ++한다.
         */
        int pointer = firstStart;
        int i = firstStart;
        int j = secondStart;

        while (pointer <= secondEnd) {
            if (i > firstEnd) { //first는 정렬이 완료됨. second 마무리해야함
                while (j <= secondEnd) {
                    sorted[pointer++] = numbers[j++];
                }
                break;
            }
            if (j > secondEnd) {
                while (i <= firstEnd) {
                    sorted[pointer++] = numbers[i++];
                }
                break;
            }

            if (numbers[i] < numbers[j]) {
                sorted[pointer++] = numbers[i++];
            } else {
                sorted[pointer++] = numbers[j++];
            }
        }
    }
}
