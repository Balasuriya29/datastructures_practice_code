package com.datastructures.stack;

import java.util.*;

public class QueueWithOneStack {
	private Stack<Integer> stack1;
	
	public QueueWithOneStack() {
		stack1 = new Stack<Integer>();
	}
	
	public void enqueue(int item) {
		Stack<Integer> stack2 = new Stack<Integer>();
	
		movingStackItems(stack1,stack2);
		stack1.push(item);
		movingStackItems(stack2,stack1);
	}

	private void movingStackItems(Stack<Integer> stack_1, Stack<Integer> stack_2) {
		while(!stack_1.isEmpty()) {
			stack_2.push(stack_1.pop());
		}
	}
	
	public int dequeue() {
		if(isEmpty()) throw new IllegalStateException();
		
		return stack1.pop();
		
	}
	
	public int peek() {
		if(isEmpty()) throw new IllegalStateException();
		
		return stack1.peek();
	}
	
	public boolean isEmpty() {
		return stack1.isEmpty();
	}
	
	@Override
	public String toString() {
		return stack1.toString();
	}
}
