package org.example.greedy;

//https://www.acmicpc.net/problem/1120

import java.util.Scanner;

public class String1120 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        int minGap = returnMinGap(a, b);
        System.out.println(minGap);
    }

    private static int returnMinGap(String a, String b) {
        // 남은 부분은 동일한 알파벳을 추가할 것임
        return calculateMinGapBetween(a, b);
    }

    private static int calculateMinGapBetween(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();

        int minGap = Integer.MAX_VALUE;

        // 가장 앞단과 가장 뒷단의 값을 빼고 더하는 것도 방법이 될 수 있을듯.
        for (int aStart = 0; aStart <= lenB - lenA; aStart++) {
            int sameCount = 0;
            for (int bPointer = aStart; bPointer < aStart + lenA; bPointer++) {
                if (bPointer >= lenB) {
                    throw new IllegalStateException();
                }

                int aPointer = bPointer - aStart;

                if (a.charAt(aPointer) == b.charAt(bPointer)) {
                    sameCount++;
                }
            }

            int gap = lenA - sameCount;
            if (gap < minGap) {
                minGap = gap;
            }
        }

        return minGap;
    }
}
