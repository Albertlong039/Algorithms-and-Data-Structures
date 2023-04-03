import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;  // MAY CHOSE ANOTHER NUMBER

	public MyHashSet( int numBuckets )
	{	size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}

	public boolean add( String key )
	{
		// your code here to add the key to the table and ++ your size variable
		int hash=hashOf(key,bucketArray.length);
		if (bucketArray[hash]==null){
			bucketArray[hash] = new Node(key,null);
		}else{
			Node cur = bucketArray[hash];
			if (cur.data.equals(key)){
				return false;
			}else if(cur.data.compareTo(key)>0){
				Node toInsert = new Node(key,cur);
				bucketArray[hash] = toInsert;
			}else {
				while (cur.next != null && cur.next.data.compareTo(key) < 0) {
					cur = cur.next;
				}
				if (cur.next == null) {
					cur.next = new Node(key, null);
				} else if (cur.next.data.equals(key)) {
					return false;
				} else {
					Node toInsert = new Node(key, cur.next);
					cur.next = toInsert;
				}
			}

		}
		++size; // you just added a key to one of the lists
		if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
		return true;
	}
	public boolean contains( String key )
	{
		int hash = hashOf(key,bucketArray.length);
		Node cur = bucketArray[hash];

		while (cur.next!=null){
			if (cur.data.equals(key)){
				return true;
			}
			cur=cur.next;
		}
		if (cur.data.equals(key)){
			return true;
		}
		return false;
		  // just to make it compile.
		// You hash this key. goto that list. look for this key in that list
	}

	private void upSize_ReHash_AllKeys()
	{	System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArray.length, bucketArray.length*2  );
		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;
//		FOR EACH LIST IN THE ARRAY
//			FOR EACH NODE IN THAT LIST
//				HASH THAT KEY INTO THE BIGGER TABLE
//				BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS
		for(Node i: bucketArray){
			Node cur = i;
			while(cur!=null){
				int hash = hashOf(cur.data,biggerArray.length);
				if (biggerArray[hash]==null){
					biggerArray[hash] = new Node(cur.data,null);
				}else{
					Node cur2 = biggerArray[hash];
					while(cur2.next!=null){
						cur2=cur2.next;
					}
					cur2.next = new Node(cur.data,null);
				}
				cur=cur.next;
			}
		}
		bucketArray = biggerArray;
	} // END OF UPSIZE & REHASH

	public void clear(){
		bucketArray =new Node[numBuckets];
	}

	@Override
	public boolean remove(String key) {

		int hash=hashOf(key,bucketArray.length);
		if (bucketArray[hash]==null){
			return false;
		}else{
			Node cur = bucketArray[hash];
			if (cur.data.equals(key)){
				bucketArray[hash]=cur.next;
			}else if(cur.data.compareTo(key)>0){
				return false;
			}
			else {
				while (cur.next != null && cur.next.data.compareTo(key) < 0) {
					cur = cur.next;
				}
				if (cur.next == null) {
					return false;
				} else if (cur.next.data.equals(key)) {
					cur.next = cur.next.next;
				} else {
					return false;
				}
			}

		}
		--size;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return (size==0 ? true:false);
	}

	@Override
	public int size() {
		return size;
	}

	private int hashOf( String key, int numBuckets ) //  number returned -MUST- MUST BE IN [0..numBuckets-1]
	{

		int hashOf = 0;
		for(int i=0; i<key.length();i++){
			hashOf*=31;
			hashOf+=key.charAt(i);
		}
		hashOf%=numBuckets;
		hashOf=Math.abs(hashOf);
		return hashOf;
	}
} //END MyHashSet CLASS

class Node
{	String data;
	Node next;
	public Node ( String data, Node next )
	{ 	this.data = data;
		this.next = next;
	}
}



