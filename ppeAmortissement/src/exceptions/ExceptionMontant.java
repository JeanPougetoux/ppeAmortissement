package exceptions;

import interfaceGraphique.FenetrePrincipale;
import interfaceGraphique.MessageErreur;

public class ExceptionMontant extends MonException{

	String message;
	double montant;
	public ExceptionMontant(String message,double montant, FenetrePrincipale f){
	
		this.message = message;
		this.montant = montant;
		MessageErreur.ErreurException(f, message);
	}
	
	@Override
	public String toString() {
		return this.message + this.montant;
	}
}
