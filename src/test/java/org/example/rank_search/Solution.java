package org.example.rank_search;

import java.util.*;

class Solution {
    class Tree {
        List<Integer> scores = new ArrayList<>();
        Map<String, Tree> map = new HashMap<>();
        
        @Override
        public String toString() {
            return String.format("%s\n%s\n", map, scores);
        }
        
        void insert(String info) {
            String[] split = info.split(" ");
            Tree cur = this;
            int col = 0;
            
            while (col <= 4) {
                if (col == 4) {
                    cur.scores.add(Integer.parseInt(split[col]));
                    break;
                }

                if (!cur.map.containsKey(split[col])) {
                    cur.map.put(split[col], new Tree());
                }
                cur = cur.map.get(split[col]);
                col += 1;
            }
        }
        
        int search(String query) {
            // System.out.println(query);
            String[] split = query.split(" and | ");
            int index = 0;
            return getCount(this, split, 0);
        }
        
        int getCount(Tree cur, String[] q, int index) {
            if (cur == null) {
                return 0;
            }
            
            if (index == 4) {
                return getStudents(cur.scores, Integer.parseInt(q[4]));
            }
            
            if (q[index].equals("-")) {
                int res = 0;
                for (Tree t : cur.map.values()) {
                    res += getCount(t, q, index + 1);
                }
                return res;
            }
            return getCount(cur.map.get(q[index]), q, index + 1);
        }
        
        int getStudents(List<Integer> scores, int score) {
            int res = 0;
            for (int s : scores) {
                if (s >= score) {
                    res += 1;
                }
            }
            
            return res;
        }
    }
    public int[] solution(String[] info, String[] query) {
        Tree root = new Tree();
        int[] res = new int[query.length];
        
        for (String i : info) {
            root.insert(i);
        }

        for (int i = 0; i < query.length; i++) {
            res[i] = root.search(query[i]);
        }
        
        return res;
    }
}