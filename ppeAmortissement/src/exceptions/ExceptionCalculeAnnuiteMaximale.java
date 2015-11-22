package exceptions;

public class ExceptionCalculeAnnuiteMaximale extends Exception{

	String message;
	public ExceptionCalculeAnnuiteMaximale(String message){
	
		this.message = message;
	}
	
	@Override
	public String toString() {
		return this.message;
	}
}
