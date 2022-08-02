package com.datastructures.heap;

public class PriorityQueueWithHeap {
	private Heap heap;
	
	public PriorityQueueWithHeap(int size) {
		this.heap = new Heap(size);
	}
	
	public void enqueue(int item) {
		heap.insert(item);
	}
	
	public int dequeue() {
		return heap.removeAtIndex(0);
	}
	
	public int peek() {
		return heap.peek();
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public boolean isFull() {
		return heap.isFull();
	}
	
	@Override
	public String toString(){
		return heap.toString();
	}
	
	
}
