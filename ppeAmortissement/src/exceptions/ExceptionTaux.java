package exceptions;

import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

public class ExceptionTaux extends MonException{

	String message;
	double taux;
	public ExceptionTaux(String message,double taux, FenetrePrincipale f){
	
		this.message = message;
		this.taux = taux;
		MessageErreur.ErreurException(f, message);
	}
	
	@Override
	public String toString() {
		return this.message + this.taux;
	}
}
