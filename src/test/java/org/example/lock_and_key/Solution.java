package org.example.lock_and_key;

import java.util.*;

class Solution {
    int N;
    int M;
    int[][] lockBoard;
    int[][] keyBoard;
    
    int[][] key;
    int[][] lock;
    
    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        this.N = lock.length;
        this.M = key.length;
        this.lockBoard = new int[N * 3][N * 3];
        this.keyBoard = new int[N * 3][N * 3];
        
        for (int i = 0; i < N; i++) {
            System.arraycopy(lock[i], 0, lockBoard[N + i], N, N);
        }
        
        for (int i = 0; i < 4; i++) {
            if (checkMatch()) {
                return true;
            }
            rotateKey();
        }
        
        return false;
    }
    
    public boolean checkMatch() {
        for (int r = 0; r <= N * 2; r++) {
            for (int c = 0; c <= N * 2; c++) {
                placeKey(r, c);
                if (check()) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void placeKey(int r, int c) {
        clearKeyBoard();
        for (int i = 0; i < M; i++) {
            System.arraycopy(key[i], 0, keyBoard[r + i], c, key.length);
        }
    }
    
    public void clearKeyBoard() {
        for (int[] k : keyBoard) {
            Arrays.fill(k, 0);
        }
    }
    
    public boolean check() {   
        for (int r = N; r < 2 * N; r++) {
            for (int c = N; c < 2 * N; c++) {
                if (lockBoard[r][c] + keyBoard[r][c] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void rotateKey() {
        int left = 0, right = M - 1;
        
        while (left < right) {
            int top = left, bottom = right;
        
            for (int i = 0; i < right - left; i++) {
                int topLeft = key[top][left + i];
                
                // top left <- bottom left
                key[top][left + i] = key[bottom - i][left];
                
                // bottom left <- bottom right
                key[bottom - i][left] = key[bottom][right - i];
                
                // bottom right <- top right
                key[bottom][right - i] = key[top + i][right];
                
                // top right <- top left
                key[top + i][right] = topLeft;
            }
            
            left += 1;
            right -= 1;
        }
    }
}