package Skillbuilders.LinkedList;

public class Node 
{
	private Object data;
	private Node next;
	
	public Node(Object newData)
	{
		data = newData;
		next = null;
	}
	
	public Node getNext()
	{
		return(next);
	}

	public void setNext(Node newNode)
	{
		next = newNode;
	}
	
	public Object getData()
	{
		return data;
	}
}
