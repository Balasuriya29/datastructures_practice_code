package com.datastructures.trees;

import java.util.*;

public class Tree {
	private class Node{
		private int data;
		private Node leftChild;
		private Node rightChild;
		
		Node(int data) {
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
		}
	}
	
	private Node root;
	private ArrayList<Integer> contents = new ArrayList<>();
	
	public void insert(int data) {
		Node node = new Node(data);
		
		if(root == null) root = node;
		else {
			var current = root;
			while(current != null) {
				if(current.data == data) return;
				
				else if(current.data < data) {
					if(current.rightChild == null) {
						current.rightChild = node;
						return;
					}
						
					current = current.rightChild;
				}
				
				else {
					if(current.leftChild == null) {
						current.leftChild = node;
						return;
					}
					current = current.leftChild;
				}
			}
		}
	}
	
	public boolean find(int data) {
		var current = root;
		while(current != null) {
			if(current.data == data) return true;
			
			else if(current.data < data) {
				current = current.rightChild;
			}
			
			else {
				current = current.leftChild;
			}
		}
		return false;
	}
	
	public String traversePreOrder() {
		contents.clear();
		traversePreOrder(root);
		return contents.toString();
	}
	
	public String traverseInOrder() {
		contents.clear();
		traverseInOrder(root);
		return contents.toString();
	}
	

	public String traversePostOrder() {
		contents.clear();
		traversePostOrder(root);
		return contents.toString();
	}
	
	public int heightOfTheTree() {
		return heightOfTheTree(root);
	}
	
	public int findMinimumInBST() {
		isEmpty();
		
		return findMinimumInBST(root);
	}

	public int findMinimum() {
		isEmpty();
		
		return findMinimum(root);
	}
	
	public boolean equals(Tree tree) {
		if(tree == null) return false;
		
		return equalsNode(root, tree.root);
	
	}
	
	private boolean equalsNode(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;
		
		if(root1 != null && root2 != null)
			return root1.data == root2.data
					&& equalsNode(root1.leftChild, root2.leftChild)
					&& equalsNode(root1.rightChild, root2.rightChild);
		
		return false;
		
		
//		if(isLeaf(root1) && isLeaf(root2)) {
//			if(root1.data == root2.data) return true;
//			else return false;
//		}
//		
//		if(isNotLeaf(root2) ^ isNotLeaf(root1)) return false;
//		
//		boolean leftChild = true, rightChild = true;
//		
//		if(root1.leftChild != null && root2.leftChild != null) 
//			leftChild = equalsNode(root1.leftChild, root2.leftChild);
//
//		if(root1.rightChild != null && root2.rightChild != null) 
//			rightChild = equalsNode(root1.rightChild, root2.rightChild);
//		
//		return leftChild && rightChild;
	}
	
	public void swapRoot() {
		var temp = root.leftChild;
		root.leftChild = root.rightChild;
		root.rightChild = temp;
	}
	
	public boolean isVaildBST() {
		isEmpty();
		
		return isValidBST(root,Integer.MAX_VALUE, Integer.MIN_VALUE);
	}
	
	public ArrayList<Integer> getNodesAtDistance(int distance) {
		isEmpty();

		if(distance >= 0) {
			contents.clear();
			getNodesAtDistance(root, distance);
			return contents;
		}
		
		throw new IllegalArgumentException();

	}
	
	public ArrayList<Integer> traverseLevelOrder(){
		isEmpty();
		
		contents.clear();
		var height = heightOfTheTree();
		for(int i = 0;i <= height;i++) {
			traverseLevelOrder(root, i);
		}
		
		return contents;
	}
	
	private void traverseLevelOrder(Node node, int distance) {
		if(node == null) return;
		
		if(distance == 0) {
			contents.add(node.data);
			return;
		}
		
		traverseLevelOrder(node.leftChild, distance-1);
		traverseLevelOrder(node.rightChild, distance-1);
	}
	
	private void getNodesAtDistance(Node node, int distance) {
		if(node == null) return;
		
		if(distance == 0) {
			contents.add(node.data);
			return;
		}
		
		getNodesAtDistance(node.leftChild, distance-1);
		getNodesAtDistance(node.rightChild, distance-1);
	}
	
	private boolean isValidBST(Node node, int max, int min) {
		if(node == null) return true;
		
		if(node.data < max && node.data > min) 
			return isValidBST(node.leftChild, node.data, min) 
					&& isValidBST(node.rightChild, max, node.data);
		
		
		return false;
	}
	private void traversePreOrder(Node root) {
		if(root == null) return;
		
		contents.add(root.data);
		traversePreOrder(root.leftChild);
		traversePreOrder(root.rightChild);
	}
	
	private void traverseInOrder(Node root) {
		if(root == null) return;

		traverseInOrder(root.leftChild);
		contents.add(root.data);
		traverseInOrder(root.rightChild);
	}

	private void traversePostOrder(Node root) {
		if(root == null) return;
	
		traversePostOrder(root.leftChild);
		traversePostOrder(root.rightChild);
		contents.add(root.data);
	}
	
	private int heightOfTheTree(Node root) {
		if(root == null) return 0;
		
		if(isLeaf(root))
			return 0;
		
		return (1+ Math.max(heightOfTheTree(root.leftChild), heightOfTheTree(root.rightChild)));
	}

	private void isEmpty() {
		if(root == null) throw new IllegalStateException();
	}
	
	private boolean isLeaf(Node node) {
		return node.leftChild == null && node.rightChild == null;
	}

	private int findMinimumInBST(Node root) {
		var current = root;
		while(current.leftChild != null) {
			current = current.leftChild;
		}
		
		return current.data;
	}
	
	private int findMinimum(Node root) {
		if(root == null) return Integer.MAX_VALUE;
		
		if(isLeaf(root)) return root.data;

		var leftMin = findMinimum(root.leftChild);
		var rightMin = findMinimum(root.rightChild);
		
		return Math.min(Math.min(leftMin, rightMin), root.data);
	}
}
