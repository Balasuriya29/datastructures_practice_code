package com.datastructures.queue;

import java.util.Arrays;

public class ArrayQueue {
	private int[] items;
	private int front;
	private int rear;
	private int size;
	
	public ArrayQueue(int size){
		items = new int[size];
//		this.front = -1;
//		this.rear = 0;
	}
	
	public void enqueue(int item) {
		if(isFull()) throw new IllegalStateException();
		
//		if(isEmpty()) {
//			front+=1;
//		}
		items[rear] = item;
		rear = (rear + 1) % (items.length);
		size++;
	}
	
	public int dequeue() {
		if(isEmpty()) throw new IllegalStateException();
		
		int item = items[front];
		items[front] = 0;
		front = (front + 1) % (items.length);
//		if(size == 1) {
//			front = -1;
//			rear = 0;
//		}
		size--;
		
		return item;
	}
		
	public int peek() {
		if(isEmpty()) throw new IllegalStateException();
		
		return items[front];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return items.length == size;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(items);
	}
}