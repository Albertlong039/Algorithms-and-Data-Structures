import java.io.*;
import java.util.*;

public class HashTester 
{
  public HashTester() {}
  
  public static void main(String[] args) throws Exception 
  {
    double startTime = System.currentTimeMillis();
    
    if (args.length < 3) die("usage: java HashTester <numOfBuckets> <maxBucketSize> <fileOfStrings>");
    int numOfBuckets = Integer.parseInt(args[0]);
    int idealBucketSize = Integer.parseInt(args[1]);
    String infileName = args[2];
    MyHashCode hcode = new MyHashCode(numOfBuckets, idealBucketSize);
    BufferedReader infile = new BufferedReader(new FileReader(infileName));
    while (infile.ready())
    {
      hcode.add(infile.readLine());
      if (hcode.size() == numOfBuckets * idealBucketSize) break;
    }
    infile.close();
    double stopTime = System.currentTimeMillis();
    System.out.format( "Elapsed time: %1.5f sec.\n", (stopTime-startTime) / 1000.0D );
    hcode.printStats();
  }
  
  static void die(String errMsg)
  {
    System.out.println(errMsg);
    System.exit(0);
  }
}