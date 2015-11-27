package exceptions;

@SuppressWarnings("serial")
public class ExceptionAnnuite extends MonException {

	String message;
	double annuite;
	public ExceptionAnnuite(String message,double annuite){
	
		this.message = message;
		this.annuite = annuite;
	}
	
	@Override
	public String toString() {
		return this.message + this.annuite;
	}
}
