package org.example.dfsNbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/1260
// Silver2 Solved

public class DfsBfs1260 {
    private static List<Edge> edges;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int vCount = sc.nextInt();
        int eCount = sc.nextInt();
        int startV = sc.nextInt();

        edges = new ArrayList<>();
        for (int i = 0; i < eCount; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt()));
        }

        List<Integer> dfsResult = doDfs(startV, new ArrayList<>(Arrays.asList(startV)));
        for (int r : dfsResult) {
            System.out.print(r + " ");
        }
        System.out.println();

        List<Integer> bfsResult = doBfs(startV, new ArrayList<>());
        for (int r : bfsResult) {
            System.out.print(r + " ");
        }
    }

    private static List<Integer> doDfs(int fromV, List<Integer> visited) {
        List<Edge> movables = getMovableEdges(fromV, visited);

        if (movables.isEmpty()) {
            return visited;
        }

        for (Edge next : movables) {
            int toV = next.toOf(fromV);
            if (visited.contains(toV)) {
                continue;
            }
            visited.add(toV);
            doDfs(toV, visited); //return 아님 -> 중간에 막히면 stop이 아니라 백트래킹이기 때문
        }

        return visited;
    }

    private static List<Integer> doBfs(int startV, List<Integer> visited) {
        Queue<Integer> toGos = new LinkedList<>();
        toGos.add(startV);

        while (!toGos.isEmpty()) {
            int fromV = toGos.poll();
            if (visited.contains(fromV)) {
                continue;
            }
            visited.add(fromV);

            List<Edge> movables = getMovableEdges(fromV, visited);
            for (Edge next : movables) {
                int toV = next.toOf(fromV);
                toGos.add(toV);
            }
        }

        return visited;
    }

    private static List<Edge> getMovableEdges(int fromV, List<Integer> visited) {
        List<Edge> movables = edges.stream()
                .filter(edge -> edge.one == fromV || edge.two == fromV)
                .filter(edge -> !visited.contains(edge.toOf(fromV)))
                .sorted(Comparator.comparingInt(edge -> edge.toOf(fromV)))
                .collect(Collectors.toList());
        return movables;
    }

    public static class Edge {
        int one;
        int two;

        public Edge(int one, int two) {
            this.one = one;
            this.two = two;
        }

        public int toOf(int from) {
            if (from == one) {
                return two;
            }
            return one;
        }
    }
}
