package exceptions;

public class ExceptionTaux extends MonException{

	String message;
	double taux;
	public ExceptionTaux(String message,double taux){
	
		this.message = message;
		this.taux = taux;
	}
	
	@Override
	public String toString() {
		return this.message + this.taux;
	}
}
