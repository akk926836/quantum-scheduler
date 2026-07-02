package Skillbuilders.Queue2;


public class Queue2 
{
	private Object[] data;
	private int front, rear, maxsize;
	
	public Queue2(int maxItems)
	{
		data = new Object[maxItems];
		front = -1;
		rear = -1;
		maxsize = maxItems;
	}
	
	public Object front()
	{
		return(data[front]);
	}
	
	public Object dequeue()
	{
		Object item;
		
		item = data[front];
		
		if(front == rear)
		{
			makeEmpty();
		}
		else
		{
			front = (front + 1) % maxsize;
		}
		return(item);
	}
	
	public void enqueue(Object item)
	{
		if(isEmpty())
		{
			rear = 0;
			front = 0;
			data[rear] = item;
		}
		else
		{
			rear = (rear + 1) % maxsize;
			data[rear] = item;
		}
	}
	
	
	public boolean isEmpty()
	{
		if(front == -1 && rear == -1)
		{
			return(true);
		}
		else
		{
			return(false);
		}
	}
	
	public void makeEmpty()
	{
		front = -1;
		rear = -1;
	}
	
	public int size()
	{
		if(isEmpty())
		{
			return(0);
		}
		else
		{
			if(rear > front)
			{
				return(rear - front  + 1);
			}
			else if(front == rear + 1)
			{
				return(maxsize);
			}
			else
			{
				return(front - rear + 1);
			}
		}
	}

}
