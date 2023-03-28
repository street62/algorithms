package org.example.car_fleet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        List<List<Integer>> pairs = new ArrayList<>(); // (pos, speed)

        for (int i = 0; i < position.length; i++) {
            pairs.add(List.of(position[i], speed[i]));
        }
        Collections.sort(pairs, (p1, p2) -> p2.get(0) - p1.get(0));
        Stack<Double> stack = new Stack<>();

        for (List<Integer> p : pairs) {
            double timeToDst = (double)(target - p.get(0)) / (double) p.get(1);
            if (stack.size() > 0 && stack.peek() >= timeToDst) {
                continue;
            }
            stack.push(timeToDst);
        }
        return stack.size();
    }
}