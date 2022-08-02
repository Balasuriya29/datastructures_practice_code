package com.datastructures.queue;

import java.util.*;

public class LinkedListQueue {
	private class Node{
		private int data;
		private Node next;
		private Node prev;
		
		Node(int data){
			this.data = data;
		}
	}
	
	private Node first;
	private Node last;
	private int size;
	
	public void enqueue(int data) {
		Node node = new Node(data);
		if(isEmpty()) 
			first = last = node;

		else {
			first.prev = node;
			node.next = first;
			first = node;
		}
		size++;
	}
	
	public int dequeue() {
		var lastNode = last;
		if(isEmpty())
			throw new IllegalStateException();
		
		if(size == 1)
			first = last = null;
		
		else {
			var node = last.prev;
			last.prev.next = null;
			last.prev = null;
			last = node;
		}
		size--;
		return lastNode.data;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int peek() {
		if(isEmpty())
			throw new IllegalStateException();
		
		return last.data;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		ArrayList<Integer> contents = new ArrayList<>();
		var current = last;
		while(current != null) {
			contents.add(current.data);
			current = current.prev;
		}
		
		return contents.toString();
	}
}
