package org.example.dp_k번째수;

// https://www.acmicpc.net/problem/1023

import java.util.Scanner;
import java.util.Stack;

public class gualhoString1023 {
    /**
     * 어떻게 풀지 sol1) 일단 다 해본다. 1. ((((에서 시작 -> 맨뒤에서부터 하나씩 꺾어본다. 2. 고정되지 않은 부분 중 가장 앞자리를 )로 고정한 뒤 괄호 ㄴㄴ 문자열인지 확인한다. 3. 탐색이
     * 끝났으면 고정되지 않은 부분을 한칸 앞으로 땡긴 뒤 동일한 동작을 수행한다. (2번을)
     * <p>
     * sol2) 부문제로 나눈다. 1. DP[i] = i번째 사전순 문자열..? 2. DP[0] = ( * n
     */

    /**
     * 해결해야 하는 문제 : 메모리 초과
     */
    // Exception in thread "main" java.lang.IllegalArgumentException: Illegal Capacity: -1096161460
    // kLong = 1234567890123 -> k = 1912276171
    // 해결 : DP 안 쓰고 직전 문자열만 기억하면서 진행, k는 Long으로 받고 다님

//    static List<String> DP;
    static int totalStringLength;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lgth = scanner.nextInt();
        Long k = scanner.nextLong();
        totalStringLength = lgth;

//        DP = new ArrayList<>(); //index = 0~k

        try {
            String kthGaulhoNonoString = findKthGualhoNonoString(k);
            System.out.println(kthGaulhoNonoString);
        } catch (IllegalStateException e) {
            System.out.println(-1);
        }
    }

    private static String findKthGualhoNonoString(Long k) {
        /**
         * 1. 문자열 중 ) 가 등장하는 마지막 위치를 찾는다.
         * 2. )...(... 가 있다면, ( 중 마지막 위치를 )로 바꾼다. (사전순 + 1)
         * 3. )...)... 의 구조라면, 전부 (...(...로 바꾸고 그 앞을 )로 바꾼다.
         * 4. 바꾼 문자열이 괄호ㄴㄴ문자열인지 확인하고, 그렇지 않다면 1~3을 반복한다.
         */
        String beforeString = ""; //DP일 필요 없음. 직전 문자열만 기억하면 됨

        beforeString = "(".repeat(totalStringLength);
        if (k == 0) {
            return beforeString;
        }

        beforeString = beforeString.substring(0, totalStringLength - 1) + ")";
        if (k == 1) {
            return beforeString;
        }

        for (int i = 2; i <= k; i++) {
            String afterString = findNextDictionaryStringFrom(beforeString);
            beforeString = afterString;
        }

        return beforeString;
    }

    private static String findNextDictionaryStringFrom(String realBeforeString) {
        String beforeString = realBeforeString;
        while (true) {
            String afterString = "";
            int standardIndex = beforeString.lastIndexOf(")");

            int changingIndex = beforeString.lastIndexOf("("); // 사전순 + 1을 하기 위해 필요한 수정 위치
            if (changingIndex == -1) { // beforeString이 ))..))라면 더이상 진행 불가
                throw new IllegalStateException();
            }

            if (changingIndex <= standardIndex) {
                int newStandardIndex = beforeString.substring(0, standardIndex).lastIndexOf("(");
                afterString = beforeString.substring(0, newStandardIndex) + ")" + "(".repeat(
                        totalStringLength - newStandardIndex - 1);
            } else {
                afterString = beforeString.substring(0, changingIndex) + ")" + beforeString.substring(changingIndex + 1);
            }

            if (isGualhoNonoString(afterString)) {
                return afterString;
            }

            beforeString = afterString;
        }
    }

    private static boolean isGualhoNonoString(String afterString) {
        boolean isGualhoNoNoString = false;
        Stack<String> gualhoStack = new Stack<>();
        for (String str : afterString.split("")) {
            if (str.equals("(")) {
                gualhoStack.push(str);
                continue;
            }
            if (gualhoStack.isEmpty()) {
                isGualhoNoNoString = true;
                break;
            }
            gualhoStack.pop();
        }

        if (isGualhoNoNoString) {
            return true;
        }
        if (gualhoStack.isEmpty()) {
            return false;
        }
        return true;
    }
}
