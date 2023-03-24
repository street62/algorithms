package org.example.word_search_2;

import java.util.*;

class Solution {
    private int ROWS;
    private int COLS;
    private char[][] board;
    private boolean[][] visit;
    private Set<String> res = new HashSet<>();

    class TrieNode {
        private Map<Character, TrieNode> map = new HashMap<>();
        private boolean isWord;
    }

    public List<String> findWords(char[][] board, String[] words) {
        this.ROWS = board.length;
        this.COLS = board[0].length;
        this.board = board;
        this.visit = new boolean[ROWS][COLS];

        TrieNode root = new TrieNode();

        for (String w : words) {
            TrieNode cur = root;
            int index = 0;
            while (index < w.length()) {
                if (!cur.map.containsKey(w.charAt(index))) {
                    cur.map.put(w.charAt(index), new TrieNode());
                }
                cur = cur.map.get(w.charAt(index));
                index += 1;
            }
            cur.isWord = true;
        }

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                dfs(r, c, new StringBuilder(), root);
            }
        }

        return new ArrayList<>(res);
    }

    public void dfs(int r, int c, StringBuilder curWord, TrieNode curTrie) {
        if (r < 0 || r >= ROWS || c < 0 || c >= COLS) {
            return;
        }
        if (visit[r][c]) {
            return;
        }
        
        if (!curTrie.map.containsKey(board[r][c])) {
            return;
        }

        visit[r][c] = true;
        curTrie = curTrie.map.get(board[r][c]);
        curWord.append(board[r][c]);
        
        if (curTrie.isWord) {
            res.add(curWord.toString());
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            dfs(r + dx[i], c + dy[i], curWord, curTrie);
        }

        curWord.deleteCharAt(curWord.length() - 1);
        visit[r][c] = false;

    }
}