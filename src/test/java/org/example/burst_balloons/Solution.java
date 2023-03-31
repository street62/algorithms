package org.example.burst_balloons;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    int[] nums;
    int[][] cache;

    public int maxCoins(int[] nums) {
        this.cache = new int[nums.length + 2][nums.length + 2];

        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        numList.add(0, 1);
        numList.add(1);

        this.nums = numList.stream().mapToInt(Integer::intValue).toArray();

        for (int[] c : cache) {
            Arrays.fill(c, Integer.MIN_VALUE);
        }

        return dfs(1, numList.size() - 2);
    }

    public int dfs(int left, int right) {
        if (left > right) {
            return 0;
        }

        if (cache[left][right] != Integer.MIN_VALUE) {
            return cache[left][right];
        }

        int res = Integer.MIN_VALUE;

        for (int i = left; i <= right; i++) { // i is gonna be popped at last
            int cur = 0;
            cur += (nums[left - 1] * nums[i] * nums[right + 1]); // compute i, which remain at last
            cur += dfs(left, i - 1) + dfs(i + 1, right); // compute both parts beside i
            res = Math.max(res, cur);
        }

        cache[left][right] = res;
        return res;
    }
}