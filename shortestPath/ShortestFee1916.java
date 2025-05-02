package org.example.shortestPath;

//https://www.acmicpc.net/problem/1916
// Gold5 referenced : https://loosie.tistory.com/166
//TODO : 입력 bufferedreader 학습하기
//TODO : DP + 그리디 학습 후 다시 보기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ShortestFee1916 {
    /**
     * sol2 : 다익스트라
     * dp + 우선순위 queue + 방문 체크 (그리디 알고리즘 + DP)
     */
    static List<Node>[] list; //list[i]에서 갈 수 있는 도시 목록
    static int[] dp; //출발지에서 dp[i]까지 가는 최소 경로
    static boolean[] check;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        dp = new int[n+1];
        check = new boolean[n+1];

        for(int i=1; i<n+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to,cost));
        }

        st = new StringTokenizer(br.readLine());
        int start= Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        dijkstra(start);
        System.out.println(dp[destination]);
    }

    private static void dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>(); //우선순위 큐 사용
        Arrays.fill(dp, Integer.MAX_VALUE);

        queue.add(new Node(start, 0));
        dp[start] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            /**
             * 우선순위 큐의 poll = 존재하는 것 중 가장 비용이 작은 것
             * 우선순위 큐를 쓰는 이유: 최소 비용이 확정된 노드는 재방문하지 않기 때문
             * -> 비용이 가장 작은 것부터 탐색해야 한번만 방문해도 최소 비용을 알 수 있음
             */
            int current = node.to;

            if (check[current]) continue; //방문한 곳은 다시 방문하지 않음

            check[node.to] = true;
            for (Node next : list[current]) {
                if(dp[next.to] >= dp[current] + next.cost) {
                    //현재 가는 경로의 비용이 지금까지 구한 비용 중 최소라면 진행, 아니면 멈춤
                    dp[next.to] = dp[current] + next.cost;
                    queue.add(new Node(next.to, dp[next.to]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    /**
     * sol1) DFS
     * 모든 경로를 매번 탐색 -> 시간 복잡도가 비효율
     */
//    static List<Bus> buses = new ArrayList<>();
//    static int startCity = 0;
//    static int endCity = 0;
//    static int finalMinFee = Integer.MAX_VALUE; //study
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = null;
//
//        int cityCount = Integer.parseInt(br.readLine());
//        int busCount = Integer.parseInt(br.readLine());
//
//        for (int i = 0; i < busCount; i++) {
//            st = new StringTokenizer(br.readLine());
//            int fromCity = Integer.parseInt(st.nextToken());
//            int toCity = Integer.parseInt(st.nextToken());
//            int busFee = Integer.parseInt(st.nextToken());
//
//            buses.add(new Bus(fromCity, toCity, busFee));
//        }
//
//        st = new StringTokenizer(br.readLine());
//        startCity = Integer.parseInt(st.nextToken());
//        endCity = Integer.parseInt(st.nextToken());
//
//        countMinFee(startCity, 0);
//        System.out.println(finalMinFee);
//    }
//
//    public static int countMinFee(int fromCity,int currentFee) {
//        if (currentFee > finalMinFee) {
//            return -1; //이미 최소 비용은 실패함, 더 진행할 필요 없음
//        }
//
//        if (fromCity == endCity) {
//            return currentFee; //목적지 도착
//        }
//
//        List<Bus> availableBuses = buses.stream()
//                .filter(bus -> bus.fromCity == fromCity && bus.toCity <= endCity)
//                .collect(Collectors.toList());
//
//        if (availableBuses.isEmpty()) {
//            return -1; // 이 경로로는 목적지까지 이동 불가
//        }
//
//        for (Bus bus : availableBuses) {
//            int currentMinFee = countMinFee(bus.toCity, currentFee + bus.fee);
//            if (currentMinFee == -1) {
//                continue;
//            }
//            if (currentMinFee < finalMinFee) {
//                finalMinFee = currentMinFee;
//            }
//        }
//
//        return -1; // 경로 계산 끝, 이 경로는 계산 완료임
//    }
//
//    public static class Bus {
//        int fromCity;
//        int toCity;
//        int fee;
//
//        public Bus(int fromCity, int toCity, int fee) {
//            this.fromCity = fromCity;
//            this.toCity = toCity;
//            this.fee = fee;
//        }
//    }
}
