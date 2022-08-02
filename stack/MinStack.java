package com.datastructures.stack;

import java.util.*;

public class MinStack {
	private Stack<Integer> minStack;
	private int minimum;
	public MinStack() {
		minStack = new Stack<>();
		minimum = 0;
	}
	
	public void push(int item) {
		int newItem;
		if(checkMinimum(item)) {
			newItem = 2*item - minimum;
			minimum = item;
		}
		else newItem = item;
		minStack.push(newItem);
	}

	public int pop() {
		if(isEmpty()) throw new IllegalStateException();
		
		int item = minStack.pop();
		if(checkMinimum(item)) {
			minimum = 2*minimum - item;
			item = (item + minimum) / 2;
		}
		return item;
	}
	
	public boolean isEmpty() {
		return minStack.isEmpty();
	}
	
	public int peek() {
		if(isEmpty()) throw new IllegalStateException();
		
		return minStack.peek();
	}
	
	public int getMinimum() {
		return minimum;
	}
	
	@Override
	public String toString() {
		return minStack.toString();
	}
	
	private boolean checkMinimum(int item) {
		return minimum > item || isEmpty();
	}
	
}
