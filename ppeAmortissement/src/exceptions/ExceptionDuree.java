package exceptions;

@SuppressWarnings("serial")
public class ExceptionDuree extends MonException {

	String message;
	int duree;
	public ExceptionDuree(String message,int duree){
	
		this.message = message;
		this.duree = duree;
	}
	
	@Override
	public String toString() {
		return this.message + this.duree;
	}
}
