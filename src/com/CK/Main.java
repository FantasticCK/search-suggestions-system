package com.CK;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
//        new Solution().suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse");
        new Solution().suggestedProducts(new String[]{"bags", "baggage", "banner", "box", "cloths"}, "bags");
    }
}

class Solution {
    private class TrieNode {
        TrieNode[] children;
        PriorityQueue<String> words;

        TrieNode() {
            children = new TrieNode[26];
            words = new PriorityQueue<>();
        }
    }

    private class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public PriorityQueue<String> search (TrieNode node) {
            return node.words;
        }

        public void insert(String s) {
            TrieNode curr = root;
            int i = 0;
            while (i < s.length()) {
                int c = s.charAt(i) - 'a';
                if (curr.children[c] == null) {
                    curr.children[c] = new TrieNode();
                }
                curr = curr.children[c];
                curr.words.offer(s);
                i++;
            }
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        if (products.length == 0 || searchWord.length() < 2) {
            return res;
        }
        Trie trie = new Trie();
        for (String s: products) {
            trie.insert(s);
        }
        TrieNode curr = trie.root;
        for (int i = 0; i < searchWord.length(); i++) {
            int c = searchWord.charAt(i) - 'a';
            List<String> temp = new ArrayList<>();
            if ( curr == null || curr.children[c] == null) {
                res.add(new ArrayList<>(temp));
                curr = null;
                continue;
            }
            curr = curr.children[c];
            for (int j = 0; j < 3; j++) {
                if (!curr.words.isEmpty()) {
                    temp.add(curr.words.poll());
                }
            }
            res.add(new ArrayList<>(temp));
            for (int j = 0; j < temp.size(); j++) {
                curr.words.offer(temp.get(j));
            }

        }
        return res;
    }
}
