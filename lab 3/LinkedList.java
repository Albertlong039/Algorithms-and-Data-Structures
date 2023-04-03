// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST IDENTICAL TO OTHER
	public LinkedList( LinkedList<T> other )
	{
		head = other.head; // YOU ABSOLUTLEY MUST CHANGE THIS. THIS IS A SHALLOW COPY :(
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " -> ";
		}

		return toString + "\n";
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// TACK A NEW NODE ONTO THE END (CABOOSE) OF THE LIST
	public void insertAtTail(T data)
	{
		if(head==null){
			insertAtFront(data);
		}
		else{
			Node<T> curr=head;
			while(curr.getNext()!=null){
				curr=curr.getNext();
			}
			Node<T> temp=new Node<T>(data);
			curr.setNext(temp);
		}
		// YOUR CODE HERE. MUST USE insertAtFront() IN BASE CASE
	}

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT WE MAKE YOU
	// COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	public int size()
	{
		int count=0;
		Node<T> curr=head;
		while(curr!=null){
			curr=curr.getNext();
			count++;
		}
		return count; //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// MUST USE search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
	// NO LOOPS ALLOWED
	public boolean contains( T key )
	{
		return (search(key)!=null); //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// TRAVERSE LIST FRONT TO BACK LOOKING FOR THIS DATA VALUE.
	// RETURN REF TO THE FIRST NODE THAT CONTAINS THIS KEY.
	// DO NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	public Node<T> search( T key )
	{
		for(Node<T> curr=head;curr!=null;curr=curr.getNext()){
			if(curr.getData().equals(key)){
				return curr;
			}
		}
		return null; //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}
} //END OF LINKEDLIST CLASS DEFINITION
