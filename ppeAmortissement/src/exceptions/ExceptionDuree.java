package exceptions;

import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

@SuppressWarnings("serial")
public class ExceptionDuree extends MonException {

	String message;
	int duree;
	public ExceptionDuree(String message,int duree, FenetrePrincipale f){
	
		this.message = message;
		this.duree = duree;
		MessageErreur.ErreurException(f, message);
	}
	
	@Override
	public String toString() {
		return this.message + this.duree;
	}
}
