package exceptions;

import interfaceGraphique.Application;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

/**
 * Creer une exception concernant les erreurs de duree */
@SuppressWarnings("serial")
public class ExceptionDuree extends MonException {

	String message;
	int duree;
	public ExceptionDuree(String message,int duree){
	
		this.message = message;
		this.duree = duree;
		MessageErreur.ErreurException(Application.fenetre, message);
	}
	
	@Override
	public String toString() {
		return this.message + this.duree;
	}
}
