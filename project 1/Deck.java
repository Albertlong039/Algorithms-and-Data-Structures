/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		int a=deck.length/2,b=0;
		int []c = new int[deck.length/2];
		for(int i=0;i<deck.length/2;i++){
			c[i]=deck[i];
		}
		for(int i=0;i<deck.length;i++)
			if(i%2==0){
				deck[i]=deck[a];
				a++;
			}
			else{
				deck[i]=c[b];
				b++;
			}
		// YOUR CODE HERE
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int a=deck.length/2,b=0;
		int []c = new int[deck.length/2];
		for(int i=0;i<deck.length/2;i++){
			c[i]=deck[i];
		}
		for(int i=0;i<deck.length;i++)
			if(i%2==0){
				deck[i]=c[b];
				b++;
			}
			else{
				deck[i]=deck[a];
				a++;
			}
		// YOUR CODE HERE
	}
	
	public String toBitString( int n ) 
	{
		String binary="";
		int a=1,count=0;
		while(a<=n/2){
			a=a*2;
		    count++;
		}
		binary = binary+"1";
		int left=n-a;
		a=a/2;
		for (;count>0;count--){
			if(left>=a){
				binary=binary+"1";
			    left=left-a;
				a=a/2;
			}
			else{
				binary=binary+"0";
				a=a/2;
			}	
		}
			
			
		return binary; // REPLACE WITH YOUR CODE
	}
	
}	// END DECK CLASS