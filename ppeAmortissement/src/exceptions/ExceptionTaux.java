package exceptions;

import interfaceGraphique.Application;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

/**
 * Creer une exception concernant les erreurs de taux 
 * @author thomas
 *
 */
public class ExceptionTaux extends MonException{

	String message;
	double taux;
	public ExceptionTaux(String message,double taux){
	
		this.message = message;
		this.taux = taux;
		MessageErreur.ErreurException(Application.fenetre, message);
	}
	
	@Override
	public String toString() {
		return this.message + this.taux;
	}
}
