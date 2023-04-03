import java.io.*;
import java.util.*;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{	BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		TreeSet<String> allPacs= new TreeSet<String>();
		while( AllPacsFile.ready())
			allPacs.add(AllPacsFile.readLine());

		TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED
//start here

		for (String name:allPacs){
			pacToMembers.put(name, new TreeSet<String>());
		}

		TreeSet<String> acronyms=new TreeSet<String>();
		while(memberToPacsFile.ready())
		{
			String line = memberToPacsFile.readLine();
			String[] x=line.split(" ");
			for(int i=1;i<x.length;i++)
			{
				if (	pacToMembers.get(x[i]) == null){
						pacToMembers.put(x[i], new TreeSet<String>());
				}
				pacToMembers.get(x[i]).add(x[0]);
			}
		}
		//POPULATE THE TREE MAP ABOVE
		for(String pac : allPacs)
		{
			System.out.print(pac);
			for(String acronym: pacToMembers.keySet()){
					acronyms=pacToMembers.get(pac);
			}
			for(String x:acronyms){
				System.out.print(" "+x);
			}
			System.out.println();
		}
		//NOw PRINT THAT MAP (see output)


	} // END MAIN
} // CLASS
