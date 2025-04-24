package org.example.dataStructure;

//https://www.acmicpc.net/problem/2178
// 레퍼런스 https://iseunghan.tistory.com/312

/**
 * 왜 목표지점을 실제로 방문했는지 궁금하지 않아도 되는가?
 * 결국 bfs는 모든 지점을 다 돌아야 종료됨 -> 종료시점을 기준으로 목표지점의 방문횟수만 파악하면 된다.
 */

/**
 * 왜 bfs를 떠올려야 할까?
 * dfs bfs 모두 모든 노드를 방문하긴 함
 *
 * bfs는 처음 도착 시점이 곧 최단 거리
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MiroSearch2178 {
    private static char[][] miro;
    private static int[][] dist;
    private static Queue<Pair> queue = new LinkedList<>();

    private static int maxCount = 0;
    private static int endX;
    private static int endY;

    private static int[] dX = new int[]{0, 0, 1, -1};
    private static int[] dY = new int[]{1, -1, 0, 0};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        sc.nextLine(); //주의

        miro = new char[n][m];
        dist = new int[n][m];
        endX = n - 1;
        endY = m - 1;
        for (int x = 0; x < n; x++) {
            String line = sc.nextLine();
            for (int y = 0; y < m; y++) {
                miro[x][y] = line.charAt(y);
                dist[x][y] = -1;
            }
        }

        queue.offer(new Pair(0, 0));
        dist[0][0] = 0;
        bfsMiro();

        System.out.println(dist[endX][endY] + 1);
    }

    private static void bfsMiro() {
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = p.x + dX[i];
                int newY = p.y + dY[i];

                if (newX < 0 || newX > endX || newY < 0 || newY > endY) { //주의
                    continue;
                }
                if (miro[newX][newY] == '0' || dist[newX][newY] != -1) {
                    continue;
                }

                /**
                 * 목적지에 도착하는 순간 최단경로임 -> 바로 out
                 * 근데 사실 여기선 n,m이 가장 끝이라 유의미한 차이 x
                 */
                if (newX == endX && newY == endY) {
                    dist[newX][newY] = dist[p.x][p.y] + 1;
                    return;
                }

                queue.offer(new Pair(newX, newY));
                /**
                 * 현재 점의 거리는 부모 점의 거리 + 1 == 부모 점의 레벨(or차수) + 1이다.
                 */
                dist[newX][newY] = dist[p.x][p.y] + 1;
            }
        }
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
