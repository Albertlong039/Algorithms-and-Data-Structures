import java.io.*;
import java.util.*;

public class Tower<T>
{
	private Disk<T> base;  // pointer to first disk at BASE of the tower (i.e. the old head pointer)
	private Disk<T> top;   // pointer to last disk at TOP of the tower   (i.e. the old tail pointer)

	public Tower()
	{	base = null; // compiler does this anyway. just for emphasis
	}

	public boolean empty()
	{
		return (base==null);
	}

	// i.e. the old insertAtTail or now insertAtTop we call it a PUSH
	public void push(T label)
	{
		if(empty()){
			base=new Disk<T>(label);
			top=base;
			return;
		}
		else{
			Disk<T> temp=new Disk<T>(label);
			top.next=temp;
			top=top.next;
		}
	}

	// i.e. the old removeAtTail or now removeAtTop we call it a POP
	public Disk<T> pop() throws Exception
	{
		if(empty()) throw new Exception("/nFATAL EXCEPTION");
		Disk<T> temp=base;
		if(base.next==null){
			base=top=null;
			return temp;
		}
		while(temp.next.next!=null){
			temp=temp.next;
		}
		Disk<T> temp1=temp.next;
		temp.next=null;
		top=temp;
		return temp1;
		// YOU WRITE THIS METHOD
		// MUST THROW FATAL EXCEPTION IF TOWER IS EMPTY
	  // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// prints the tower base to top, left to right,  respectively //
	public String toString()
	{	if (base == null ) 	return "EMPTY\t";
		String toString = "";
		for ( Disk<T> curr = base; curr != null ; curr=curr.next )
			toString += curr.label + " ";

		return toString;
	}
} // END TOWER CLASS

/*###############################################################################*/

class Disk<T>
{
	T label;
	Disk<T> next;

	Disk(T data)
	{	this( data, null );
	}

	Disk(T label, Disk<T> next)
	{	this.label = label;
		this.next = next;
	}

} // END DISK CLASS
