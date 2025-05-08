package org.example.dp_가장좋은해;

// https://www.acmicpc.net/problem/11052
// solved Silver 1

import java.util.Scanner;

public class PurchaseCard11052 {
    public static int[] DP;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cardCount = sc.nextInt();
        int[] cardPackFees = new int[cardCount + 1];
        DP = new int[cardCount + 1];

        for (int i = 1; i <= cardCount; i++) {
            cardPackFees[i] = sc.nextInt();
        }

        int maxFee = calculateMaxFee(cardPackFees);
        System.out.println(maxFee);
    }

    private static int calculateMaxFee(int[] cards) {
        DP[1] = cards[1];

        int cardCount = cards.length - 1;
        if (cardCount == 1) {
            return DP[1];
        }

        for (int count = 2; count <= cardCount; count++) {
            int maxFeeByCount = 0;
            for (int i = count; i >= 1; i--) {
                int currFee = DP[count - i] + cards[i];
                maxFeeByCount = Math.max(maxFeeByCount, currFee);
            }
            DP[count] = maxFeeByCount;
        }

        return DP[cardCount];
    }
}
