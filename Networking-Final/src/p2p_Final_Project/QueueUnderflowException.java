package p2p_Final_Project;

public class QueueUnderflowException extends RuntimeException
{
 /*
  Josh Greenwell
  CISC 370 Networking
  2/20/15

  Exception for the queue having no values

 Class Variables:

 Constructor:
  public QueueUnderflowException()
   calls the super

 Methods:

 Modification History:
  February 20, 2015
   Original Version

  */
 private static final long serialVersionUID = 1;
 public QueueUnderflowException(){ super(); }
}
