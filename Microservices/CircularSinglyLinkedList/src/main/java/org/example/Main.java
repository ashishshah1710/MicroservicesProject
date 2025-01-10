package org.example;

class Node {

  int data;
  Node next;

  Node(int value) {
    data = value;
    next = null;
  }
}
public class Insert{
  public Node insertInEmptyList(Node last ,int data){

    if(last != null){
      System.out.print("List is not Empty");
      return last;
    }

  }
  public Node insertAtBegining(Node last,int data){

  }
  public Node insertAtEnd(Node last, int data){

  }
  public Node insertAtLocation(Node last,int data, int location){

  }
}
public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}