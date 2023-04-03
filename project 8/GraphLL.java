import java.io.*;
import java.util.*;

public class GraphLL
{
	private final int NO_EDGE = -1; // all real edges are positive
	private  Edge[] G;              // every G[i] is the head of a linked list, i.e ref to an Edge

	private int numEdges;
	public GraphLL( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );
		int numNodes = graphFile.nextInt();
		G = new Edge[numNodes];
		numEdges=0;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// DO an insertAtFront using g[SRC] as the head

		while ( graphFile.hasNextInt() )
		{
			int src=graphFile.nextInt();
			int dest=graphFile.nextInt();
			int weight=graphFile.nextInt();
			addEdge(src,dest,weight);
		}
	} // END readGraphFile

	// GO TO G[src] AND DO INSERTATFRONT ON THAT 'head' POINTER I.E. G[src]
	private void addEdge( int src, int dest, int weight )
	{
		// Edge newEdge=new Edge(dest,weight,null);
		// if(G[src]==null){
		// 	G[src]=newEdge;
		// 	return;
		// }
		// Edge curr=G[src];
		// while(curr.next!=null){
		// 	curr=curr.next;
		// }
		// curr.next=newEdge;

		G[src] = new Edge(dest, weight, G[src]);
	}

	private boolean hasEdge(int src, int dest)
	{
		Edge curr=G[src];
		while(curr!=null){
			if(curr.dest==dest){
				return true;
			}
			curr=curr.next;
		}
		return false;
		// GOTO G[src] AND WALK THAT LINKED LIST LOOKING FOR A NODE (EDGE) WITH DEST
	}

	private int inDegree(int dest) // # of roads(edges) entering this city (node)
	{	// THE HARDER ONE
		int inDeg = 0;
		// WALK ALL THE LISTS COUNTING THE NODE HAVING THIS DEST
		for(int i=0;i <G.length;i++){
			Edge curr=G[i];
			if(curr==null){
				return inDeg;
			}
			while(curr!=null){
				if(curr.dest==dest){
					inDeg++;
				}
				curr=curr.next;
			}
		}
		return inDeg;
	}

	private int outDegree(int src) // # of roads(edges) leaving this city (src node #)
	{	// THE EASIER ONE
		int outDeg=0;
		Edge curr=G[src];
		// JUST RETURN THE LENGTH OF THIS LIST AT G[src]
		while(curr!=null){
			outDeg++;
			curr=curr.next;
		}
		return outDeg;
	}

	private int degree(int node) // indegree + outdegree of this node #
	{
		return inDegree(node)+outDegree(node);
	}

	// PUBLIC METHODS. THIS CODE COPIED FROM THE GRAPH2D LAB AND USED AS IS. SINCE IT IS NOT
	// DEPENDENT ON UNDERLYING DATA STRUCTURE AND RELIES ONLY ON CALLING THE 3 DEGREE FUNCTIONS

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

	public void removeEdge(int src, int dest)
	{	// ITS AN OLD FASHIONED FIND & REMOVE NODE ON A 1 WAY LINKED LIST
		// IF THE LIST AT G[src] IS NULL -OR-  SRC OR DEST OUT OF BOUNDS
		// 		THROW AND CATCH AN EXCEPTION - SEE OUTPUT

		// USE A BASE CASE TEST FOR 1ST NODE BEGIN THE ONE
		// WALK A CURR UP TO THE PRED OF THE DEADNODE
		// REMOVE THE NODE (IF ITS THERE)

		// ITS NOT THERE THROW AND CATCH AN EXCEPTION (SEE OUTPUT)
		try{
			if(src>G.length && hasEdge(src,dest) == false){
				throw new Exception();
			}
		}
		catch(Exception a){
			System.out.println("java.lang.Exception: Non Existent Edge Exception: removeEdge("+src+","+dest+")");
			System.exit(0);
		}
		if(G[src].next!=null){
			while(G[src].next.next!=null){
				if(G[src].next.dest==dest){
					G[src].next=G[src].next.next;
				}
				G[src]=G[src].next;
			}
			if(G[src].next.dest==dest){
				G[src].next=null;
			}
		}
		else{
			if(G[src].dest==dest){
				G[src]=null;
			}
		}

	} // E N D  R E M O V E D G E

	// TOSTRING
	public String toString()
	{
		String the2String = "";
		String x="";
		// SEE OUTPUT
		for (int r=0 ; r < G.length ;++r )
		{
			Edge curr = G[r];
			the2String += String.format("%3s",r + ":");
			while(curr!=null){
				the2String += String.format("%3s"," -> ["+curr.dest+","+curr.weight + "]");
				curr=curr.next;
			}
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING
} // E N D    G R A P H L L    C L A S S

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

class Edge
{
	int dest,weight;
	// int dest, weight
	Edge next;

	Edge(int dest,int weight){
		this(dest,weight,null);
	}
	Edge(int dest,int weight,Edge next){
		this.dest=dest;this.weight=weight;this.next=next;
	}
	// Edge next
	// a Constructor that takes dest,weight, next
}
