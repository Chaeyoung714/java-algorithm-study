package org.example.dp_LISLCS다차원DP;

// https://www.acmicpc.net/problem/9251
// Gold 5 Referenced https://st-lab.tistory.com/139

import java.util.Scanner;

public class LCS9251 {
    // 해결 방식 : 고려해야 할 점이 2가지여서 어려웠음 -> 2차원 배열 DP를 사용한다..!!
    /**
     * \ A C A Y K P
     * C
     * A
     * P
     * C
     * A
     * K
     */
    /**
     * DP[i][j] = a문자열의 i번째까지 문자열과, b 문자열의 j번째까지 문자열을 비교했을 때 LCS의 길이
     * 1. a[i] == b[j] : DP[i-1][j-1] + 1
     * 2. a[i] != b[j]
     *      1. a[i]가 CS에 포함될 때 길이
     *      2. b[j]가 CS에 포함될 때 길이
     *      중 max
     */

    static int[][] DP;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] a = scanner.nextLine().split("");
        String[] b = scanner.nextLine().split("");

        DP = new int[a.length][b.length];

        int lcsLength = countLcsLength(a, b);
        System.out.println(lcsLength);
    }

    private static int countLcsLength(String[] a, String[] b) {
        if (a[0].equals(b[0])) {
            DP[0][0] = 1;
        }
        for (int i = 1; i < a.length; i++) {
            if (DP[i - 1][0] == 1) {
                DP[i][0] = 1;
            } else {
                if (a[i].equals(b[0])) {
                    DP[i][0] = 1;
                }
            }
        }
        for (int j = 1; j < b.length; j++) {
            if (DP[0][j - 1] == 1) {
                DP[0][j] = 1;
            } else {
                if (b[j].equals(a[0])) {
                    DP[0][j] = 1;
                }
            }
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                if (a[i].equals(b[j])) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
                }
            }
        }

        return DP[a.length - 1][b.length - 1];
    }

//    static int[] DP;
//    static String[] LCS;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        String str1 = scanner.nextLine();
//        String str2 = scanner.nextLine();
//
//        DP = new int[str1.length()]; //str1의 i번째 문자가 공통 문자열에 해당될 때, 최대 LCS 길이
//        LCS = new String[str1.length()];
//
//        int result = countLengthOfLCS(str1, str2);
//        System.out.println(result);
//    }
//
//    private static int countLengthOfLCS(String str1, String str2) {
////        int lgth = str1.length(); //아무데서도 둘이 길이가 같다고 한 적 없음..!
//        for (int i = 0; i < str1.length(); i++) {
//            String str1Char = str1.substring(i, i + 1);
//            DP[i] = 0;
//            for (int j = 0; j < str2.length(); j++) {
//                if (str2.substring(j, j + 1).equals(str1Char)) { // 공통 수열 가능
//                    /**
//                     * 직전 공통 수열에 + 1을 한다.
//                     */
//                    String lastLCS = countLengthOfLastLCS(i, j, str1, str2);
//
//                    if (lastLCS == null) {
//                        if (DP[i] == 0) {
//                            DP[i] = 1;
//                            LCS[i] = str1Char;
//                        }
//                        continue;
//                    }
//
//                    int lastLCSLength = lastLCS.length();
//                    if (lastLCSLength + 1 > DP[i]) {
//                        DP[i] = lastLCSLength + 1;
//                        LCS[i] = lastLCS;
//                    }
//                }
//            }
//        }
//
//        return Arrays.stream(DP).max().getAsInt();
//    }
//
//    //지금까지 순차적인 공통 수열을 구하는 것.
//    private static String countLengthOfLastLCS(int i, int j, String str1, String str2) {
//        if (i == 0 || j == 0) {
//            return null;
//        }
//
//        String str2String = str2.substring(0, j);
//
//        int maxLCSLength = -1;
//        StringBuilder maxLCS = new StringBuilder();
//
//        for (int index1 = i - 1; index1 >= 0; index1--) {
//            StringBuilder common = new StringBuilder();
//            for (String s : LCS[index1].split("")) {
//                if (str2String.contains(s)) {
//                    common.append(s);
//                }
//            }
//
//            if (maxLCSLength < common.length()) {
//                maxLCSLength = common.length();
//                maxLCS = common;
//            }
//        }
//
//        if (maxLCSLength == -1) {
//            return null;
//        }
//        return maxLCS.toString();
//    }
}
