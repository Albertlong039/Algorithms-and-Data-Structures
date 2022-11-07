import java.io.*;
import java.util.*;
public class WoodChuck
{
	public static void main(String args[]) throws Exception
	{
		TreeMap<String,Integer> histogram = new TreeMap<String,Integer>();
		BufferedReader infile = new BufferedReader( new FileReader( args[0] ) );
		while ( infile.ready() ) // .ready() true if there is another line in the file 
		{
			String word = infile.readLine();
			if ( !histogram.containsKey(word) )
				histogram.put( word, 1 );
			else
				histogram.put( word, histogram.get(word) + 1 ); // replace freq with freq+1 
		}	
		for ( String word : histogram.keySet() )
			System.out.println( word + "\t" + histogram.get(word) );
	} // END MAIN
} // CLASS WOODCHUCK
