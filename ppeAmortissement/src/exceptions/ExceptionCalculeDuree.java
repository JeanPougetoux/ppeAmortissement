package exceptions;

public class ExceptionCalculeDuree extends Exception{

	String message;
	public ExceptionCalculeDuree(String message){
		this.message = message;
	}
	@Override
	public String toString() {
		return this.message;
	}
	
	
}
