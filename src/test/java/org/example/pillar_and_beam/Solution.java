package org.example.pillar_and_beam;

import java.util.*;

class Solution {
    static int BO = 1;
    static int STICK = 0;
    
    int[][] board;
    int n;
    
    public int[][] solution(int n, int[][] build_frame) {
        this.board = new int[n * 2 + 1][n * 2 + 1];
        this.n = n;
        
        for (int[] b : board) {
            Arrays.fill(b, -1);
        }
        
        for (int[] f : build_frame) {
            readCommand(f);
        }
        
        return buildRes();
    }
    
    public int[][] buildRes() {
        List<int[]> res = new ArrayList<>();
        
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == BO) {
                    int y = ((n * 2) - r) / 2 + 1;
                    int x = c / 2;
                    res.add(new int[] {x, y, BO});
                } else if (board[r][c] == STICK) {
                    int y = ((n * 2) - r) / 2;
                    int x = c / 2;
                    res.add(new int[] {x, y, STICK});
                }
            }
        }
        
        Collections.sort(
            res,
            Comparator.comparing((int[] n) -> n[0])
                      .thenComparing((int[] n) -> n[1])
                      .thenComparing((int[] n) -> n[2])
        );
        
        return res.toArray(new int[0][]);
    }
    
    public void readCommand(int[] comm) {
        int r = comm[1], c = comm[0], isBo = comm[2], isInstall = comm[3];
        
        if (isBo == BO) {
            r = (n * 2) - ((r * 2) - 1);
            c = (c * 2) + 1;

            int prev = board[r][c];
            
            board[r][c] = (isInstall == 1) ? BO : -1;
            
            if (!isValidBoard()) {
                board[r][c] = prev;
            }
            
        } else {
            r = (n * 2) - (r * 2);
            c = (c * 2);
            
            int prev = board[r][c];
            
            board[r][c] = (isInstall == 1) ? STICK : -1;
            
            if (!isValidBoard()) {
                board[r][c] = prev;
            }

        }
    }
    
    public boolean isValidBoard() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                
                if (board[r][c] == BO && !checkBo(r, c)) {
                    return false;
                }
                
                if (board[r][c] == STICK && !checkStick(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkBo(int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board.length) {
            return false;
        }
        
        if (checkElement(r + 1, c - 1, STICK) || checkElement(r + 1, c + 1, STICK)) {
            return true; // 한쪽 아래 기둥
        }
        
        if (checkElement(r, c - 2, BO) && checkElement(r, c + 2, BO)) {
            return true; // 양쪽 끝에 보
        }
        return false;
    }
    
    public boolean checkStick(int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board.length) {
            return false;
        }
        
        if (r == board.length - 1) { // 바닥
            return true;
        }
        
        if (checkElement(r + 1, c - 1, BO) || checkElement(r + 1, c + 1, BO)) {
            return true; // 보의 한쪽 끝 위
        }

        if (checkElement(r + 2, c, STICK)) { // 다른 기둥 위
            return true;
        }

        return false;
    }
    
    public boolean checkElement(int r, int c, int element) {
        if (r < 0 || r >= board.length || c < 0 || c >= board.length) {
            return false;
        }
        
        return board[r][c] == element;
    }
}