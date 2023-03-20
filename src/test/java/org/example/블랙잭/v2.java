package org.example.블랙잭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class v2 {
    private static int N;
    private static int max;
    private static List<Integer> cards;
    private static int res = Integer.MIN_VALUE;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        print();
    }

    private static void print() {
        System.out.println(res);
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < N; k++) {
                    if (i == k || j == k) {
                        continue;
                    }
                    int sum = cards.get(i) + cards.get(j) + cards.get(k);
                    if (sum <= max && sum > res) {
                        res = sum;
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> first = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        N = first.get(0);
        max = first.get(1);
        cards = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        cache = new int[max + 1];
    }
}
