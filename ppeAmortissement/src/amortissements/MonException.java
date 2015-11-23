package amortissements;

public class MonException extends Exception{
	public MonException()
    {
        System.out.println("Exception monException has been raised...");
    }
	public String toString()
    {
      return "You tried to do an illegal assignement !";
    }
}
