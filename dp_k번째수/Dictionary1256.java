package org.example.dp_k번째수;

// https://www.acmicpc.net/problem/1256

import java.util.Scanner;

public class Dictionary1256 {
    static String[] DP;
    static String a = "a";
    static String z = "z";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int aCount = scanner.nextInt();
        int zCount = scanner.nextInt();
        int k = scanner.nextInt();
        DP = new String[k + 1]; //인덱스 1부터 시작

        fillDPUntil(k, aCount, zCount);

        System.out.println(DP[k]);
    }

    private static void fillDPUntil(int k, int aCount, int zCount) {
        int totalLength = aCount + zCount;
        DP[1] = a.repeat(aCount) + z.repeat(zCount);

        for (int i = 2; i <= k; i++) {
            String prev = DP[i - 1];
            int firstIndexOfZ = prev.indexOf(z);
            if (firstIndexOfZ == 0) {
                //do something
            }

            int lastIndexOfA = prev.lastIndexOf(a);
            if (lastIndexOfA < firstIndexOfZ) {
                //do something
            }
            if (lastIndexOfA == totalLength - 1) { //a가 끝까지 이동했으면
                //do something
                int secondLastIndexOfA = prev.substring(0, lastIndexOfA).lastIndexOf(a);
                if (prev.substring(secondLastIndexOfA + 1, secondLastIndexOfA + 2).equals(z)) { //a 다음 바로 z라면
                    DP[i] = prev.substring(0, secondLastIndexOfA) + z + a + a
                            + z.repeat(totalLength - secondLastIndexOfA - 3);
                } else {

                }
            }
            // a가 더 이동할 수 있으면
            DP[i] = prev.substring(0, lastIndexOfA) + z + a + prev.substring(lastIndexOfA + 2);//항상 z가 있을지에 대한 고민은 해야됨

        }
    }
}
