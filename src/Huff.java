import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import java.util.*;


public class Huff extends Hufftree{

  static boolean DEBUG=true;

  // MAIN METHOD
  // It has to throw IOException since you are using classes that throw IOExceptions.
  public static void main (String[] args) throws IOException {


    PriorityQueue<Hufftree> pQueue = new PriorityQueue<Hufftree>();
    // USING FileIOC TO READ IN A FILE
    // Here is some code that shows you how use FileIOC to read in a (not binary) file:
    // Of course, you will want to read a file provided as a command-line argument.
    FileIOC fioc = new FileIOC();
    FileReader fr = fioc.openInputFile("../samples/lincoln.txt");
    Map<Character, Integer> freq = new HashMap<>();

    // This lets you go through the file character by character so you can count them.
    int c;
    while ((c = fr.read()) != -1) {

      // Example of something to do: print out each character.
      //System.out.println((char) c);

      //if the character has a frequency of 0, make the frequency = 1
      if(!freq.containsKey((char)c)){
        freq.put((char)c,1);
        //System.out.println(freq.get((char)c));
      }

      //else, add 1 to the value of the given character
      else{
        int temp = freq.get((char)c);
        temp++;
        freq.put((char)c,temp);
        //System.out.println(freq.get((char)c));
      }

      // This would be a good place for STEP 1, putting the code that keeps track of
      // the frequency of each character, storing it in your HashMap member variable.
    }
    System.out.println(freq);

    // You have to close the file, just the way you would in Python.
    fr.close();

    // Here's where you want to do your STEP 2. Don't forget that you
    // will want to create a separate class for HuffTree outside this class.
    System.out.println("This actually runs");
    //iterating through the hashmap in order to make an instance of hufftree for every character
    for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
    Character key = entry.getKey();
    Integer value = entry.getValue();
    System.out.println("Loop reached");
    //setting the values of the top of the instance of hufftree
    Node node = new Node(null,null,null,key,value);
    Hufftree temp = new Hufftree(node, 0, 0);

    //adding each instance of hufftree to the priority queue
    pQueue.add(temp);
    }

//extract the two minimum values here
    while(pQueue.size() > 1){
      //get the first min

      Hufftree t1 = pQueue.peek();
      Node t1_top = pQueue.peek().top;
      pQueue.poll();
      //get the second min
      Hufftree t2 = pQueue.peek();
      Node t2_top = pQueue.peek().top;
      pQueue.poll();

      //make a new node
      Node fake = new Node(t1_top,t2_top,null,'\u0000',t1_top.weight+t2_top.weight);//should be good
      t1_top.parent=fake;
      t2_top.parent=fake;
      Hufftree t = new Hufftree(fake,t1.size+t2.size+1,t1_top.weight+t2_top.weight);//check size later
      //t.top.leftNode = t1;
      //t.top.rightNode = t2;

      //t.top.weight = t1.top.weight + t2.top.weight;
      pQueue.add(t);
    }



    // USING FileIOC TO WRITE OUT TO A BINARY FILE

    // Here is some code that shows you how to use FileIOC to write out a binary file.
    // Below are just some examples of what you can do with BinaryOut. You don't need
    // to use all of this code, of course.

    // BTW, this is where you'll be doing STEP 3.

    // FileIOC uses the S&W BinaryOut class, which lets you write to binary a file.
    // FileIOC will automatically open a file with the same name as your input file
    // but it will replace .txt with .zip.
    BinaryOut bo = fioc.openBinaryOutputFile();

    // The BinaryOut class has a write() method that can print out all the
    // primitive data types to binary. Here are some examples, below.

    // This line prints out the "signature" two bytes that begin one of our zip files.
    // A short is a two-byte datatype, so use a short.
    short s = 0x0bc0;
    bo.write(s);


    // Suppose after you build your Huffman binary tree, the code for T
    // ends up being 101. Here's one way you can print that out to the binary file.
    String t = "101";
    int i = Integer.parseInt(t, 2);
    bo.write(i, t.length());


    // A boolean is the only type that has two values, so you can pass in a boolean
    // to the write() method of the BinaryOut class to print out a single bit.
    // Here's how you could write out 101 using individual bits.
    bo.write(true);
    bo.write(false);
    bo.write(true);


    // One last thing: files have to be written in bytes not bits. The BinaryOut
    // write() method will bunch your bits together into bytes for you, but
    // when you write out your compressed file, so you might end up with some
    // number of bits that isn't divisible by 8. Thus, at the end of your file,
    // you need to "flush" the output, as shown below. flush() will add 0s at
    // the end of your file until you complete a byte.
    bo.flush();

    // Do this to close the output stream.
    bo.close();


  }

}
