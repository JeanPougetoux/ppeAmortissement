package exceptions;

public class ExceptionCalculeTaux extends Exception{
	String message;
	public ExceptionCalculeTaux(String message)
    {
     this.message = message;   
    }
	public String toString()
    {
      return this.message;
    }
}
