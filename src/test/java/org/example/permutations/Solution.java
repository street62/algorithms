package org.example.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {
        new Solution().permute(new int[] {1, 2});
    }
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> numsList = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());

        backTrack(numsList);
        return res;
    }

    public List<List<Integer>> backTrack(List<Integer> numsList) {
        System.out.println(numsList);

        List<List<Integer>> res = new ArrayList<>();

        if (numsList.size() == 1) {
            res.add(new ArrayList<>(numsList));
            return res;
        }

        System.out.println("for start");
        for (int i = 0; i < numsList.size(); i++) {
            System.out.println(i);
            int num = numsList.get(0);
            numsList.remove(0);
            List<List<Integer>> perms = backTrack(numsList);
            for (List<Integer> p : perms) {
                p.add(num);
            }
            res.addAll(perms);
            // numsList.add(num);
        }
        System.out.println("for end");


        return res;
    }
}