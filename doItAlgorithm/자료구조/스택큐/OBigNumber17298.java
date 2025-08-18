package org.example.doItAlgorithm.자료구조.스택큐;

// https://www.acmicpc.net/problem/17298
// referenced: 두잇 알고리즘

import java.io.*;
import java.util.*;

/**
 * 100,000,000번, N <= 1,000,000
 * O(n^2) 불가능,  O(nlogn) = 1,000,000 * 6 * 3.3 ~= 20,000,000 -> 가능
 */
public class OBigNumber17298 {
    /**
     *  Ai의 오른쪽에 있으면서 Ai보다 큰 수 중 가장 왼쪽에 있는 수, 없으면 -1
     *  왼쪽부터 하나식 빼보다가, 자기자신보다 더 큰 수가 나오면 바로 반환 => O(nlogn)~O(n^2) 걸칠듯
     *  Idea) 비교해야 하는 수를 기준으로 돌지말고, 오큰수를 기준으로 돌자
     *  => 스택에 아직 비교가 필요한 이전 수들을 저장할 수 있음
     *  => 스택에 있는 수들은 top에서부터 오름차순일 수밖에 없음. -> top의 숫자가 오큰수보다 크다면, 나머지 수들도 오큰수보다 크므로 확인할 필요 없음
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i); //인덱스를 넣어야 result 값에 적당한 인덱스를 찾아갈 수 있음
                continue;
            }

            int oNumber = numbers[i];
            while (!stack.isEmpty() && numbers[stack.peek()] < oNumber) { //while문을 여러번 돌때를 대비해 isEmpty 체크 해야함
                result[stack.pop()] = oNumber;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        /*

        꼭 이거 쓰자... 이것 땜에 시간초과 남 ㅡㅡ
         */
        StringBuilder sb = new StringBuilder(n * 3);
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.println(sb);

        /**
         * 시간초
         * O(nlogn)정도 되어보이는데?
         */
//        for (int i = 0; i < n; i++) {
//            if (i == n - 1) {
//                result[i] = -1;
//                break;
//            }
//            int target = numbers[i];
//            for (int j = i + 1; j < n; j++) {
//                if (target < numbers[j]) {
//                    result[i] = numbers[j];
//                    break;
//                }
//                if (j == n - 1) {
//                    result[i] = -1;
//                    break;
//                }
//            }
//        }
    }
}
