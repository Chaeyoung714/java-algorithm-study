package org.example.doItAlgorithm.탐색.깊이우선탐색;

// https://www.acmicpc.net/problem/13023

import java.util.*;
import java.io.*;

/**
 * 시간초과 ㅠㅠ
 */
public class ABCDE13023 {
    /**
     * 친구관계 dfs가 4번만 타고들어가면 됨.
     * 이때 각각의 친구는 다 다른 친구
     */

    static int[][] friends;
    static int[] visited;
    static boolean isExists;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        friends = new int[n][n];
        visited = new int[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            friends[f1][f2] = 1;
            friends[f2][f1] = 1;
        }

        for (int f = 0; f < n; f++) {
            visited = new int[n];

            dfs(f, 1);
            if (isExists) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    private static void dfs(int from, int count) {
        if (count >= 5) {
            isExists = true;
            return;
        }

        visited[from] = 1;


        for (int to = 0; to < n; to++) {
            if (friends[from][to] == 1 && visited[to] == 0) {
                dfs(to, count + 1);
                if (isExists) {
                    break;
                }
            }
        }
    }
}
