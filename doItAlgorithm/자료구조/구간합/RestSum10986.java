package org.example.doItAlgorithm.자료구조.구간합;

// https://www.acmicpc.net/problem/10986
// https://kimtaesoo99.tistory.com/145

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RestSum10986 {
    /**
     * 제한 : 100,000,000, n <= 10^6
     * O(n^2) 불가능
     * O(nlogn) 10^6 * 6 * 3.3 -> 20,000,000 가능
     */
    /**
     * Ai  + ... + Aj의 합이 M으로 나누어떨어짐
     * 1. (Ai + ... + Aj) % M == 0
     * ((Ai % M) + (Ai+1 % M) + ... + (Aj % M)) % M == 0
     */

    static int n;
    static int m;
    static int[] rests;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        rests = new int[n];

        st = new StringTokenizer(br.readLine());
        rests[0] = Integer.parseInt(st.nextToken()) % m;
        for (int i = 1; i < n; i++) {
            rests[i] = (rests[i - 1] + Integer.parseInt(st.nextToken()) % m) % m; //A1 + ... + Ak까지 더한 수를 M으로 나눈 결과
        }

        int count = calculateCount();
        System.out.println(count);
    }

    private static int calculateCount() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int rest;
                if (i == 0) {
                    rest = rests[j];
                } else {
                    rest = rests[j] - rests[i - 1];
                }

                if (rest == 0) {
                    count++;
                }
            }
        }
        return count;
    }

//    static int[] numbers;
//    static int n;
//    static int m;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//
//        numbers = new int[n];
//
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < n; i++) {
//            numbers[i] = Integer.parseInt(st.nextToken());
//        }
//
//        int result = countParts();
//        System.out.println(result);
//    }
//
//    private static int countParts() {
//        int count = 0;
//        int prevRest = 0;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                if (j == i) {
//                    prevRest = numbers[j] % m;
//                    continue;
//                }
//                prevRest = (prevRest + (numbers[j] % m)) % m; // i~j까지 합의 나눗셈
//                if (prevRest == 0) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
}
