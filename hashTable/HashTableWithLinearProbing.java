package com.datastructures.hashTable;

import java.util.ArrayList;

public class HashTableWithLinearProbing {
	String[] items;
	int size;
	public HashTableWithLinearProbing(int size) {
		super();
		this.items = new String[size];
	}
	
	public void put(int key, String value) {
		if(isFull()) throw new IllegalStateException();
		
		int index = key % items.length;
		while(items[index] != null) 
			index = (index + 1) % items.length;
		
		items[index] = value;
		size++;
	}
	
	public String get(int key) {
		return items[key%items.length];
	}
	
	public int remove(int key) {
		return 1;
	}

	
	public boolean isFull() {
		return size == items.length;
	}
	
	@Override
	public String toString() {
		var contents = new ArrayList<>();
		for(int i = 0; i<items.length ;i++) {
			if(items[i] != null)
				contents.add(i+1 + " -> " + items[i]);
			else
				contents.add(i+1 + " -> ");
		}
		return contents.toString();
	}
	
}
