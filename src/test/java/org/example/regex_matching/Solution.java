package org.example.regex_matching;

import java.util.Arrays;

class Solution {
    char[] s;
    char[] p;
    int[][] cache;

    public boolean isMatch(String s, String p) {
        this.s = s.toCharArray();
        this.p = p.toCharArray();
        this.cache = new int[s.length() + 1][p.length() + 1];

        for (int[] c : cache) {
            Arrays.fill(c, Integer.MIN_VALUE);
        }

        return dfs(0, 0) == 1;
    }

    public int dfs(int sIdx, int pIdx) {
        // System.out.printf("(%d, %d)\n", sIdx, pIdx);
        if (sIdx >= s.length && pIdx >= p.length) {
            return 1;
        }
        if (pIdx >= p.length) {
            return 0;
        }

        if (cache[sIdx][pIdx] != Integer.MIN_VALUE) {
            return cache[sIdx][pIdx];
        }

        int res = 0;
        boolean isMatch = sIdx < s.length && (s[sIdx] == p[pIdx] || p[pIdx] == '.');
        boolean isThisStar = pIdx + 1 < p.length && p[pIdx + 1] == '*';

        if (isThisStar) {
            if (isMatch) {
                res = dfs(sIdx + 1, pIdx);
            }
            res = res | dfs(sIdx, pIdx + 2);
        }
        else if (isMatch) {
            res = dfs(sIdx + 1, pIdx + 1);
        }

        cache[sIdx][pIdx] = res;
        
        return res;
    }
}