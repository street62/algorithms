package org.example.minimum_window_substring;

class Solution {
    private int[] sArr = new int[52];
    private int[] tArr = new int[52];
    private String res = "";

    public String minWindow(String s, String t) {
        for (char c : t.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tArr[c - 'A'] += 1;
            } else {
                tArr[c - 'a' + 26] += 1;
            }
        }

        
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            sArr[getIndex(s.charAt(end))] += 1;

            while (start < s.length() &&
             (sArr[getIndex(s.charAt(start))] >  tArr[getIndex(s.charAt(start))])) {
                sArr[getIndex(s.charAt(start))] -= 1;
                start += 1;
            }

            if (getMatches() == 52 && (res.length() > end - start + 1 || res.equals(""))) {
                res = s.substring(start, end + 1);
            }
        }

        return res;
    }

    public int getIndex(char c) {
        if (Character.isUpperCase(c)) {
            return c - 'A';
        } else {
            return 26 + c - 'a';
        }
    }

    public int getMatches() {
        int matches = 0;

        for (int i = 0; i < tArr.length; i++) {
            if (sArr[i] >= tArr[i]) {
                matches += 1;
            }
        }

        return matches;
    }
}