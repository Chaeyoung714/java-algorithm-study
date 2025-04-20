package org.example.divideConquer;

//https://www.acmicpc.net/problem/1074
//Gold5 solved

import java.util.Scanner;

public class Z1074 {
    private static int row;
    private static int column;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.next());
        row = Integer.parseInt(scanner.next());
        column = Integer.parseInt(scanner.next());

        int visitCount = countVisitCount(
                0, ((int) Math.pow(2, n)) - 1,
                0, ((int) Math.pow(2, n)) - 1,
                0
        );

        System.out.println(visitCount);
    }

    private static int countVisitCount(int rowStartBound, int rowEndBound, int columnStartBound, int columnEndBound, int visitCount) {
        int blockLength = rowEndBound - rowStartBound + 1;
        int blockHalfLength = blockLength / 2;
        int oneQuarterSize = blockHalfLength * blockHalfLength;

        if (oneQuarterSize < 1) { // while 아니고 if
            return visitCount;
        }

        if (row < rowStartBound + blockHalfLength) {
            if (column < columnStartBound + blockHalfLength) { //1
                return countVisitCount(
                        rowStartBound, rowStartBound + blockHalfLength - 1,
                        columnStartBound, columnStartBound + blockHalfLength - 1,
                        visitCount
                );
            } else { // 2
                return countVisitCount(
                        rowStartBound, rowStartBound + blockHalfLength - 1,
                        columnStartBound + blockHalfLength, columnEndBound,
                        visitCount + oneQuarterSize
                );
            }
        } else {
            if (column < columnStartBound + blockHalfLength) { //3
                return countVisitCount(
                        rowStartBound + blockHalfLength, rowEndBound,
                        columnStartBound, columnStartBound + blockHalfLength - 1,
                        visitCount + oneQuarterSize * 2
                );
            } else { //4
                return countVisitCount(
                        rowStartBound + blockHalfLength, rowEndBound,
                        columnStartBound + blockHalfLength, columnEndBound,
                        visitCount + oneQuarterSize * 3
                );
            }
        }
    }
}
