package p2p_Final_Project;

public class LinkedListQueue
{
 /*
  Josh Greenwell
  CISC 370 Networking
  2/20/15

  This class allows you to create and use a linked list queue

 Class Variables:

 Constructor:
  public LinkedListQueue()

 Methods:
  public void removeAll()
   sets the head and rear to null

  public boolean isEmpty()
   returns true if there is nothing in the queue

  public Object peek()
   returns the data in the front node

  public Object deQueue()
   removes the front element of the queue and
   returns it's data

  public void enQueue(Object data)
   adds a node to the end of the queue

  public void enQueue(LinkedListQueue queue)
   Adds a queue to the end of the current queue

 Modification History:
  February 20, 2015
   Original Version

  */

 private Node front;
 private Node rear;

 public LinkedListQueue()
 {
  //Initializes variables
  this.front = null;
  this.rear = null;
 }

 public void removeAll()
 {
  //sets teh rear and front to null
  this.front = null;
  this.rear = null;
 }

 public boolean isEmpty(){return front == null;}

 public Object peek()
 {
  //returns the front's data
  if(this.isEmpty()){throw new QueueUnderflowException();}
  return front.getData();
 }

 public Object deQueue()
 {
  //removes the front node and returns it's data
  if(this.isEmpty()){throw new QueueUnderflowException();}
  Node temp = this.front;
  front = front.getNext();
  if(this.isEmpty()){this.rear = null;}
  return temp.getData();
 }

 public void enQueue(Object data)
 {
  //adds a node to the end of the queue
  Node temp = new Node(data, null);
  if(this.isEmpty())
  {
   this.front = temp;
   this.rear = temp;
  }
  else
  {
   this.rear.setNext(temp);
   this.rear = temp;
  }
 }

 public void enQueue(LinkedListQueue queue)
 {
  
  //adds a queue to the end of the queue
  if(queue == null){throw new IllegalArgumentException();}
  if(queue == this){throw new IllegalArgumentException();}

  if(this.isEmpty())
  {
   this.front = queue.front;
   this.rear = queue.rear;
  }
  else
  {
   if(!queue.isEmpty())
   {
    this.rear.setNext(queue.front);
    this.rear = queue.rear;
   }
  }

  queue.removeAll();
 }

 private class Node
 {
  /*
   Josh Greenwell
   CISC 370 Networking
   2/20/15

   This class structures a node to be used in the linked list

  Class Variables:
   private Object data
    The data held in each node

   private Node next
    The next node after this node

  Constructor:
   public Node(Object data, Node next)
    Initializes instance variables

  Methods:
   public Object getData()
    Returns the data value

   public Node getNext()
    returns the next value

   public void setNext(Node next)
    sets the next value to the variable passed in.


  Modification History:
   February 20, 2015
    Original Version

   */
  private Object data;
  private Node next;

  public Node(Object data, Node next)
  {
   
   //Initializes variables
   this.data = data;
   this.next = next;
  }

  public Object getData(){return data;}
  public Node getNext(){return next;}

  public void setNext(Node next){this.next = next;}
 }
}
