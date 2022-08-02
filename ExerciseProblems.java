package com.datastructures;

import java.util.*;

import com.datastructures.heap.Heap;

public class ExerciseProblems {
	
	private final static List<Character> openingBrackets = Arrays.asList('(','[','<','{');
	private final static List<Character> closingBrackets = Arrays.asList(')',']','>','}');
	
	//Stack
	public static String stackReverser(String str) {
		java.util.Stack<Character> stack = new java.util.Stack<Character>();
		
		for(char ch: str.toCharArray())
			stack.push(ch);
				
		StringBuffer reversed = new StringBuffer();
		while(!stack.isEmpty())
			reversed.append(stack.pop());
				
		return reversed.toString();
	}
	
	public static boolean isBalanced(String s) {
		java.util.Stack<Character> validator = new java.util.Stack<Character>(); 
		
		for(char ch: s.toCharArray()) {
			if(containsArray(ch,openingBrackets)) {
				validator.push(ch);
				continue;
			}
			
			if(containsArray(ch,closingBrackets)) {
				if(validator.isEmpty()) return false;
			
				if(!checkpeek(ch, validator)) return false;
			}

		}
		
		if(validator.isEmpty()) return true;
		return false;
	}
	
	private static boolean containsArray(char c, List<Character> Brackets) {
		return Brackets.contains(c);
	}
	
	private static boolean checkpeek(char ch, java.util.Stack<Character> stack) {
		var index = closingBrackets.indexOf(ch);
		if(stack.peek() == openingBrackets.get(index)) {
			stack.pop();
			return true;
		}	
		return false;
	}
	
	public static boolean isSequence(String a, String b) {
		java.util.Stack<Character> stack = new java.util.Stack<Character>();
		
		if(a == "") return true;
		
		var reverse_a = stackReverser(a);
		
		for(char ch: reverse_a.toCharArray()) 
			stack.push(ch);
				
		for(char ch: b.toCharArray()) {
			if(ch == stack.peek()) 
				stack.pop();
				if(stack.isEmpty()) break;
		}
		
		if(stack.isEmpty()) return true;
		
		return false;
	}
	
	//Queue
	public static void queueReverser(Queue<Integer> queue) {
		Stack<Integer> stack = new Stack<Integer>();
		
		while(!queue.isEmpty()) {
			stack.push(queue.remove());
		}
		
		while(!stack.isEmpty()) {
			queue.add(stack.pop());
		}
	}
	
	public static void reverseFirstKItemsInQueue(Queue<Integer> queue, int k) {
		if(k<0 || k > queue.size()) throw new IllegalArgumentException();
		
		if(k == 0) return;
		
		int copyOfK = k;
		int[] list = new int[queue.size()];
		boolean flag = true;
		k--;
		while(!queue.isEmpty()) {
			if(flag) {
				list[k--] = queue.poll(); 
				if(k == -1) {
					k = copyOfK;
					flag = !flag;
				}
			}
			else {
				list[k++] = queue.poll();
			}
		}
		k = 0;
		while(list.length != k) {
			queue.add(list[k++]);
		}
		
	}
	
	//HashMap
	public static char firstNonRepeatedCharacter(String s) {
		Map<Character, Integer> hashmap = new HashMap<>();
		char[] characters = s.toCharArray();
		
		for(char i: characters) {
			int value = (hashmap.containsKey(i) ? hashmap.get(i) : 0)+1;
			hashmap.put(i,value);
		}
		
		for(char i: characters) {
			int value = hashmap.get(i);
			if(value == 1) return i;
		}
		
		return Character.MIN_VALUE;
	}
	
	public static int mostFrequent(int[] numbers) {
		Map<Integer,Integer> hashmap = new HashMap<>();
		
		for(int i : numbers) {
			int value = (hashmap.containsKey(i))?(hashmap.get(i) + 1):1;	
			hashmap.put(i, value);	
		}
		int maxKey = numbers[0];
		for(int i:hashmap.keySet()) 
			maxKey = (hashmap.get(maxKey) < hashmap.get(i)) ? i : maxKey;
		
		return maxKey;
	}
	
	//Set
	public static char firstRepeatedCharacter(String s) {
		Set<Character> set = new HashSet<>();
		char[] characters = s.toCharArray();
		
		for(char i: characters) {
			if(set.contains(i))	return i;
			
			set.add(i);
		}
		
		return Character.MIN_VALUE;
	}
	
	//Heap
	public static void heapify(int[] array) {
		var lastParent = ((array.length)/2)-1;
		for(int i=lastParent;i>=0;i--)
			heapify(array,i);
	}
	
	private static void heapify(int[] array, int index) {
		var largerValueIndex = index;
		
		var leftChildIndex = (2 * index) + 1;
		var rightChildIndex = (2 * index) + 2;
		
		if(leftChildIndex < array.length &&
				array[largerValueIndex] < array[leftChildIndex]) 
			largerValueIndex = leftChildIndex;
		
		if(rightChildIndex < array.length &&
				array[largerValueIndex] < array[rightChildIndex])
			largerValueIndex = rightChildIndex;
		
		if(index == largerValueIndex) return;
		
		swapper(array, index, largerValueIndex);
		
		heapify(array, largerValueIndex);
	}
	private static void swapper(int[] array, int index1, int index2) {
		int temp = array[index2];
		array[index2] = array[index1];
		array[index1] = temp;
	}
	
	public static int findKthLargestItem(int[] array, int k) {
		
		if(k<=0 || k>array.length) throw new IllegalArgumentException();
		
		Heap heap = new Heap(array.length);
		for(var i: array) 
			heap.insert(i);
		
		var data = -1;
		
		for(var i=0;i<k;i++) 
			data = heap.removeAtIndex(0);
		
		return data;
	}
	
	//2-D Arrays
	public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingDouble(o -> o[0]));
        var index = 0;

        var checker = true;
        int[][] mergeList = new int[intervals.length][2];

        for(var i:intervals){
            if(checker) {
                mergeList[0] = i;
                checker = !checker;
                continue;
            }
            var temp = mergeList[index][1];
            if(temp > i[0]){
                int[] temp2 = {mergeList[index][0], i[1]};
                mergeList[index] = temp2;
            }
            else{
                index+=1;
                mergeList[index] = i;
            }
        }
        return Arrays.copyOfRange(mergeList, 0, index+1);
	}
	
	public static void print2DArrays(int[][] array) {
		int count = 0;
		System.out.print("[");
		for(var i:array) {
			System.out.print(Arrays.toString(i));
			if(count++ != array.length-1)System.out.print(",");
		}
		System.out.print("]");
	} 
}
