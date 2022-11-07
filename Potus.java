import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader infile	= new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();

		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();

		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();

		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n");
		state2Presidents=loadMap(infile);
		printMap(state2Presidents);

		System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");
		while(allPresidentsFile.ready()){
			allPresidents.add(allPresidentsFile.readLine());
		}
		for(String x2:allPresidents){
			for(String x1:state2Presidents.keySet()){
				if(state2Presidents.get(x1).contains(x2)){
					System.out.println(x2+" "+x1);
				}
			}
		}

		System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");
		TreeSet<String> x=new TreeSet<String>();
		TreeSet<String> x1=new TreeSet<String>();
		x1.addAll(allPresidents);
		for(String x2:state2Presidents.keySet()){
			x.addAll(state2Presidents.get(x2));
		}
		x1.removeAll(x);
		for(String x3:x1){
			System.out.println(x3);
		}

		System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");
		while(allStatesFile.ready()){
			allStates.add(allStatesFile.readLine());
		}
		for(String x4:allStates){
			if(!state2Presidents.containsKey(x4)){
				System.out.println(x4);
			}
		}

	} // END MAIN

	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -
	static TreeMap<String,TreeSet<String>> loadMap( BufferedReader input) throws Exception
	{
		TreeMap<String,TreeSet<String>> map = new TreeMap<String, TreeSet<String>>();
		while(input.ready()){
			String line = input.readLine();
			String[] x = line.split("\\s+");
			String state=x[0];
			TreeSet<String> presidents= new TreeSet<String>();
			for(int i=1;i<x.length;i++){
				presidents.add(x[i]);
			}
			map.put(state,presidents);
		}
		return map; // REPLACE WITH YOUR CODE
	}

	static void printMap( TreeMap<String,TreeSet<String>> map )
	{
		for ( String state : map.keySet()){
			System.out.print( state );
			TreeSet<String> presidents=map.get(state);
			for(String president:presidents){
				System.out.print(" "+president);
			}
			System.out.println();
		}
	}
}	// END CLASS
