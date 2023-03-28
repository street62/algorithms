package org.example.largest_rectangle_in_histogram;

import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<int[]> stack = new Stack<>(); // [index, height]
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int index = i;
            while (stack.size() > 0 && stack.peek()[1] > heights[i]) {
                int[] last = stack.pop();
                maxArea = Math.max(maxArea, last[1] * (i - last[0]));
                index = last[0];
            }
            stack.push(new int[] {index, heights[i]});
        }

        int lastIndex = heights.length - 1;
        while (stack.size() > 0) {
            int[] last = stack.pop();
            maxArea = Math.max(maxArea, last[1] * (lastIndex - last[0] + 1));
        }

        return maxArea;
    }
}