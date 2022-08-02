package com.datastructures.heap;

import java.util.Arrays;

public class Heap {
	private int[] items;
	private int size;
	
	public Heap(int size) {
		this.items = new int[size];
	}
	
	public void insert(int item) {
		if(isFull()) throw new IllegalStateException();
		
		if(contains(item)) return;
		
		items[size++] = item;
		bubbleUpIfNeeded();
	}
	
	public int removeAtIndex(int index) {
		if(isEmpty()) throw new IllegalStateException();
		
		int item = items[index];
		size--;
		bubbleDownIfNeeded(index);

		return item;
	}
	
	public int peek() {
		return items[size-1];
	}
	
	public boolean isFull() {
		return size == items.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public String toString() {
		int[] contents = new int[size];
		
		for(int i=0;i<size;i++) {
			contents[i] = items[i];
		}
		
		return Arrays.toString(contents);
	}
	
	private boolean contains(int item) {
		return Arrays.asList(items).contains(item);
	}
	
	private void bubbleDownIfNeeded(int index) {
		if(isEmpty()) return;
		
		items[index] = items[size];
		
		while(isNotValidParent(index)) 
			index = findLargerChildToSwap(index);
		
	}

	private int findLargerChildToSwap(int index) {
		return (leftChild(index)>rightChild(index)) 
				? swapper(index, indexOfLeftChild(index))
				: swapper(index, indexOfRightChild(index));
	}

	private boolean isNotValidParent(int index) {
		if(indexOfLeftChild(index) > size || indexOfRightChild(index) > size) return false;
		
		return items[index] < leftChild(index) 
				|| items[index] < rightChild(index);
	}

	private int rightChild(int index) {
		return items[indexOfRightChild(index)];
	}

	private int leftChild(int index) {
		return items[indexOfLeftChild(index)];
	}
	
	private void bubbleUpIfNeeded() {
		if(size == 1) return;
		
		int index = size-1;
		while(index > 0 && items[indexOfParent(index)] < items[index]) 
			index = swapper(index, indexOfParent(index));
	}

	private int swapper(int index1, int index2) {
		int temp = items[index2];
		items[index2] = items[index1];
		items[index1] = temp;
		index1 = index2;
		return index1;
	}
	
	private int indexOfParent(int index) {
		return (index-1)/2;
	}
	
	private int indexOfLeftChild(int index) {
		return (index*2)+1;
	}
	
	private int indexOfRightChild(int index) {
		return (index*2)+2;
	}
}
