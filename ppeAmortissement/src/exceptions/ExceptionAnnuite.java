package exceptions;

import interfaceGraphique.Application;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;
/**
 * 
 * Creer une exception concernant les erreurs d'annuite 
 * @author thomas
 *
 */
@SuppressWarnings("serial")

public class ExceptionAnnuite extends MonException {

	String message;
	double annuite;
	public ExceptionAnnuite(String message,double annuite){
	
		this.message = message;
		this.annuite = annuite;
		MessageErreur.ErreurException(Application.fenetre, message+ " annuitee saisie : "+this.annuite);
	}
	
	@Override
	public String toString() {
		return this.message + this.annuite;
//		MessageErreur.ErreurException(Application.fenetre, message);
	}
}
