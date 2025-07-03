package org.example.dp_LISLCS다차원DP;

// https://www.acmicpc.net/problem/11053
// Referenced : GPT가 모범답안 알려줌

import java.util.Scanner;

public class LIS11063 {
   /**
    * 여기서 DP = **자기 자신을 포함한** LIS 중 최대 길이
     */
   static int[] DP;
   static int[] numbers;

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      int aLength = scanner.nextInt();
      numbers = new int[aLength + 1];
      DP = new int[aLength + 1];
      for (int i = 1; i <= aLength; i++) {
         numbers[i] = scanner.nextInt();
      }

      int lis = calculateLIS();
      System.out.println(lis);
   }

   private static int calculateLIS() {
      int aLength = numbers.length - 1;
      int maxResult = 1;
      for (int i = 1; i <= aLength; i++) {
         DP[i] = 1; //default
         for (int j = 1; j < i; j++) {
            if (numbers[j] < numbers[i]) {
               DP[i] = Math.max(DP[i], DP[j] + 1);
            }
         }
         maxResult = Math.max(maxResult, DP[i]);
      }
      return maxResult;
   }
//
//    private static int calculateLIS() {
//        DP[1] = 1;
//        int aLength = DP.length - 1; //6
//        if (aLength <= 1) {
//            return 1;
//        }
//        for (int i = 2; i <= aLength; i++) {
//            int maxLengthWithoutMyself = countMaxLengthUntil(i - 1);
//            int maxLengthIncludingMyself = countMaxLegthUntilAndSmallerThan(i - 1);
//            DP[i] = Math.max(maxLengthIncludingMyself, maxLengthWithoutMyself);
//        }
//        return DP[aLength];
//    }
//
//    private static int countMaxLegthUntilAndSmallerThan(int limit) { //1
//        int maxLength = -1;
//        for (int i = 1; i <= limit; i++) {
//            if (numbers[i] < numbers[limit + 1]) { //dp1 < dp2
//                maxLength = Math.max(maxLength, DP[i]); //max = 1
//            }
//        }
//        if (maxLength == -1) {
//            return maxLength;
//        }
//        return maxLength + 1; //return 2
//    }
//
//    private static int countMaxLengthUntil(int limit) {
//        int maxLength = -1;
//        for (int i = 1; i <= limit; i++) {
//            maxLength = Math.max(maxLength, DP[i]);
//        }
//        return maxLength;
//
}
