package exceptions;

import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

@SuppressWarnings("serial")
public class ExceptionAnnuite extends MonException {

	String message;
	double annuite;
	public ExceptionAnnuite(String message,double annuite, FenetrePrincipale f){
	
		this.message = message;
		this.annuite = annuite;
		MessageErreur.ErreurException(f, message);
	}
	
	@Override
	public String toString() {
		return this.message + this.annuite;
//		MessageErreur.ErreurException(Application.fenetre, message);
	}
}
