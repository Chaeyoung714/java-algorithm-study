package org.example.doItAlgorithm.자료구조.스택큐;

// https://www.acmicpc.net/problem/11286

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 200,000,000번, N <= 100,000
 * O(n), O(nlogn)
 * 정수 <= 2^31 => Long
 */
public class AbsoluteHeap11286 {
    /**
     * 정수 x를 넣음
     * x != 0 => 배열에 x를 추가
     * x == 0 -> 절댓값이 가장 작은 값 && 값이 더 작은 값을 출력하고 그 값을 배열에서 제거함
     * 배열이 빈 경우에는 0을 출력함
     * <p>
     * 어떻게 가장 작은 수를 구하지?
     * 1. 가장 먼저 떠오른 거 - 최소 힙
     * 최소 힙처럼, push할때마다 정렬에 맞게 추가하자.
     */

    // 시간초과

    static int startPoint = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Deque<Long> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(br.readLine());

            if (x == 0) {
                if (deque.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
                Long min = deque.pollFirst();
                System.out.println(min);
                continue;
            }
            locateX(deque, x);
            startPoint++;
        }

    }

    private static void locateX(Deque<Long> deque, long x) {
        if (deque.isEmpty()) {
            deque.addLast(x);
            return;
        }

        long last = deque.pollLast();

        if (Math.abs(last) == Math.abs(x)) {
            if (x < 0 && last > 0) {
                locateX(deque, x);
                deque.addLast(last);
            } else if (x > 0 && last < 0) {
                deque.addLast(last);
                deque.addLast(x);
            } else {
                deque.addLast(last);
                deque.addLast(last);
            }
            return;
        }
        if (Math.abs(x) < Math.abs(last)) {
            locateX(deque, x);
            deque.addLast(last);
        } else {
            deque.addLast(last);
            deque.addLast(x);
        }
    }
}
