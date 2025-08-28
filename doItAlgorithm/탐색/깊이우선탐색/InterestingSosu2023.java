package org.example.doItAlgorithm.탐색.깊이우선탐색;

// https://www.acmicpc.net/problem/2023
// solved gold5

import java.io.*;
import java.util.*;
import java.util.stream.Collectors; //왜 import가 안됐지?

/**
 * 200,000,000, N <= 8자리
 * N이 시간복잡도의 기준이 되지 않음ㅁ
 * -> 10 * 100* 1000 * ... * 100,000,000
 * -> 10 ^ 35 -> x
 * 이때 한자리수 소수는 1 2 3 5 7 -> 5
 */
public class InterestingSosu2023 {

    static int n;
    static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        /**
         * 왼쪽부터 1~4자리 수 모두 소수면 신기한 소수
         * 왼쪽부터 dfs로 점검
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        primes = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (isPrime(i)) { //2, 3, 5, 7
                dfs(i, 1);
            }
        }

        List<Integer> sortedPrimes = primes.stream().sorted().collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (int prime : primes) {
            sb.append(prime + "\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int currentNum, int length) {
        if (length >= n) {
            primes.add(currentNum);
            return;
        }

        for (int num = 1; num <= 9; num++) {
            int nextNum = currentNum * 10 + num;
            if (isPrime(nextNum)) {
                dfs(nextNum, length + 1);
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }

        int sqrt = (int) Math.sqrt(num);

        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
