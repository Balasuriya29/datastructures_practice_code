package com.datastructures.array;

import java.util.*;

public class DyanamicArray {
	
	private int[] items;
	private int len;
	
	public DyanamicArray(int length){
		items = new int[length];
	}
	
	public void insert(int item) {
		if(items.length == len) {
			int[] items2 = new int[len * 3];
			for(int i=0; i<items.length;i++) {
				items2[i] = items[i];
			}
			items = items2;
		}

		items[len++] = item;


	}
	
	public void insertInto(int index,int item) {
		if(index >= len || index< 0) throw new IllegalArgumentException();
		if(!(indexOf(item) != -1)) {
			int[] list = Arrays.copyOfRange(items, index, len);
			int index2 = 0;
			int index1 = index+1;
			while(index2 != list.length)
				items[index1++] = list[index2++];
			}
		items[index] = item;
		len++;
	}
	
	public void removeAt(int index) {
		if(0>index || index>=len)
			throw new ArrayIndexOutOfBoundsException();

		for(int i = index; i<len-1; i++) {
			items[i] = items[i+1];
		}
		items[--len] = 0;

	}
	
	public void print() {
		for(int i=0; i<len;i++) 
			System.out.println(items[i]);
	}
	
	@Override
	public String toString() {
		int[] content = new int[len];
		for(int i=0; i<len;i++) {
			content[i] = items[i];
		}
		return Arrays.toString(content);
	}
	
	public int indexOf(int item) {

		for(int i=0; i<items.length;i++) {
			if(items[i] == item) {
				return i;
			}
		}
		return -1;
	}
	
	public int max() {
		int max = -1;
		for(var i:items) 
			if(max < i) max = i;
		
		return max;
	}
	
	public void reverse() {
		int[] contents = new int[len];
		int index = len;
		for(var i=0;i<len;i++) 
			contents[--index] = items[i];
		
		items = contents;
	}
	
	private List<Integer> asList(int[] array){
		List<Integer> list = new ArrayList<>();
		for(var i:array)
			list.add(i);
		
		return list;
	}
	
	public Object[] intersect(int[] array) {
		HashSet<Integer> set = new HashSet<>();
		var list1 = asList(items);
		var list2 = asList(array);
		set.addAll((items.length > array.length)?list1:list2);
		set.retainAll((items.length > array.length)?list2:list1);
		
		return set.toArray();
	}
}
