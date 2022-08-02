package com.datastructures.trees;

public class AVLTree {
	private class AVLNode{
		private int data;
		private AVLNode leftChild;
		private AVLNode rightChild;
		private int height;
		
		public AVLNode(int data) {
			this.data = data;
		}	
	}
	
	private AVLNode root;
	
	public void insert(int data) {				
		root = insert(root, data);
	}
	
	private AVLNode insert(AVLNode root, int data) {		
		if(root == null) return new AVLNode(data);
		
		else if(data < root.data) 
			root.leftChild = insert(root.leftChild, data);
		
		else 
			root.rightChild = insert(root.rightChild, data);

		
		root.height = height(root);
		var node = performingRotationsIfNotBalanced(root);
		return node;
	}
	
	private int height(AVLNode root) {
		if(root == null) return -1;
		
		var heightOfLeft = (root.leftChild != null)?root.leftChild.height:-1;
		var heightOfRight = (root.rightChild != null)?root.rightChild.height:-1;

		return 1 + Math.max(heightOfLeft, heightOfRight);
	}
	
	private int balanceFactor(AVLNode root) {
		return height(root.leftChild) - height(root.rightChild);
	}
	
	private boolean isRightHeavy(AVLNode root) {
		return balanceFactor(root) < -1;
	}
	
	private boolean isLeftHeavy(AVLNode root) {
		return balanceFactor(root) > 1;
	}
	
	private AVLNode performingRotationsIfNotBalanced(AVLNode root) {
		AVLNode newnode = root;
		if(isRightHeavy(root)) {
			if(balanceFactor(root.rightChild) > 0) {
				newnode = rightRotate(root.rightChild);
				root.rightChild = newnode;
			}
			newnode = leftRotate(root);
		}
		if(isLeftHeavy(root)) {
			if(balanceFactor(root.leftChild) < 0) {
				newnode = leftRotate(root.leftChild);
				root.leftChild = newnode;
			}
			newnode = rightRotate(root);
		}
		
		return newnode;
	}
	
	private AVLNode leftRotate(AVLNode root) {
		var newroot = root.rightChild;
		
		root.rightChild = newroot.leftChild;
		newroot.leftChild = root;
		
		newroot.leftChild.height = height(newroot.leftChild);
		newroot.height = height(newroot);
	
		return newroot;
	}
	
	private AVLNode rightRotate(AVLNode root) {
		var newroot = root.leftChild;
		
		root.leftChild = newroot.rightChild;
		newroot.rightChild = root;
		
		newroot.rightChild.height = height(newroot.rightChild);
		newroot.height = height(newroot);
		
		return newroot;
	}
}
