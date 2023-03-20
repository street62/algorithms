package org.example.상어_초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class v3 {
    private static int n;
    private static Map<Integer, Set<Integer>> favMap = new HashMap<>();
    private static int[][] map;
    private static int[] students;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
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
        for (int s : students) {
            Seat seat = null;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0) {
                        continue;
                    }

                    Seat cur = new Seat(i, j, getFavCount(s, i, j), getEmptyCount(i, j));

                    if (seat == null || cur.compareTo(seat) < 0) {
                        seat = cur;
                    }
                }
            }
            assert seat != null;
            map[seat.x][seat.y] = s;
        }

        getTotal();
    }

    private static void getTotal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int favCount = getFavCount(map[i][j], i, j);
                if (favCount > 0) {
                    res += Math.pow(10, favCount - 1);
                }
            }
        }
    }

    private static int getEmptyCount(int i, int j) {
        int res = 0;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
                continue;
            }

            if (map[nx][ny] == 0) {
                res += 1;
            }
        }
        return res;
    }

    private static int getFavCount(int s, int i, int j) {
        int res = 0;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
                continue;
            }

            if (favMap.get(s).contains(map[nx][ny])) {
                res += 1;
            }
        }
        return res;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        students = new int[n * n];
        favMap = new HashMap<>();

        for (int i = 0; i < n * n; i++) {
            List<Integer> list = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int student = list.get(0);
            HashSet<Integer> fav = new HashSet<>(Set.of(list.get(1), list.get(2), list.get(3), list.get(4)));

            students[i] = student;
            favMap.put(student, fav);
        }
    }

    private static class Seat implements Comparable<Seat> {
        int x;
        int y;
        int favCount;
        int emptyCount;

        public Seat(int x, int y, int favCount, int emptyCount) {
            this.x = x;
            this.y = y;
            this.favCount = favCount;
            this.emptyCount = emptyCount;
        }

        @Override
        public int compareTo(Seat o) {
            if (this.favCount != o.favCount) {
                return o.favCount - this.favCount;
            }
            if (this.emptyCount != o.emptyCount) {
                return o.emptyCount - this.emptyCount;
            }
            if (this.x != o.x) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
    }

}
