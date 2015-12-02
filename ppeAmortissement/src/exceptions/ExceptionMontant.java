package exceptions;

import interfaceGraphique.Application;
import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

/**
 * Creer une exception concernant les erreurs de montant
 * @author thomas
 *
 */
@SuppressWarnings({ "serial", "unused" })
public class ExceptionMontant extends MonException{

	String message;
	double montant;
	public ExceptionMontant(String message,double montant){
	
		this.message = message;
		this.montant = montant;
		MessageErreur.ErreurException(Application.fenetre, message+ "montant saisi :"+this.montant);
	}
	
	@Override
	public String toString() {
		return this.message + this.montant;
	}
}
