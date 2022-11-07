// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list
	private Node<T> tail;  // pointer to the last elem of the list ( caboose or tail node)

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
		tail = head;
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " -> ";
		}

		return (String) (toString + " ");
	}

	// ########################## Y O U   W R I T E   T H E S E    M E T H O D S
	// . . .AND ANY SUPPORTING METHODS YOU NEED FOR THEM

	// THIS VERSION JUST LOADS THE LISTS FROM THE FILE BEFORE THEY ARE MERGED
	public void insertAtTail( T data )
	{
		if(head==null){
			this.head=new Node<T>(data);
			tail=new Node<T>(data);
		}
		else{
			Node<T> curr=head;
			while(curr.next!=null){
				curr=curr.next;
			}
		Node<T> temp=new Node<T>(data);
		curr.next=temp;
		this.tail=temp;
	 }
 }
	public LinkedList<T> merge( LinkedList<T> other )
	{
		LinkedList<T> result = new LinkedList<T>();
		Node<T> curr2 = other.head;
		for (Node<T> curr1 = this.head; curr1!=null; curr1 = curr1.next){
			while(curr2!=null && curr2.data.compareTo(curr1.data)<=0){
				if(!curr2.data.equals(curr1.data)){
					result.insertAtTail(curr2.data);
				}
				curr2=curr2.next;
			}
			result.insertAtTail(curr1.data);
		}
		return result;
	}
} //END OF LINKEDLIST CLASS DEFINITION

// NODE CLASS
 class Node<T>
{
  T data;
  Node<T> next;

  Node(T data)
  {
    this( data, null );
  }
  Node(T data, Node<T> next)
  {
    this.data = data;
    this.next = next;
  }

  public String toString()
  {
	  return "" + data; // stringify the data
  }

} //EOF
