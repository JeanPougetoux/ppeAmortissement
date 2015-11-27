package exceptions;

public class ExceptionMontant extends MonException{

	String message;
	double montant;
	public ExceptionMontant(String message,double montant){
	
		this.message = message;
		this.montant = montant;
	}
	
	@Override
	public String toString() {
		return this.message + this.montant;
	}
}
