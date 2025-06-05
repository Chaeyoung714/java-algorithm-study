package org.example.graph;

// https://www.acmicpc.net/problem/11724
// Solved silver2
// TODO sol2로 풀었더니 계속 틀림, 뭐가 틀린지 모름 -> 양방향 간선으로 시도해보기!

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ConnectedComponent11724 {

    /**
     * solved
     * 6 6
     * 1 2
     * 1 5
     * 2 5
     * 3 4
     * 5 6
     * 3 5
     * -> 2로 뜸
     */

    // sol2 : 정석으로 풀기 - DFS
    // https://whitetigerlouis.tistory.com/6

    static Map<Integer, Set<Integer>> edges;
    static int[] visited;
    static int componentCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int eCount = sc.nextInt();
        int vCount = sc.nextInt();

        edges = new HashMap<>();
        visited = new int[eCount + 1];

        for (int e = 1; e <= eCount; e++) {
            edges.put(e, new HashSet<>());
        }

        for (int v = 0; v < vCount; v++) {
            int e1 = sc.nextInt();
            int e2 = sc.nextInt();
            edges.get(Math.min(e1, e2)).add(Math.max(e1, e2));
        }

        countComponentCount();
        System.out.println(componentCount);
    }

    private static void countComponentCount() {
        for (int e = 1; e <= edges.keySet().size(); e++) {
            dfs(e, true);
        }
    }

    private static void dfs(int fromEdge, boolean isStartOfComponent) {
        Set<Integer> toEdges = edges.get(fromEdge);

        boolean isConnectedToExistingComponent = false;

        if (visited[fromEdge] == 0) {
            if (isStartOfComponent && toEdges.isEmpty()) { // 앞으로도 연결될 일 없음 -> 고립된 하나의 정점일 때
                componentCount++;
                visited[fromEdge] = 1;
                return;
            }
        } else { //현재까지 연결된 적 있음
            isConnectedToExistingComponent = true;
        }

        visited[fromEdge] = 1;

        if (isStartOfComponent && !isConnectedToExistingComponent) {
            for (int toEdge : toEdges) {
                if (visited[toEdge] == 1) {
                    isConnectedToExistingComponent = true;
                }
            }
            if (!isConnectedToExistingComponent) { //현재까지 방문한 적 없고, 연결된 점들도 다 방문한 적 없는 점들일때
                componentCount++;
            }
        }

        for (int toEdge : toEdges) {
            if (visited[toEdge] == 1) {
                continue;
            }
            dfs(toEdge, false);
        }
    }

//    static List<Set<Integer>> components = new ArrayList<>();;
//    static Set<Integer> edgesWithVertex = new HashSet<>();;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int eCount = sc.nextInt(); // 간선이 아예 없는 점도 고려해야함
//        int vCount = sc.nextInt();
//
//        for (int v = 0; v < vCount; v++) {
//            int e1 = sc.nextInt();
//            int e2 = sc.nextInt();
//            updateComponents(e1, e2);
//        }
//
//        int componentCount = components.size();
//        int edgesWithoutVertexCount = eCount - edgesWithVertex.size();
//
//        System.out.println(componentCount + edgesWithoutVertexCount);
//    }
//
//    private static void updateComponents(int e1, int e2) {
//        boolean containsE1 = false;
//        int e1ComponentIndex = -1;
//        boolean containsE2 = false;
//        int e2ComponentIndex = -1;
//
//        for (int index = 0; index < components.size(); index++) {
//            Set<Integer> component = components.get(index);
//
//            if (containsE1 && containsE2) {
//                break;
//            }
//            if (component.contains(e1)) {
//                containsE1 = true;
//                e1ComponentIndex = index;
//            }
//            if (component.contains(e2)) {
//                containsE2 = true;
//                e2ComponentIndex = index;
//            }
//        }
//
//        if (containsE1 && !containsE2) {
//            components.get(e1ComponentIndex).add(e2);
//        }
//        if (!containsE1 && containsE2) {
//            components.get(e2ComponentIndex).add(e1);
//        }
//        if (containsE1 && containsE2) {
//            if (e1ComponentIndex != e2ComponentIndex) {
//                components.get(e1ComponentIndex).addAll(components.get(e2ComponentIndex));
//                components.remove(e2ComponentIndex);
//            }
//        }
//        if (!containsE1 && !containsE2) {
//            components.add(new HashSet<>(Arrays.asList(e1, e2)));
//        }
//
//        edgesWithVertex.add(e1);
//        edgesWithVertex.add(e2);
//    }
}
