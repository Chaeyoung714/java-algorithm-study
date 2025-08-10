package org.example.doItAlgorithm.자료구조.슬라이딩윈도우;

// https://www.acmicpc.net/problem/12891

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 최대 200,000,000, n <= 1,000,000
 * O(n^2) -> 불가능
 * O(nlogn) = 1,000,000 * 6 * 3.3 = 20,000,000 -> 가능
 */
public class DnaPassword12891 {
    /**
     * DNA 문자열 = 모든 문자열에 등장하는 문자가 ACGT에 속하는 문자열 중 부분 문자열
     * 부분 = 연속된 일부분임
     * && 속하는 문자가 특정 개수 이상이어야 함
     * 만들 수 있는 비밀번호 종류의 수
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        int dnaLength = Integer.parseInt(st.nextToken());
        int passwordLength = Integer.parseInt(st.nextToken());

        String[] dna = bufferedReader.readLine().split("");

        st = new StringTokenizer(bufferedReader.readLine());

        int aMinCount = Integer.parseInt(st.nextToken());
        int cMinCount = Integer.parseInt(st.nextToken());
        int gMinCount = Integer.parseInt(st.nextToken());
        int tMinCount = Integer.parseInt(st.nextToken());

        int count = 0;

        int aCount = 0;
        int cCount = 0;
        int gCount = 0;
        int tCount = 0;
        for (int i = 0; i < passwordLength; i++) {
            String str = dna[i];
            if (str.equals("A")) {
                aCount++;
            } else if (str.equals("C")) {
                cCount++;
            } else if (str.equals("G")) {
                gCount++;
            } else if (str.equals("T")) {
                tCount++;
            }
        }

        /**
         * 이부분 잊어버리지 말기
         */
        if (aCount >= aMinCount
                && cCount >= cMinCount
                && gCount >= gMinCount
                && tCount >= tMinCount
        ) {
            count++;
        }

        for (int start = 1; start < dnaLength; start++) {
            int end = start + passwordLength - 1;

            if (end >= dnaLength) {
                break;
            }

            String missingString = dna[start - 1];
            if (missingString.equals("A")) {
                aCount--;
            } else if (missingString.equals("C")) {
                cCount--;
            } else if (missingString.equals("G")) {
                gCount--;
            } else if (missingString.equals("T")) {
                tCount--;
            }

            String addingString = dna[end];
            if (addingString.equals("A")) {
                aCount++;
            } else if (addingString.equals("C")) {
                cCount++;
            } else if (addingString.equals("G")) {
                gCount++;
            } else if (addingString.equals("T")) {
                tCount++;
            }

            if (aCount < aMinCount
                    || cCount < cMinCount
                    || gCount < gMinCount
                    || tCount < tMinCount
            ) {
                continue;
            }
            count++;
        }

        System.out.println(count);
    }
}
