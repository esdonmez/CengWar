

public class OldStack {




public class Stack 
{
	private int top;
	private Object[] elements;
	
	public Stack(int capasity)
	{
		elements = new Object[capasity];
		top = -1;
	}
	public boolean isEmpty()
	{
		return top == -1;
	}
	public boolean isFull()
	{
		return elements.length == top + 1;
	}
	public int Size()
	{
		return top + 1;
	}
	
	public Object peek()
	{
		if(isEmpty())
		{
			System.err.println("Stack is empty");
			return null;
		}
		else
		{
			return elements[top];
		}
	}
	
	public Object pop()
	{
		if(isEmpty())
		{
			System.err.println("Stack is empty");
			return null;
		}
		else
		{
			Object temp = elements[top];
			elements[top] = null;
			top--;
			return temp;
		}
	}
	
	public void push(Object element)
	{
		if(isFull())
		{
			System.err.println("Stack is full");
		}
		else
		{
			top++;
			elements[top] = element;
		}
	}

}









	/*private int top;
	private Object[] elements;
	
	OldStack(int capacity) 
	{
		elements = new Object[capacity];
		top = -1;
	}
	
	void push(Object data)
	{
		if(isFull())
			System.out.println("Stack overflow");
		else
		{
			top++;
			elements[top] = data;
		}
	}
	
	Object pop()
	{
		if(isEmpty())
		{
			System.out.println("Stack is empty");
			return null;
		}
		else
		{
			Object retData = elements[top];
			top--;
			return retData;
		}
	}
	
	Object peek() 
	{
		if(isEmpty())
		{
			System.out.println("Stack is empty");
			return null;
		}
		else 
			return elements[top];
	}
	
	boolean isEmpty()
	{
		return(top == -1);
	}
	
	boolean isFull()
	{
		return(top + 1 == elements.length);
	}
	
	int size()
	{
		return top + 1;
	}*/
}
