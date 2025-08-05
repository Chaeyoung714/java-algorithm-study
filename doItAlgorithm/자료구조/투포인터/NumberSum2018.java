package org.example.doItAlgorithm.자료구조.투포인터;

// https://www.acmicpc.net/problem/2018
// Referenced Silver5 https://velog.io/@isohyeon/Java-%EB%B0%B1%EC%A4%80-2018-%EC%88%98%EB%93%A4%EC%9D%98-%ED%95%A9-5-%EC%97%B0%EC%86%8D%EB%90%9C-%EC%9E%90%EC%97%B0%EC%88%98%EC%9D%98-%ED%95%A9-%EA%B5%AC%ED%95%98%EA%B8%B0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N <= 10,000,000, 2초
 * 2초 = 200,000,000
 * -> O(NlogN) = 10,000,000 * 7 * 3.3 ~= 230,000,000
 * -> O(N)이 안정, O(NlogN)은 애매
 */
public class NumberSum2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 1; //이미 n 자신을 포인터로 하는 경우는 포함시킴
        int start = 1;
        int end = 1;
        int sum = 1;

        /**
         * start++, end++를 하지 않는 이유
         * : 모든 수열을 중복없이 탐색해야 하기 때문!
         */
        while (end < n) {
            if (sum < n) {
                end++;
                sum += end;
            } else if (sum > n) {
                sum -= start;
                start++;
            } else {
                count++;
                end++;
                sum += end;
            }
        }

        System.out.println(count);
    }

//    static int n;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        n = Integer.parseInt(br.readLine());
//
//        int count = 0;
//        int middle = (1 + n) / 2;
//        for (int i = 1; i <= middle; i++) {
//            if (canSatisfy(i)) {
//                count++;
//            }
//        }
//
//        System.out.println(count);
//    }
//
//    private static boolean canSatisfy(int i) {
//        for (int j = n; j >= i; j--) {
//            int sum = countSumBetween(i, j);
//            if (sum == n) {
//                return true;
//            }
//            if (sum < n) { // 점점 값은 줄어들고 있음
//                return false;
//            }
//        }
//        return false;
//    }
//
//    private static int countSumBetween(int i, int j) {
//        return ((j - i + 1) * (i + j)) / 2;
//    }
}
