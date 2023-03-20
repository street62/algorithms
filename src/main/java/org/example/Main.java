package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static int[][] grid;
    private static Map<Integer, Set<Integer>> favMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        grid = new int[n][n];

        String line = reader.readLine();

        for (int i = 0; i < (n * n); i++) {
            List<Integer> list = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int student = list.get(0);
            HashSet<Integer> fav = new HashSet<>(List.of(list.get(1), list.get(2), list.get(3), list.get(4)));

            favMap.put(student, fav);
            line = reader.readLine();
        }

        System.out.println(favMap);

        System.out.println(solution(n));
    }

    public static int solution(int n) throws IOException {

        for (Map.Entry<Integer, Set<Integer>> e : favMap.entrySet()) {
            int student = e.getKey();
            Set<Integer> fav = e.getValue();

            int curX = 0;
            int curY = 0;
            int favCount = 0;
            int emptyAdj = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != 0) {
                        continue;
                    }

                    int tempFavCount =
                            isFav(i + 1, j, fav) +
                                    isFav(i - 1, j, fav) +
                                    isFav(i, j + 1, fav) +
                                    isFav(i, j - 1, fav);

                    int tempEmptyAdj =
                            isEmpty(i + 1, j) +
                                    isEmpty(i - 1, j) +
                                    isEmpty(i, j + 1) +
                                    isEmpty(i, j - 1);

                    if (tempFavCount > favCount) {
                        curX = i;
                        curY = j;
                        favCount = tempFavCount;
                        emptyAdj = tempEmptyAdj;
                        continue;
                    }

                    if (tempFavCount == favCount) {
                        if (tempEmptyAdj > emptyAdj) {
                            curX = i;
                            curY = j;
                            emptyAdj = tempEmptyAdj;
                        }
                    }
                }
            }
            grid[curX][curY] = student;
        }

        for (int[] ints : grid) {
            System.out.println(Arrays.toString(ints));
        }

        return getTotalSatisfaction();
    }

    private static int getTotalSatisfaction() {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Set<Integer> fav = favMap.get(grid[i][j]);
                int favCount = isFav(i + 1, j, fav) + isFav(i - 1, j, fav) + isFav(i, j + 1, fav) + isFav(i, j - 1, fav);
                if (favCount > 0) {
                    res += Math.pow(10, favCount - 1);
                }
            }
        }
        return res;
    }

    private static int isEmpty(int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }

        if (grid[i][j] == 0) {
            return 1;
        }
        return 0;
    }

    private static int isFav(int i, int j, Set<Integer> fav) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }

        if (fav.contains(grid[i][j])) {
            return 1;
        }

        return 0;
    }
}