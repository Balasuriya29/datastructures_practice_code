package com.datastructures.stack;

import java.util.*;

public class TwoStacks {
	private int[] items;
	private int top1;
	private int top2;
	public TwoStacks(int size) {
		items = new int[size];
		top1 = 0;
		top2 = size-1;
	}
	
	public void push1(int item) {
		if(isFull1()) throw new StackOverflowError();
		
		items[top1++] = item;
	}
	
	public void push2(int item) {
		if(isFull2()) throw new StackOverflowError();
		
		items[top2--] = item;
	}
	
	public int pop1() {
		if(isEmpty1()) throw new EmptyStackException();
		
		return items[--top1];
	}
	
	public int pop2() {
		if(isEmpty2()) throw new EmptyStackException();
		
		return items[++top2];
	}
	
	public boolean isEmpty1() {
		return top1 == 0;
	}
	
	public boolean isEmpty2() {
		return top2 == items.length-1;
	}
	
	public boolean isFull1() {
		return top1 > top2;
	}
	
	public boolean isFull2() {
		return top1 > top2;
	}
	
	@Override
	public String toString() {
		String[] stacks = new String[2];
		stacks[0] = getStackAsString(0, top1);
		stacks[1] = getStackAsString(top2+1, items.length);
		return Arrays.toString(stacks);
	}
	
	private String getStackAsString(int start, int end) {
		int[] stack = Arrays.copyOfRange(items, start, end);
		return Arrays.toString(stack);
	}

}
