import java.io.*;
import java.util.*;

public class Jumbles
{	public static void main(String args[]) throws Exception
  {
    BufferedReader infile = new BufferedReader( new FileReader( args[0] ) );
    BufferedReader infile1 = new BufferedReader( new FileReader(args[1]) );
    TreeMap<String,TreeSet<String>> map1= new TreeMap<String,TreeSet<String>>();
    while ( infile.ready() )
    {
      TreeSet<String> dictionary=new TreeSet<String>();
      String word = infile.readLine();
      if ( !map1.containsKey(toCanonical(word))){
        dictionary.add(word);
        map1.put(toCanonical(word),dictionary);
      }
      else{
        dictionary=map1.get(toCanonical(word));
        dictionary.add(word);
        map1.put(toCanonical(word),dictionary);
      }
    }
    TreeSet<String> jumble=new TreeSet<String>();
    while ( infile1.ready() )
    {

      String word = infile1.readLine();
      if ( !map1.containsKey(toCanonical(word))){
        jumble.add(word);
      }
      else{
        jumble.add(word);
      }
    }
    for(String jumbles: jumble)
		{
			System.out.print(jumbles);
			if(map1.containsKey(toCanonical(jumbles)))
			{
				for (String words: map1.get(toCanonical(jumbles)))
				{
					System.out.print(" "+words);
				}
			}
			System.out.println();
		}
  }
  static String toCanonical( String s )
  {
  	char[] letters = s.toCharArray();
  	Arrays.sort( letters );
  	return new String( letters );
  }

}
