package org.example.doItAlgorithm.정렬.삽입정렬;

// https://www.acmicpc.net/problem/11399
// solved silver4

import java.util.*;
import java.io.*;

/**
 * 100,000,000번, N <= 1,000
 * O(n^2)까지 가능~! -> 어떤 정렬이라도 사용 가능
 */
public class ATM11399 {
    /**
     * 앞에 선 사람부터 Pi분만큼 인출 시간이 누적
     * 전체 시간의 합을 최소화함
     *
     * sol1) 모든 정렬의 경우의 수를 구한다. 만약 min을 넘으면 중간에 break
     * 하나씩만 이동한다. 이때 모든 경우의 수는 O(n^2)인가?
     *
     * sol2) 규칙을 찾는다
     * a b c d e일때 합 = 5a + 4b + 3c + 2d + 1e
     * a가 작을수록 좋겠지만, 반드시 최소일 것이라고 확신할 수 없음 -> 아니지. 반드시 맞는 것 같음
     * a < b일때 5a + 4b 가 더 적을 경우는 없음..!!
     * 고로 그냥 최소 정렬하면 된다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] times = new int[n];
        String[] timeInputs = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(timeInputs[i]);
        }

        for (int i = 1; i < n; i++) {
            int current = i;
            int target = i - 1;

            while (target >= 0) {
                if (times[target] <= times[current]) {
                    current--;
                    target--;
                    break;
                }

                int tmp = times[target];
                times[target] = times[current];
                times[current] = tmp;

                current--;
                target--;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += times[i] * (n - i);
        }

        System.out.println(sum);
    }
}
