package Skillbuilders.Stack3;

import java.util.ArrayList;

public class Stack3 
{
	private ArrayList<Object> data;
	private int top;
	
	public Stack3() {
		data = new ArrayList<>();
		top = -1;
	}
	
	
	public Object top() {
		return(data.get(top));
	}
	
	
	public Object pop() {
		top -= 1;
		return(data.get(top));
	}
	
	
	public void push(Object item) {
		top += 1;
		data.add(item);
	}
	
	
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int size() {
		
		if(isEmpty()) {
			return 0;
		}
		
		else {
			return (top+1);
		}
	}
	
	public void makeEmpty() {
		data.clear();
	}
}
