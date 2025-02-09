/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE )
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		// YOU DO THIS => init deck to be exactly numCards long
		for(int i=0; i<numCards;i++){
			deck[i]=i;
		}
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
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
		// YOUR CODE HERE DELETE LINE
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
		// YOUR CODE HERE DELETE LINE
	}

	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		// YOUR CODE HERE DELETE LINE
		for(int i=0;i<deck.length-1;i++)
		{
			if (deck[i]>deck[i+1])
				return false;
		}
		return true;
		// JUST HERE TO COMPILE
	}
}	// END DECK CLASS
