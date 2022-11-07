/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );


	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
					G[i][j]=-1;
			}
		}
		while(graphFile.hasNext()){

			int row=graphFile.nextInt();
			int col=graphFile.nextInt();
			int weight=graphFile.nextInt();
			G[row][col]=weight;
		}
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				if(i==j){
					G[i][j]=0;
				}
			}
		}
		// WRITE A LOOP THAT PUTS NO_EDGE VALUE EVERYWHERE EXCPT ON THE DIAGONAL
		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

	} // END readGraphFile

	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}

  private boolean hasEdge(int fromNode, int toNode)
  {
    if(G[fromNode][toNode]!=-1){
			return true;
		}
		return false;
  }

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int amount=0;
		int count=0;
		for(int i=0;i <G.length;i++){
			amount=G[i][node];
			if(amount>0){
				count++;
			}
		}
		return count;
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int amount=0;
		int count=0;
		for(int i=0;i<G.length;i++){
			amount=G[node][i];
			if(amount>0){
				count++;
			}
		}
		return count;
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY
	private int degree(int node)
	{
		return inDegree(node)+outDegree(node);
	}

	// PUBLIC METHODS

	public int maxOutDegree()
	{
		int a=0;
		for(int i=0;i<G.length;i++){
			if(outDegree(i)>a){
				a=outDegree(i);
			}
		}
		return a;
	}

	public int maxInDegree()
	{
		int a=0;
		for(int i=0;i<G.length;i++){
			if(inDegree(i)>a){
				a=inDegree(i);
			}
		}
		return a;
	}

	public int minOutDegree()
	{
		int a=outDegree(0);
		for(int i=0;i<G.length;i++){
			if(outDegree(i)<a){
				a=outDegree(i);
			}
		}
		return a;
	}
	public int minInDegree()
	{
		int a=inDegree(0);
		for(int i=0;i<G.length;i++){
			if(inDegree(i)<a){
				a=inDegree(i);
			}
		}
		return a;
	}

	public int maxDegree()
	{
		int a=0;
		for(int i=0;i<G.length;i++){
			if(degree(i)>a){
				a=degree(i);
			}
		}
		return a;
	}

	public int minDegree()
	{
		int a=degree(0);
		for(int i=0;i<G.length;i++){
			if(degree(i)<a){
				a=degree(i);
			}
		}
		return a;
	}

	public void removeEdge(int fromNode, int toNode)
	{
		try{
			if(fromNode>G.length && G[fromNode][toNode] == -1){
				throw new Exception();
			}
		}
		catch(Exception a){
			System.out.println("java.lang.Exception: Non Existent Edge Exception: removeEdge("+fromNode+","+toNode+")");
			System.exit(0);
		}
		G[fromNode][toNode]=NO_EDGE;
	}

	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING

} //EOF
