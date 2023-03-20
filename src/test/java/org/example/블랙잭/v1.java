package org.example.블랙잭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class v1 {
    private static int N;
    private static int max;
    private static List<Integer> cards;
    private static int res = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        print();
    }

    private static void print() {
        System.out.println(res);
    }

    private static void solution() {
        backtrack(0, new Stack<>(), 0);
    }

    private static void backtrack(int index, Stack<Integer> cur, int sum) {
        if (cur.size() == 3) {
            if (sum <= max && sum > res) {
                res = sum;
                return;
            }
        }
        if (index == N) {
            return;
        }

        cur.push(cards.get(index));
        backtrack(index + 1, cur,sum + cards.get(index));
        cur.pop();
        backtrack(index + 1, cur, sum);
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
    }
}
