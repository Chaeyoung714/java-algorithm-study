package org.example.dp_k번째수;

// https://www.acmicpc.net/problem/1256
// Gold2 referenced https://arinnh.tistory.com/70 & GPT

import java.util.Scanner;

public class Dictionary1256 {
    static long[][] DP;
    static String a = "a";
    static String z = "z";
    static int totalLength;
    static int aCount;
    static int zCount;
    static final long LIMIT = 1_000_000_001L;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        aCount = scanner.nextInt();
        zCount = scanner.nextInt();
        long k = scanner.nextLong();
        totalLength = aCount + zCount;
        DP = new long[aCount + 1][zCount + 1];

        fillDPUntil(aCount, zCount);

        if (DP[aCount][zCount] < k) {
            System.out.println(-1);
        } else {

            String kthString = findKthString(new StringBuilder(), aCount, zCount, k);

            System.out.println(kthString);
        }

    }

    private static void fillDPUntil(int aTotal, int zTotal) {
        /**
         * 초기화
         * aCount나 zCount 중 하나가 0이라면 한가지 방법으로만 만들수 있다
         */
        for (int aCount = 0; aCount <= aTotal; aCount++) {
            DP[aCount][0] = 1;
        }
        for (int zCount = 0; zCount <= zTotal; zCount++) {
            DP[0][zCount] = 1;
        }

        /**
         * DP 채우기
         */
        for (int aCount = 1; aCount <= aTotal; aCount++) {
            for (int zCount = 1; zCount <= zTotal; zCount++) {
//                DP[aCount][zCount] = DP[aCount - 1][zCount] + DP[aCount][zCount - 1];
                DP[aCount][zCount] = Math.min(LIMIT, DP[aCount - 1][zCount] + DP[aCount][zCount - 1]); //Referenced
            }
        }
    }

    private static String findKthString(StringBuilder currentString, int aCount, int zCount, long k) {
        /**
         * 첫문자부터, a로 시작할 때와 b로 시작할 때를 확인한 후 가능한 경우의 수가 k번째를 포함하는 문자로 선택을 한다.
         * ex. 현재 위치가 a면 3~5번째를 커버, 현재 위치가 z면 6~8번째를 커버
         * k = 4라면 a를 선택, k = 7이라면 z를 선택
         * 이후 재귀로 나아감
         */
        if (aCount == 0 && zCount == 0) {
            return currentString.toString();
        }
        if (aCount == 0) {
            return currentString.append(z.repeat(totalLength - currentString.length())).toString();
        }
        if (zCount == 0) {
            return currentString.append(a.repeat(totalLength - currentString.length())).toString();
        }

        // 1. 현재 위치에 a가 붙는다면
        long leftCount = DP[aCount - 1][zCount]; //Referenced
        if (leftCount >= k) {
            return findKthString(currentString.append(a), aCount - 1, zCount, k);
        }
        // 2. 현재 위치에 z가 붙는다면
        return findKthString(currentString.append(z), aCount, zCount - 1, k - leftCount); //k-leftCount : Referenced
    }

    /**
     * sol2 (덜함)
     */
//    static String[] DP;
//    static String a = "a";
//    static String z = "z";
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int aCount = scanner.nextInt();
//        int zCount = scanner.nextInt();
//        int k = scanner.nextInt();
//        DP = new String[k + 1]; //인덱스 1부터 시작
//
//        fillDPUntil(k, aCount, zCount);
//
//        System.out.println(DP[k]);
//    }
//
//    private static void fillDPUntil(int k, int aCount, int zCount) {
//        int totalLength = aCount + zCount;
//        DP[1] = a.repeat(aCount) + z.repeat(zCount);
//
//        for (int i = 2; i <= k; i++) {
//            String prev = DP[i - 1];
//            int firstIndexOfZ = prev.indexOf(z);
//            if (firstIndexOfZ == 0) {
//                //do something
//            }
//
//            int lastIndexOfA = prev.lastIndexOf(a);
//            if (lastIndexOfA < firstIndexOfZ) { //aaa...zzz 상태, 즉 a와 z가 섞이지 않았다면
//                //do something
//            }
//            if (lastIndexOfA == totalLength - 1) { //a가 끝까지 이동했으면
//                //do something
//                int secondLastIndexOfA = prev.substring(0, lastIndexOfA).lastIndexOf(a);
//                int firstZAfterSecondLastIndexOfA = (secondLastIndexOfA + 1)
//                        + prev.substring(secondLastIndexOfA + 1).indexOf(z);
//                DP[i] =
//                if (prev.substring(secondLastIndexOfA + 1, secondLastIndexOfA + 2).equals(z)) { //a 다음 바로 z라면
//                    DP[i] = prev.substring(0, secondLastIndexOfA) + z + a + a
//                            + z.repeat(totalLength - secondLastIndexOfA - 3);
//                } else {
//
//                }
//            }
//            // a가 더 이동할 수 있으면 -> 어차피 lastOfIndexA의 a 다음은 무조건 z임
//            if (lastIndexOfA == totalLength - 2) {
//                DP[i] = prev.substring(0, lastIndexOfA) + z + a;
//                continue;
//            }
//            DP[i] = prev.substring(0, lastIndexOfA) + z + a + prev.substring(lastIndexOfA + 2);
//        }
//    }
}
