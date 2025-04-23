package org.example.dataStructure;

//https://www.acmicpc.net/problem/2178

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MiroSearch2178 {
    private static int[][] arr;
    private static  int[][] visited;

    private static int maxCount = 0;

    private static int[] dRow = new int[]{0, 0, 1, -1};
    private static int[] dCol = new int[]{1, -1, 0, 0};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt() - 1;
        int m = sc.nextInt() - 1;

        sc.nextLine(); //주의

        arr = new int[n + 1][m + 1];
        visited = new int[n + 1][m + 1];
        for (int row = 0; row <= n; row++) {
            String line = sc.nextLine();
            for (int column = 0; column <= m; column++) {
                int i = Integer.parseInt(String.valueOf(line.charAt(column)));
                arr[row][column] = i;
            }
        }

        if (arr[0][0] == 1) {
            int minTrack = countMinTrackTo(0, 0, n, m, 1);
            System.out.println(minTrack);
        } else {
            System.out.println(0);
        }
    }

    private static int countMinTrackTo(int currRow, int currCol, int endRow, int endCol, int totalCount) {
        if (currRow == endRow && currCol == endCol) {
            if (totalCount > maxCount) {
                maxCount = totalCount;
            }
            return totalCount;
        }

        visited[currRow][currCol] = 1;
        int currCount = -1;

        for (int d = 0; d < 4; d++) {
            int newRow = currRow + dRow[d];
            int newCol = currCol + dCol[d];
            if (newRow < 0 || newRow >= endRow || newCol < 0 || newCol >= endCol) {
                continue;
            }
            if (arr[newRow][newCol] == 0 || visited[newRow][newCol] == 1) {
                continue;
            }

            //먼가 값 전달이 안됨 (2,0)에서
            currCount = countMinTrackTo(newRow, newCol, endRow, endCol, totalCount + 1);
        }

        visited[currRow][currCol] = 0;
        return currCount;
    }
}
