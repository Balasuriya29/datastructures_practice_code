package com.datastructures.stack;

import java.util.*;

public class DynamicStack {
	public class Node{
		private int data;
		private Node next;
		
		Node(int data){
			this.data = data;
		}
	}
	
	private Node top;
	private Node first;
	private int size = -1;
	
	public int push(int data) {
		var node = new Node(data);
		
		if(isEmpty()) top = first = node;

		else {
			top.next = node;
			top = node;
		}
		
		size+=1;
		return data;
	}
	
	public int pop() {
		if(isEmpty()) throw new EmptyStackException();
		
		var node = top;
		int data = node.data;
		
		if(size == 0) {
			top = first = null; 
			size-=1;
			return data;
		}
		
		else {
			var previous = getPrevious(top);
			top = previous;
		}
		top.next = null;
		size-=1;
		return data;
	}
	
	private Node getPrevious(Node node) {
		var current = first;
		while(current!=null) {
			if(current.next == node) return current;
			current = current.next;
		}
		return null;
	}
	
	public int peek() {
		if(isEmpty()) throw new EmptyStackException();
		return top.data;
	}
	
	public boolean isEmpty() {
		return size == -1;	
	}
	
	@Override
	public String toString() {
			
		var node = first;
		int[] content = new int[size+1];

		for(int i = 0;node != null;i++) {
			content[i] = node.data;
			node = node.next;
		}
		
		return Arrays.toString(content);
	}
}
