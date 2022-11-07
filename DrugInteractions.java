import java.util.*;
import java.io.*;

public class DrugInteractions
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader foodDrug2CategoryFile = new BufferedReader( new FileReader( "foodDrug2Category.txt" ) );
		BufferedReader patient2FoodDrugFile = new BufferedReader( new FileReader( "patient2FoodDrug.txt") );
		BufferedReader dontMixFile = new BufferedReader( new FileReader( "dontMix.txt" ) );
		TreeMap<String, TreeSet<String>> map1 = new TreeMap<String, TreeSet<String>>();
		TreeMap<String, TreeSet<String>> map2 = new TreeMap<String, TreeSet<String>>();
		map1=loadMap(foodDrug2CategoryFile);
		printMap(map1);
		map2=loadMap(patient2FoodDrugFile);
		printMap(map2);
		System.out.println();
		TreeSet<String> x5=new TreeSet<String>();
		while(dontMixFile.ready()){
			String line = dontMixFile.readLine();
			String[] x = line.split(",");
			String first=x[0];
			String second=x[1];
			TreeSet<String> x1=new TreeSet<String>();
			TreeSet<String> x2=new TreeSet<String>();
			x1=map1.get(first);
			x2=map1.get(second);
			for(String x3: x1){
				for(String x4: x2){
					for(String x0:map2.keySet()){
						if(map2.get(x0).contains(x3)){
							if(map2.get(x0).contains(x4)){
								x5.add(x0);
							}
						}
					}
				}
			}
		}
		for(String y: x5){
			System.out.println(y);
		}


	} // END MAIN
	static TreeMap<String,TreeSet<String>> loadMap( BufferedReader input) throws Exception
	{
		TreeMap<String,TreeSet<String>> map = new TreeMap<String, TreeSet<String>>();
		while(input.ready()){
			String line = input.readLine();
			String[] x = line.split(",");
			String key=x[0];
			TreeSet<String> set= new TreeSet<String>();
			for(int i=1;i<x.length;i++){
				set.add(x[i]);
			}
			map.put(key,set);
		}
		return map; // REPLACE WITH YOUR CODE
	}
	static void printMap( TreeMap<String,TreeSet<String>> map )
	{
		for ( String key : map.keySet()){
			System.out.print(key);
			TreeSet<String> set=map.get(key);
			for(String sets:set){
				System.out.print(" "+sets);
			}
			System.out.println();
		}
	}

} // END CLASS
