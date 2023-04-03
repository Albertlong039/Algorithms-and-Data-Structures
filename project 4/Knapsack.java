/*
	PrintSubSets.java
*/
import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
public class Knapsack
{
	public static void main( String[] args ) throws FileNotFoundException
	{
		// int[] set = { 2,5,7,8,3 }; // 5 elements produces 2^5 subsets ( 32 subsets )
		// System.out.print( "original set: { " );
		// for ( int i=0 ; i<set.length ; ++i )
		// 	System.out.print( set[i] + " " );
		// System.out.println("}" );

		// declare a 16 integer array

		// use a scanner to read in 16 integers and the target weight from the input file
		Scanner infile = new Scanner(new File(args[0]));
		int[] set= new int[16];
    for(int i=0;i<16;i++)
				set[i] = infile.nextInt();
		int target= infile.nextInt();

		for ( int i=0 ; i<65536; ++i )
		{	// initialize a sum tracker
			// initialize an empty string

			String bitmap = toBitString( i, set.length );
			int sum =0;
			String bit="";
			//System.out.format("bitmap %s  { ", bitmap );
			for ( int bindx=0 ; bindx<set.length ; ++bindx )
				if ( bitmap.charAt(bindx)=='1' ) {
					sum += set[bindx];
					bit += set[bindx]+" ";
				}
				if(sum==target){
					System.out.println(bit);
				}
					//System.out.print( set[bindx] + " " );
					//System.out.print( "  " );
			//System.out.println( "}");
		}
	} // END MAIN

	// i.e number 31 converted to a width of 5 bits = "11111"
	//     nuumber 7 converted to a width of 5 bits = "00111"
	static String toBitString( int number, int width )
	{
		String bitstring = "";
		while (number > 0)
		{	if (number % 2 == 0)
				bitstring = "0" + bitstring;
			else
				bitstring = "1" + bitstring;
			number /= 2 ;
		}
		while ( bitstring.length() < width )
				bitstring = "0" + bitstring;
		return bitstring;
	}
} // END CLASS
