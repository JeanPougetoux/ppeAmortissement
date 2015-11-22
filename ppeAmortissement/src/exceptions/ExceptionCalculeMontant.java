package exceptions;

public class ExceptionCalculeMontant extends Exception {

	String message;
	public ExceptionCalculeMontant(String message){
		this.message = message;
	}
	@Override
	public String toString() {
		return this.message;
	}
}
