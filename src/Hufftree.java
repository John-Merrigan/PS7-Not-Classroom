import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import java.util.*;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.SortedSet;


public class Hufftree implements Comparable<Hufftree>{

    public Node top;
    public int size;
    public int weight;


    public Hufftree(){
      //stuff
    }
    public Hufftree(Node top, int size, int weight){
        this.top = top;
        this.size = size;
        this.weight = weight;
    }

    public static class Node{

        Node leftNode;
        Node rightNode;
        Node parent;
        char character;
        int weight;

        Node(Node leftNode, Node rightNode, Node parent, char character, int weight){
          this.leftNode = leftNode;
          this.rightNode = rightNode;
          this.parent = parent;
          this.character = character;
          this.weight = weight;
        }

    }

    public String transverseHuffTree(){
      String s = "";
      return s;
    }

    public int compareTo(Hufftree x){
      if(this.top.weight < x.top.weight){
        return 1;
      }
      else if(this.top.weight == x.top.weight){
        return 0;
      }
      else{
        return -1;
      }
    }

}
