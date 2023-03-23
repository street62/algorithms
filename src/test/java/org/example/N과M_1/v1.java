package org.example.N과M_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class v1 {
    private static int totalNums;
    private static int selectLimit;
    private static int[] res;
    private static boolean[] visit;


    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            sb.append(res[i]);
            if (i != res.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }

    private static void solution() {
        dfs(1);
    }

    private static void dfs(int depth) {
        if (depth == selectLimit + 1) {
            print();
            return;
        }

        for (int i = 1; i <= totalNums; i++) {
            if (!visit[i]) {
                res[depth - 1] = i;
                visit[i] = true;
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        totalNums = Integer.parseInt(s[0]);
        selectLimit = Integer.parseInt(s[1]);
        visit = new boolean[totalNums + 1];
        res = new int[selectLimit];

    }


}
