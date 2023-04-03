import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked")
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data );
		}
		infile.close();
	}



	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		if (head==null){
			CDLL_Node<T> newNode = new CDLL_Node<T>(data,null,null);
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			head=newNode;
			return;
		}
		CDLL_Node<T> curr=new CDLL_Node<T>(data,head.getPrev(),head);
		head.getPrev().setNext(curr);
		head.setPrev(curr);
		return;
	}


	public int size()
	{
		int count=0;
		CDLL_Node<T> curr=head;
		do{
			curr=curr.getNext();
			count++;
		} while(curr!=head);
		return count;
	}

	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{
		CDLL_Node<T> curr=head;
		do{
			if(curr.getData().equals(key)){
				return curr;
			}
			curr=curr.getNext();
		}while(curr!=head);
		return null;
	}

	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String toString="";
		if(head==null){
			return toString;
		}
		CDLL_Node<T> curr = head;
		do{
			toString =toString+curr.getData();
			if(curr.getNext()!=head){
				toString += "<=>";
		  }
		  curr=curr.getNext();
		}while(curr!=head);
		return toString;
	}

	public void removeNode ( CDLL_Node<T> deadNode )
	{
		deadNode.getNext().setPrev(deadNode.getPrev());
		deadNode.getPrev().setNext(deadNode.getNext());
	}

	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;

		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.getData();

			System.out.println("stopping on "+deadName+" to delete "+deadName);
			if(skipCount>0){
				curr=curr.getNext();
			}
			else{curr=curr.getPrev();}
			if(head.getData().equals(deadName)){
				head=curr;
			}
			removeNode(deadNode);
			System.out.println("deleted. list now: "+ toString());

			if(size()==1){
				break;
			}

			System.out.print("resuming at "+curr.getData()+", skipping "+ curr.getData()+" +");
			if(skipCount>0){
				System.out.println(skipCount-1+" nodes CLOCKWISE after");
				for(int i=0;i<skipCount;i++){
					curr=curr.getNext();
				}
			}
			else{
				System.out.println(Math.abs(skipCount+1)+" nodes COUNTERWISE after");
				for(int i=0;i>skipCount;i--){
					curr=curr.getPrev();
				}
			}

		}while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}

} // END CDLL_LIST CLASS
class CDLL_Node<T>
{
  private T data;
  private CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

  public CDLL_Node()
  {
    this( null, null, null );  // 3 FIELDS TO INIT
  }

  public CDLL_Node(T data)
  {
    this( data, null, null);
  }

  public CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {
    setData( data );
	setPrev( prev );
    setNext( next );
  }

  public T getData()
  {
    return data;
  }

  public CDLL_Node<T> getPrev()
  {
    return prev;
  }
  public CDLL_Node<T> getNext()
  {
    return next;
  }

  public void setData(T data)
  {
     this.data = data;
  }

  public void setNext(CDLL_Node<T> next)
  {
    this.next = next;
  }

  public void setPrev(CDLL_Node<T> prev)
  {
    this.prev = prev;
  }

  public String toString()
  {
	  return ""+getData();
  }

} //EOF
