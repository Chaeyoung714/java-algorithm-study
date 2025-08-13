package org.example.doItAlgorithm.자료구조.스택큐;

// https://www.acmicpc.net/problem/2164
// solved silver4

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 200,000,000번 가능, N <= 500,000
 * O(N^2) = 250,000,000,000 -> x
 * O(NlogN)까지는 가능할듯
 */
public class Card2164 {
    /**
     * 위에서부터 버리고 -> 밑으로 넣고의 반복
     * 양방향이 필요함
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        if (n == 1) {
            System.out.println(1);
            return;
        }

        while (queue.size() > 1) {
            queue.poll();
            int poll = queue.poll();

            queue.add(poll);
        }

        System.out.println(queue.poll());
    }
}
