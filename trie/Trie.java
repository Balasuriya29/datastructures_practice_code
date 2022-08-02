package com.datastructures.trie;

import java.util.*;

/*
 * The Comments in the program are basically the array implementation of tries code ^_^;
 */

public class Trie {
//	final public static int ALPHABET_SIZE = 26;
	private class Node{
		private char data;
//		private Node[] children;
		private HashMap<Character,Node> children = new HashMap<>();
		private boolean isEndOfWord;
		public Node(char data) {
			this.data = data;
//			this.children = new Node[ALPHABET_SIZE];
			this.isEndOfWord = false;
		}
		@Override
		public String toString() {
			return "[" + data + "]";
		}
		
		public boolean hasChild(char ch) {
			return children.get(ch) != null;
		}
		
		public void setChild(char i) {
			children.put(i, new Node(i));
		}
		
		public Node getChild(char ch) {
			return children.get(ch);
		}
		
		public void setEndOfWord() {
			isEndOfWord = true;
		}
		
		public Node[] getChildren() {
			return children.values().toArray(new Node[0]);
		}

		public boolean hasChildren() {
			return !children.isEmpty();
		}
		
		public void removeChild(char ch) {
			children.remove(ch);
		}
	}
	
	private Node root = new Node(' ');
	
	public void insert(String str) {
		var current = root;
//		int indexOfCharacter = 0;
		for(var i:str.toCharArray()) {
//			indexOfCharacter = i - 'a';
			if(!current.hasChild(i))
				current.setChild(i);
			
			current = current.getChild(i);
		}
		current.setEndOfWord();
	}
	
	public boolean contains(String str) {
		if(!isValid(str)) return false;
		
		var current = root;
		for(var i:str.toCharArray()) {
			if(current.hasChild(i)) {
				current = current.getChild(i);
			}
			else return false;
		}
		return current.isEndOfWord;
	}

	public void remove(String str) {
		if(!root.hasChildren() || !isValid(str)) 
			throw new IllegalStateException();
		
		remove(root, str, 0);
	}
	
	public ArrayList<String> autoCompletetheWord(String str) {
		ArrayList<String> list = new ArrayList<>();
		
		if(!root.hasChildren() || !isValid(str)) 
			return list;

		var root = lastNodeOfPrefix(str);
		autoCompletetheWord(root, str, list);
		
		return list;
	}
	
	public boolean containsRecursive(String str) {
		if(!isValid(str)) throw new IllegalArgumentException();
		
		return containsRecursive(root, str, 0);
	}
	
	public int numberOfWords() {
		List<Integer> list = new ArrayList<>();
		numberOfWords(root, list);
		return list.size();
	}
	
	public String longestCommonPrefix(String[] words) {
		if(words.length == 0) return "";
		
		Trie trie = new Trie();
		var newRoot = insertAll(trie, words);
		String str = "";

		return longestCommonPrefix(newRoot, str);
	}
	
	private Node insertAll(Trie trie,String[] words) {
		for(var i: words)
			trie.insert(i);
		
		return trie.root;
	}
	
	private String longestCommonPrefix(Node node, String str) {
		var children = node.getChildren(); 
		if(children.length == 1) {
			node = children[0];
			return longestCommonPrefix(node, str+node.data);
		}
		else {
			return str;
		}
	}

	private void numberOfWords(Node current, List<Integer> list) {
		if(current == null) return;
		
		if(current.isEndOfWord) list.add(1);
		
		for(var child: current.getChildren()) {
			numberOfWords(child, list);
		}
	}
	
	private boolean containsRecursive(Node node, String str, int index) {
		if(node == null)
			return false;
		
		if(index == str.length())
			return node.isEndOfWord;
		
		var child = getChildWithIndex(node, str, index);
		return containsRecursive(child, str, index+1);
	}

	//Pre-Order Traversal
	private void autoCompletetheWord(Node node, String str, ArrayList<String> list) {
		if(node == null) return;
		
		if(node.isEndOfWord) list.add(str);
		
		for(var child: node.getChildren()) {
			autoCompletetheWord(child, str+child.data, list);
		}
	}

	private Node lastNodeOfPrefix(String str) {
		if(str == null)
			return null;
		
		var current = root;
		
		for(var ch: str.toCharArray()) {
			if(!current.hasChild(ch))
				return null;
			
			current = current.getChild(ch);
		}
		
		return current;
	}
	
	//PostOrderTraversal
	private void remove(Node node, String str, int length) {
		if(length == str.length()) {
			node.isEndOfWord = false;
			return;
		}
		
		if(node.hasChild(str.charAt(length))) {
			var child = getChildWithIndex(node, str, length);
			remove(child, str, length+1);
			if(!child.hasChildren() && !child.isEndOfWord ) node.removeChild(child.data);
		}
		
		else return;
	}

	private Node getChildWithIndex(Node node, String str, int length) {
		var child = node.getChild(str.charAt(length));
		return child;
	}

	private boolean isValid(String str) {
		return !(str == " " || str == null);
	}
}
