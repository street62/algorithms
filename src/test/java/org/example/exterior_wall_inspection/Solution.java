package org.example.exterior_wall_inspection;

import java.util.*;

class Solution {
    int n;
    int[] weak;
    int[] dist;
    int minCount = Integer.MAX_VALUE;
    
    void solve(int cnt, int startIndex, int visit) {
        if (cnt > dist.length || cnt >= minCount) {
            return;
        }
        
        for (int i = 0; i < weak.length; i++) {
            int nextIndex = (startIndex + i) % weak.length;
            
            int diff = weak[nextIndex] - weak[startIndex];
            if (nextIndex < startIndex) { // 0을 넘어 회전함
                diff += n;
            }
            
            if (diff <= dist[dist.length - cnt]) { // 현재 개수에서 가장 빠른 사람부터 사용
                visit |= 1 << nextIndex;
            } else {
                break;
            }
        }
        
        if (visit == ((1 << weak.length) - 1)) {
            minCount = Math.min(minCount, cnt);
        } else {
            for (int i = 0; i < weak.length; i++) {
                if (((visit >> i) & 1) == 1) {
                    continue;
                }
                solve(cnt + 1, i, visit);
            }
        }
    }
    
    
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        
        Arrays.sort(dist);
        
        for (int i = 0; i < weak.length; i++) {
            solve(1, i, 0);
        }
        
        return (minCount != Integer.MAX_VALUE) ? minCount : -1;
    }
}