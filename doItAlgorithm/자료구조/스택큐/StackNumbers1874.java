package org.example.doItAlgorithm.자료구조.스택큐;

//https://www.acmicpc.net/problem/1874

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class StackNumbers1874 {
    /**
     * push : 1->N까지 오름차순으로 넣음
     * 중간중간 pop하여 원하는 수열을 만듦
     */
    /**
     * 	초기 stack.push(1)/result.add("+") 제거.
     * 	number(=next)가 n을 초과하면 더 이상 push하지 않도록 로직 변경.
     * 	입력을 역순 스택(target)으로 만들지 말고, 입력 순서대로 처리.
     * 	출력은 StringBuilder로 누적.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int next = 1;
        for (int num : numbers) {
            // x까지 push
            while (next <= num) {
                stack.push(next++);
                sb.append("+\n");
            }
            // 꼭대기가 x여야 pop 가능
            if (stack.peek() == num) {
                stack.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.print(sb);
    }
}
