package org.example.multiply_strings;

class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] res = new int[num1.length() + num2.length()];
        char[] n1 = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] n2 = new StringBuilder(num2).reverse().toString().toCharArray();

        for (int i = 0; i < n1.length; i++) {
            for (int j = 0; j < n2.length; j++) {
                int temp = (n1[i] - '0') * (n2[j] - '0');
                res[i + j] += temp;
                res[i + j + 1] += (res[i + j] / 10);
                res[i + j] = (res[i + j] % 10);
            }
        }

        int zeroIndex = res.length - 1;

        while (res[zeroIndex] == 0) {
            zeroIndex -= 1;
        }

        StringBuilder resBuilder = new StringBuilder();
        for (int i = zeroIndex; i >= 0; i--) {
            resBuilder.append(res[i]);
        }

        return resBuilder.toString();
    }
}