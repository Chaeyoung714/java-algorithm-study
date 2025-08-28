package org.example.doItAlgorithm.정렬.퀵정렬;

// https://www.acmicpc.net/problem/11004
// 시간초과 상태

import java.util.*;
import java.io.*;

/**
 * 200,000,000번, N <= 5,000,000
 * O(n^2) 불가능
 * O(nlogn) = 5,000,000 * 3.3 * 7 ~= 100,000,000정도 -> 가능할듯 -> 분할정복 ㄱㄱ
 */
public class KthNumber11004 {
    /**
     * 대표적인 퀵소트 문제인 것 같다.
     * 수 N개 오름차순 -> 앞에서부터 K번째 수
     * K번째 수까지만 정렬하면 된다 -> k번째가 포함된 그룹만 계속 분할정복하면 된다.
     */

    /**
     * left = 4개, right = 3개
     * if k = 4 : doQuicksort(left, 4)
     * if k = 5 : doQuicksort(right, 1)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int kthNumber = doQuickSort(numbers, k - 1, 0, n - 1); //0에서 시작함을 고려

        System.out.println(kthNumber);
    }

    private static int doQuickSort(int[] numbers, int k, int start, int end) {
        if (start >= end) {
            return numbers[start];
        }

        int pivot = numbers[start];

        int left = start + 1; //start는 이미 left 그룹에 속해있음
        int right = end;

        while (left <= right) {
            if (numbers[left] <= pivot) {
                if (numbers[right] <= pivot) { //right만 잘못된 위치일 때 -> 그냥 넘어감
                    left++;
                } else { //둘다 정상 위치일때
                    left++;
                    right--;
                }
            } else {
                if (numbers[right] <= pivot) { //둘다 잘못된 위치일 때 -> swap
                    int tmp = numbers[left];
                    numbers[left] = numbers[right];
                    numbers[right] = tmp;

                    left++;
                    right--;
                } else {
                    right--; //left만 잘못된 위치일 때 -> 그냥 넘어감
                }
            }
        }

        //pivot은 left자리에 들어가야 함 (left그룹 중에 최대이므로)
        //기존 left : [0 : pivot] [1] [2] .. [left -1] 여기까지/ [left]
        //새 left : [1][2]...[left-1][pivot][left]
        for (int i = start + 1; i < left; i++) {
            numbers[i - 1] = numbers[i];
        }
        numbers[left - 1] = pivot;

        if (k == left - 1) { //틀렸던 이유
            return numbers[left - 1];
        }
        if (k < left - 1) {
            return doQuickSort(numbers, k, start, left - 2);
        }
        return doQuickSort(numbers, k, left, end);
    }

    /**
     * 메모리 초과
     */
//    private static int doQuickSort(int[] numbers, int k, int start, int end) {
//        if (start >= end) {
//            return numbers[start];
//        }
//
//        int pivot = numbers[(start + end) / 2];
//
//        int[] left = new int[end - start + 1];
//        int[] right = new int[end - start + 1];
//        int leftPointer = -1; //++leftPointer를 해야함
//        int rightPointer = -1;
//
//        for (int i = start; i <= end; i++) {
//            if (numbers[i] <= pivot) {
//                left[++leftPointer] = numbers[i];
//            } else {
//                right[++rightPointer] = numbers[i];
//            }
//        }
//
//        if (leftPointer >= k) { //length가 defaultSize 기준이었나?
//            return doQuickSort(left, k, 0, leftPointer);
//        }
//
//        int newK = k - leftPointer - 1; //k = 0 기준 2 leftPointer = 1 -> newK = 0
//        return doQuickSort(right, newK, 0, rightPointer);
//    }
}