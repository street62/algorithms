package org.example.인구_이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class v1 {
    private static int N;
    private static int minDiff;
    private static int maxDiff;
    private static int[][] map;

    private static Set<List<Integer>> visit = new HashSet<>();
    private static Set<List<Integer>> union = new HashSet<>();
    private static int unionSum;

    private static boolean isMoved;
    private static int res;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        print();
    }

    private static void print() {
        System.out.println(res);
    }

    private static void solution() {
        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    search(i, j);
                }
            }
            if (isMoved) {
                res += 1;
            } else {
                break;
            }
            visit.clear();
            isMoved = false;
        }
    }

    private static void search(int x, int y) {
        bfs(x, y, map[x][y] - minDiff);

        if (union.size() == 0) {
            return;
        }

        updatePopulation(unionSum / union.size());
        clearUnion();
    }

    private static void bfs(int x, int y, int prev) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return;
        }
        int diff = Math.abs(map[x][y] - prev);
        if (diff < minDiff || diff > maxDiff) {
            return;
        }

        List<Integer> position = List.of(x, y);

        if (visit.contains(position)) {
            return;
        }

        visit.add(position);
        union.add(position);
        unionSum += map[x][y];

        bfs(x + 1, y, map[x][y]);
        bfs(x - 1, y, map[x][y]);
        bfs(x, y + 1, map[x][y]);
        bfs(x, y - 1, map[x][y]);

        int[] nums = {1, 2, 3};
        List<Integer> collect = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
    }

    private static void clearUnion() {
        union.clear();
        unionSum = 0;
    }

    private static void updatePopulation(int newPopulation) {
        if (union.size() == 1) {
            return;
        }

        isMoved = true;

        for (List<Integer> u : union) {
            map[u.get(0)][u.get(1)] = newPopulation;
        };
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> firstLine = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        N = firstLine.get(0);
        minDiff = firstLine.get(1);
        maxDiff = firstLine.get(2);

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            List<Integer> line = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int j = 0; j < N; j++) {
                map[i][j] = line.get(j);
            }
        }
    }
}
