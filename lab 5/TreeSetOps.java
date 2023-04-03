import java.io.*;
import java.util.*;

public class TreeSetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		TreeSet<String> set1 = loadSet( infile1 );
		TreeSet<String> set2 = loadSet( infile2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		TreeSet<String> union = union( set1, set2 );
		printSet( "\nunion: ", union );

		TreeSet<String> intersection = intersection( set1, set2 );
		printSet( "\nintersection: ",intersection );

		TreeSet<String> difference = difference( set1, set2 );
		printSet( "\ndifference: ",difference );

		TreeSet<String> xor = xor( set1, set2 );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// Y O U    W R I T E   T H I S     M E T H O D

	static TreeSet<String> loadSet( BufferedReader infile ) throws Exception
	{
		TreeSet<String> set = new TreeSet<String>();
		while(infile.ready()){
			set.add(infile.readLine());

		}
		return set;// JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// Y O U    W R I T E   T H I S     M E T H O D
	static void printSet( String caption, TreeSet<String> set )
	{
		System.out.print( caption );
		for ( String element : set){
			System.out.print( element + " ");
		}
		System.out.println();
	}

	// Y O U    W R I T E   T H I S     M E T H O D
	static TreeSet<String> union( TreeSet<String> set1, TreeSet<String> set2 )
	{
		TreeSet<String> unionset=new TreeSet<String>();
		unionset.addAll(set1);
		unionset.addAll(set2);
		return unionset; // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	static TreeSet<String> intersection( TreeSet<String> set1, TreeSet<String> set2 )
	{
		TreeSet<String> interset=new TreeSet<String>();
		interset.addAll(set1);
		interset.retainAll(set2);
		return interset;  // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	static TreeSet<String> difference( TreeSet<String> set1, TreeSet<String> set2 )
	{
		TreeSet<String> diffset=new TreeSet<String>();
		diffset.addAll(set1);
		diffset.removeAll(set2);
		return diffset;  // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	static TreeSet<String> xor( TreeSet<String> set1, TreeSet<String> set2 )
	{
		return difference(union(set1,set2),intersection(set1,set2));  // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE 
	}


} // END CLASS
