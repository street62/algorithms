package org.example.permutation_in_string;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] a1 = new int[128];
        int[] a2 = new int[128];

        for (int i = 0; i < s1.length(); i++) {
            a1[s1.charAt(i)] += 1;
            a2[s2.charAt(i)] += 1;
        }

        int matches = 0;

        for (int i = 0; i < 128; i++) {
            if (a1[i] == a2[i]) {
                matches += 1;
            }
        }

        int start = 0, end = s1.length();

        while (end < s2.length()) {
            if (matches == 128) {
                return true;
            }

            char sChar = s2.charAt(start);
            char eChar = s2.charAt(end);

            a2[eChar] += 1;
            if (a1[eChar] == a2[eChar]) {
                matches += 1;
            } else if (a1[eChar] + 1 == a2[eChar]) {
                matches -= 1;
            }

            a2[sChar] -= 1;
            if (a1[sChar] == a2[sChar]) {
                matches += 1;
            } else if (a1[sChar] - 1 == a2[sChar]) {
                matches -= 1;
            }

            start += 1;
            end += 1;
        }

        return matches == 128;
    }
}