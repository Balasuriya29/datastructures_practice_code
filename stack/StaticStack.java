package com.datastructures.stack;

import java.util.*;

public class StaticStack {
	private int[] items;
	private int size;
	
	public StaticStack(int size){
		items = new int[size];
		this.size = -1;
	}
	
	public int push(int item) {
		if (isOverFlow()) throw new StackOverflowError();
		
		items[++this.size] = item;
		return item;
	}
	
	public int pop() {
		if (isEmpty()) throw new EmptyStackException();

		return items[this.size--];
	}
	
	public int peek() {
		if (isEmpty()) throw new EmptyStackException();
		
		return items[this.size];
	}
	
	public boolean isEmpty() {
		return -1 == this.size;
	}
	
	@Override
	public String toString() {
		int[] content = Arrays.copyOfRange(items, 0, size+1);
		return Arrays.toString(content);
	}
	
	private boolean isOverFlow() {
		return items.length-1 == this.size;
	}
}
