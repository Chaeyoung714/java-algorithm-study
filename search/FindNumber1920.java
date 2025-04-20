package org.example.search;

import java.util.ArrayList;// https://www.acmicpc.net/problem/1920
import java.util.List;
// Silver4 solved
/**
 * 1. HashSet - 시간은 더 적게 걸리고 제일 편함
 * 2. 이분탐색 - 정렬 반드시 필요
 * 2-1) 재귀함수 사용 시 : 메모리 초과
 * 2-2) while문 + str, end 매번 수정 : 통과
 */

import java.util.Scanner;
import java.util.stream.Collectors;

public class FindNumber1920 {
    private static List<Integer> sortedStandard;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        /**
         * Set으로 할 필요가 없는 이유
         * 1. 이분탐색 시 정렬, 즉 순서가 필요하다
         * 2. 입력값끼리의 중복이 없음을 보장할 수 있다.
         */
        List<Integer> standardInts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            standardInts.add(scanner.nextInt());
        }
        sortedStandard = standardInts.stream()
                .sorted()
                .collect(Collectors.toList());

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int comparingInt = scanner.nextInt();
            if (isNumberExist(comparingInt, 0, sortedStandard.size() - 1)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static boolean isNumberExist(int comparingInt, int strIndex, int endIndex) {
        while (strIndex <= endIndex) {
            int midIndex = (endIndex + strIndex) / 2; // - 아님 조심 ㅠ
            int midValue = sortedStandard.get(midIndex);
            if (midValue == comparingInt) {
                return true;
            }
            if (midValue < comparingInt) {
                strIndex = midIndex + 1;
            }
            if (midValue > comparingInt) {
                endIndex = midIndex - 1;
            }
        }
        return false;
    }

    /**
     * 실패 (메모리초과)
     */
//    private static boolean isNumberExist(int comparingInt, int strIndex, int endIndex) {
//        if (strIndex <= endIndex) {
//            int midIndex = (endIndex + strIndex) / 2; // - 아님 조심 ㅠ
//            int midValue = sortedStandard.get(midIndex);
//            if (midValue < comparingInt) {
//                return isNumberExist(comparingInt, midIndex + 1, endIndex);
//            }
//            if (midValue > comparingInt) {
//                return isNumberExist(comparingInt, strIndex, midIndex);
//            }
//            return true;
//        }
//        return false;
//    }

//    private static boolean isNumberExist(int comparingInt, Set<Integer> standardInts) {
//        if (standardInts.contains(comparingInt)) {
//            return true;
//        }
//        return false;
//    }
}
