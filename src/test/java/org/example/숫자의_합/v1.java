package org.example.숫자의_합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class v1 {

    public static void main(String[] args) throws IOException {
        int res = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        for (char c : second.toCharArray()) {
            res += (int) (c - 48);
        }

        System.out.println(res);
    }
}
