package com.datastructures.linkedlist;

import java.util.*;

public class DoublyLinkedList {
	
	private class Node {
		private int data;
		private Node next;
		private Node prev;
		
		Node(int data){
			this.data = data;
		}
	}
	
	private Node first;
	private Node last;
	public int length;
	
	public void addFirst(int data) {
		var node = new Node(data);
		
		if(length == 0)
			first = last = node;
		else {
			node.next = first;
			first.prev = node;
			first = node;
		}
		
		length+=1;
	}
	
	public void addLast(int data) {
		var node = new Node(data);
		
		if (length == 0)
			first = last = node;
		else {
			last.next = node;
			node.prev = last;
			last = node;
		}
		length+=1;

	}
	
	public void deleteFirst() {
		if(length == 0)
			throw new NoSuchElementException();
		
		else if(length == 1) {
			first = last = null;
			length-=1;
		}

		else {
			var second = first.next;
			first.next = null;
			second.prev = null;
			first = second;
			length-=1;
		}
		
	}
	
	public void deleteLast() {
		if(length == 0)
			throw new NoSuchElementException();
		
		else if(first == last) {
			first = last = null;
			length-=1;
		}
		
		else {
			var previous = last.prev;
			previous.next = null;
			last.prev = null;
			last = previous;
			length-=1;
		}
	}

	public boolean contains(int data) {
		return indexOf(data) != -1;
	}
	
	public int indexOf(int data) {
		var node = first;
		
		for(int i=0;node != null;i++) {
			if(node.data == data) return i;
			node = node.next;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		ArrayList<Object> list = new ArrayList<Object>();
		var node = first;
		
		while(node != null) {
			list.add(node.data);
			node = node.next;
		}
		
		return Arrays.toString(list.toArray());
	}
	
	public int[] toArray() {
		int[] arr = new int[length];
		
		var node = first;
		
		for(int i=0;node != null;i++) {
			arr[i] = node.data;
			node = node.next;
		}
		return arr;
	}
	
	public void reverse() {
		if(length == 0) return;

		var node = first;
		var ref = first;
		while(node!=null) {
			var pointer = node.next;
			node.next = node.prev;
			node.prev = pointer;
				
			node = node.prev;
		}
		first = last;
		last = ref;

	}

}
