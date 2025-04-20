package org.example.dataStructure;

//https://www.acmicpc.net/problem/9012
//Silver4 solved

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Gualho9012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int repeatAmount = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < repeatAmount; i++) {
            String line = sc.nextLine();
            if (isVPS(line)) {
                System.out.println("YES");
            } else  {
                System.out.println("NO");
            }
        }
    }

    private static boolean isVPS(String line) {
        Stack<String> stack = new Stack<>();

        String[] lines = line.split("");

        for (String gualho : lines) {
            if (gualho.equals("(")) {
                stack.push(gualho);
            }
            if (gualho.equals(")")) {
                try {
                    String pop = stack.pop();
                    if (pop.equals("(")) {
                        continue;
                    }
                } catch (EmptyStackException e) {
                    return false;
                }
            }
        }

        return stack.empty();
    }
}
