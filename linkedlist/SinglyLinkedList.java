package com.datastructures.linkedlist;

import java.util.*;

public class SinglyLinkedList {
	
	private class Node {
		private int data;
		private Node next;
		
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
			var previous = getPrevious(last); 
			previous.next = null;
			last = previous;			
			length-=1;
		}
	}
	
	private Node getPrevious(Node node) {
		var current = first;
		while(current != null) {
			if(current.next == node) return current;
			current = current.next;
		}
		return null;
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
		
		var previous = first;
		var current = first.next;		 
		while(current !=null){
			var nex = current.next;
			current.next = previous;
			previous = current;
			current = nex;			 
		} 
		last = first;
		last.next = null;
		first = previous;
	}
	
	public int getKthFromTheEnd(int k){
		if(k <= 0) 
			throw new IllegalArgumentException();
		
		if (first == null) throw new IllegalStateException();
		
		var current = first;
		var next = current;
		
		while(k != 1) {
			next = next.next;
			if(next == null)
				throw new IllegalArgumentException();
			k--;
		}
		
		while(next != last) {
			current = current.next;
			next = next.next;
		}
		
		return current.data;
	}
	
	public List<Integer> getMiddle() {
		if(length == 0) throw new IllegalStateException();
		
		var slow = first;
		var fast = first;
		while(fast != last && fast.next != last) {
			slow = slow.next;
			fast = fast.next.next;
		}
		if(fast == last)
			return Arrays.asList(slow.data);
		else {
			return Arrays.asList(slow.data, slow.next.data);
		}
			
	}
	
	public boolean hasLoop() {
		if(length == 0) throw new IllegalStateException();
		
		var slow = first;
		var fast = first;
		
		while(slow!=null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow) 
				break;
		}
		if(fast == slow) return true;
		
		return false;
 	}
	
	public void createLoop() {
		last.next = first;
	}
}
