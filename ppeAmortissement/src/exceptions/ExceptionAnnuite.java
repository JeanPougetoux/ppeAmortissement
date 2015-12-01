package exceptions;

import interfaceGraphique.Application;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

@SuppressWarnings("serial")
/**
 * Creer une exception concernant les erreurs d'annuite */
public class ExceptionAnnuite extends MonException {

	String message;
	double annuite;
	public ExceptionAnnuite(String message,double annuite){
	
		this.message = message;
		this.annuite = annuite;
		MessageErreur.ErreurException(Application.fenetre, message);
	}
	
	@Override
	public String toString() {
		return this.message + this.annuite;
//		MessageErreur.ErreurException(Application.fenetre, message);
	}
}
