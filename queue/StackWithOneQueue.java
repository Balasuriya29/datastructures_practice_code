package com.datastructures.queue;

import java.util.*;

public class StackWithOneQueue {
	private Queue<Integer> queue;
	
	public StackWithOneQueue() {
		queue = new LinkedList<>();
	}
	
	public void push(int data) {
		Queue<Integer> temp = new LinkedList<>();
		
		switchItemsBetweenQueues(queue,temp);
		queue.add(data);
		switchItemsBetweenQueues(temp, queue);
	}
	
	public int pop() {
		if(isEmpty()) throw new IllegalStateException();
		
		return queue.remove();
	}
	
	public int peek() {
		if(isEmpty()) throw new IllegalStateException();
		
		return queue.peek();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	@Override
	public String toString() {
		return queue.toString();
	}

	private void switchItemsBetweenQueues(Queue<Integer> queue, Queue<Integer> temp) {
		while(!queue.isEmpty())
			temp.add(queue.poll());
	}
} 
