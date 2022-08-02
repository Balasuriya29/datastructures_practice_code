package com.datastructures.queue;

import java.util.Arrays;

public class PriorityArray {
	private int[] items;
	private int size;
	
	public PriorityArray(int size) {
		items = new int[size];
	}
	
	public void add(int item) {
		if(isFull()) throw new IllegalStateException();
		
		if(isEmpty()) {
			items[size] = item;
		}
		else {
			shiftItemsToInsert(item);
		}
		size+=1;
	}
	
	public int remove() {
		if(isEmpty()) throw new IllegalStateException();
		int item = items[--size];
		
		return item;
	}
	
	public int peek() {
		if(isEmpty()) throw new IllegalStateException();
		
		return items[0];
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

	private void shiftItemsToInsert(int item) {
		for(int i=size-1;i>-1;i--) {
			if(item < items[i]) {
				items[i+1] = items[i];
				items[i] = item;
			}
			else {
				items[i+1] = item;
				break;
			}
		}
	}
}
