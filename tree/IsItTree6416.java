package org.example.tree;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

//https://www.acmicpc.net/problem/6416
//unsolved

public class IsItTree6416 {
    public static void main(String[] args) {
        IsItTree6416 question = new IsItTree6416();
        question.start();
    }

    private void start() {
        Scanner sc = new Scanner(System.in);

        int caseCount = 0;
        while (true) {
            caseCount++;

            String s1 = sc.nextLine();
            int startNode = Integer.parseInt(s1);

            String s2 = sc.nextLine();
            int endNode = Integer.parseInt(s2);

            Map<Integer, List<Integer>> nodes = new HashMap<>();
            if (startNode == -1 && endNode == -1) {
                break;
            }
            if (startNode == 0 && endNode == 0) {
                // 트리인지 판별한다.
                if (isTree(nodes)) {
                    System.out.printf("Case %d is a tree.%n", caseCount);
                } else {
                    System.out.printf("Case %d is not a tree.%n", caseCount);
                }
                nodes = new HashMap<>();
                continue;
            }

            //트리에 추가한다.
            if (nodes.containsKey(startNode)) {
                nodes.get(startNode).add(endNode);
            } else {
                nodes.put(startNode, Arrays.asList(endNode));
            }
        }
    }

    private boolean isTree(Map<Integer, List<Integer>> nodes) {
        try {
            Node currentNode = findRoot(nodes);
            search(currentNode, nodes);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private Node findRoot(Map<Integer, List<Integer>> nodes) {
        List<Node> roots = new ArrayList<>();
        for (int startNode : nodes.keySet()) {
            if (countByEndNode(startNode, nodes) == 0) {
                roots.add(new Node(startNode, nodes.get(startNode)));
            }
        }
        if (roots.size() != 1) {
            throw new IllegalArgumentException();
        }
        return roots.get(0);
    }

    private void search (Node currentNode, Map<Integer, List<Integer>> nodes) {
        for (int endNode : currentNode.endNodes) {
            if (countByEndNode(endNode, nodes) > 1) {
                throw new IllegalArgumentException();
            }
            Node nextNode = findByStartNode(endNode, nodes);
            if (nextNode == null) {
                continue;
            }
            search(nextNode, nodes);
        }
    }

    private Node findByStartNode(int node, Map<Integer, List<Integer>> nodes) {
        for (int startNode : nodes.keySet()) {
            if (startNode == node) {
                return new Node(startNode, nodes.get(startNode));
            }
        }
        return null;
    }

    private int countByEndNode(int node, Map<Integer, List<Integer>> nodes) {
        int count = 0;
        for (int startNode : nodes.keySet()) {
            for (int endNode : nodes.get(startNode)) {
                if (endNode == node) {
                    count++;
                }
            }
        }
        return count;
    }

    private class Node {
        int startNode;
        List<Integer> endNodes;

        public Node(int startNode, List<Integer> endNodes) {
            this.startNode = startNode;
            this.endNodes = endNodes;
        }
    }
}
