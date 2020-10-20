package com.henry.java;

import java.util.*;

/**
 * 前缀树
 */
public class Trie {
    public static class TrieNode {
        private Map<Character, TrieNode> children = new HashMap<>();
        private boolean isEnd;
    }
    private TrieNode root = new TrieNode();

    //add
    public void add(String str){
        TrieNode n = root;
        for (int i=0; i<str.length(); i++) {
            //if the character doesn't exist add a node in children
            if(!n.children.keySet().contains(str.charAt(i))){
                n.children.put(str.charAt(i), new TrieNode());
            }
            //if the character is the last char in str, set isEnd flag to true
            TrieNode node = n.children.get(str.charAt(i));
            if (i == str.length()-1){
                node.isEnd = true;
            }
            //go to next level
            n = node;
        }
    }

    @Override
    public String toString() {
        //BFS
        StringBuffer sb = new StringBuffer();
        ArrayDeque<TrieNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TrieNode n = queue.poll();
            for (TrieNode child: n.children.values()){
                queue.offer(child);
                sb.append("-");
                sb.append(child.isEnd);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    //search
    public List<String> search(String prefix){
        if (prefix == null){
            return null;
        }
        //1. First check if such prefix exists
        TrieNode n = root;
        for (int i=0;i<prefix.length();i++){
            if (n.children.keySet().contains(prefix.charAt(i))){
                n = n.children.get(prefix.charAt(i));
            }else {
                return null;
            }
        }
        //2. traver the subtree to get all related strings
        List<String> result = getAllStrings(n,prefix);
        //3. return result
        return result;
    }

    /**
     * 递归中别用StringBuffer作为参数 sb.append == sb+=a
     */
    private List<String> getAllStrings(TrieNode node, String str) {
        //DFS -- preorder
        List<String> list = new ArrayList<>();
        if (node.isEnd){
            list.add(str);
        }
        //leaf node just return list.
        if (node.children.isEmpty()){
            return list;
        }
        //not leaf node continue traverse its subtrees.
        for(Map.Entry<Character,TrieNode> entry: node.children.entrySet()){
            list.addAll(getAllStrings(entry.getValue(), str+entry.getKey()));
        }
        return list;
    }

    public List<String> getAllStrings(){
        return getAllStrings(root,"");
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("connect");
        //System.out.println(trie.getAllStrings());
        trie.add("connection");
        //System.out.println(trie.getAllStrings());
        trie.add("contribute");
        System.out.println(trie.getAllStrings());
        trie.add("congratulations");
        trie.add("apple");
        trie.add("cat");
        trie.add("car");
        trie.add("dog");
        trie.add("door");

        System.out.println(trie.getAllStrings());

        System.out.println(trie.search("cat"));
        System.out.println(trie.search("ad"));
        System.out.println(trie.search("con"));
    }
}




