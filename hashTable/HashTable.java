package com.datastructures.hashTable;

import java.util.*;

public class HashTable {
	private class Entry{
		private int key;
		private String value;
		
		Entry(int k, String v){
			this.key = k;
			this.value = v;
		}
		
		@Override
		public String toString() {
			ArrayList<String> entries = new ArrayList<>();
			entries.add(String.valueOf(key));
			entries.add(value);
			
			return entries.toString();
		}
		
	}
	
	private LinkedList<Entry>[] entries;
	
	public HashTable(int size) {
		entries = new LinkedList[size];
	}
	
	public String get(int key) {
		var entry = getEntry(key);
		if(entry != null)
			return entry.value;
		
		return null;
	}
	
	public void put(int key, String value) {
		var entry = getEntry(key);
		if(entry != null) {
			entry.value = value;
			return;
		}
		
		getOrCreateBudget(key).add(new Entry(key, value));
	}
	
	public String remove(int key) {
		var entry = getEntry(key);
		if (entry != null) {
			String value = entry.value;
			getBudget(key).remove(entry);
			return value;
		}
		throw new IllegalStateException();
	}
	
	@Override
	public String toString() {
		ArrayList<String> contents = new ArrayList<>();
		for(int i=0; i<entries.length;i++) {
			if(entries[i] != null) {
				contents.add(entries[i].toString());
			}
		}
		
		return Arrays.toString(entries);
	}
	
	private int hash(int key) {
		return key%entries.length;
	}

	private Entry getEntry(int key) {
		var budget = getBudget(key);
		if(budget != null) {
			for(var i:budget) {
				if(i.key == key) {
					return i;
				}
			}
		}
		return null;
	}
	
	private LinkedList<Entry> getBudget(int key) {
		return entries[hash(key)];
	}
	
	private LinkedList<Entry> getOrCreateBudget(int key) {
		var index = hash(key);
		var budget = entries[index];
		if(budget == null) 
			entries[index] = new LinkedList<>();
			budget = entries[index];
		
		return budget;
	}
}
