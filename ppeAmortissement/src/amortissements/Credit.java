package amortissements;

import exceptions.ExceptionAnnuite;

import exceptions.ExceptionDuree;
import exceptions.ExceptionMontant;
import exceptions.ExceptionTaux;
import exceptions.MonException;

/**
 * Représente un crédit.
 */

public class Credit 
{ 
	public final static int AMORTISSEMENT_CONSTANTS = 1,
			ANNUITES_CONSTANTES = 2;
	private int typeCredit,duree;
	private double montantEmprunte,annuiteMaximale,taux;

	/**
	 * Créée un crédit.
	 */
	
	private Credit (int typeCredit, double montantEmprunte, double annuiteMaximale,
			double taux, int duree)
	{
		if (typeCredit == AMORTISSEMENT_CONSTANTS || typeCredit == ANNUITES_CONSTANTES)
			this.typeCredit = typeCredit;
		
		
		this.montantEmprunte = montantEmprunte;//(double)Math.round(montantEmprunte * 100) / 100 ;
		this.annuiteMaximale = annuiteMaximale; //(double)Math.round(annuiteMaximale * 100) / 100 ;
		this.taux = taux;//(double)Math.round(taux * 1000000) / 1000000;
		this.duree = duree;
		
	}
	/**
	 * Retourne le type du credit
	 */
	public int typeCredit(){
		return typeCredit;
	}
	
	/**
	 * Retourné le montant emprunté.
	 */
	
	public double montantEmprunte()
	{
		return montantEmprunte;
	}
	
	/**
	 * Retourne le montant de la plus grande annuité. 
	 */
	
	public double annuiteMaximale()
	{
		return annuiteMaximale;
	}
	
	/**
	 * Retourne le taux du crédit.
	 */
	
	public double taux()
	{
		return taux;
	}
	
	/**
	 * Retourne nombre d'annuités à verser.
	 */
	
	public int duree()
	{
		return duree;
	}
	
	/**
	 * Retourne le tableau d'amortissement du crédit.
	 */
	
	public TableauAmortissement getTableauAmortissement()
	{
		return new TableauAmortissement(this);
	}
	
	/**
	 * Retourne un crédit en calculant automatiquement le taux.
	 */
	
	public static Credit calculeTaux(int typeCredit, 
			double montantEmprunte, double annuiteMaximale,
			int duree)throws MonException
	{
		
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
			
				testCalculeTaux(montantEmprunte,annuiteMaximale,duree);
					
			
				double amortissement = montantEmprunte/duree;
				double interet = (annuiteMaximale - amortissement);
				double taux = interet/montantEmprunte;
				return new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
			
			
		}
		else if (typeCredit == ANNUITES_CONSTANTES){
			double annuite = annuiteMaximale +10;
			double tauxProbable = 1;
			double tauxMax = tauxProbable;
			double tauxMin = 0;
			testCalculeTaux(montantEmprunte,annuiteMaximale,duree);
				
			
			while (Math.abs(annuite - annuiteMaximale ) > 0.00001){
				
				annuite = calculTauxAnnuiteConstante(montantEmprunte,duree,tauxProbable);
				if (annuite > annuiteMaximale){
					tauxMax = tauxProbable;
				}
				else if (annuite < annuiteMaximale){
					tauxMin = tauxProbable;
				}
				tauxProbable = (tauxMax + tauxMin)/2;
			}
			return new Credit(typeCredit, montantEmprunte, annuiteMaximale, tauxProbable, duree);
		}
		return null;
	}
	/*
	 * Réalise le calcule de l'annuité en fonction du montant,de la durée et du taux
	 */
	public static double calculTauxAnnuiteConstante(double montant, int duree, double taux){
		return (montant*taux)/(1-Math.pow(1+taux, -duree));
		
	}
	/*
	 * Vérifie que les données renseignées sont logiques, sinon lève une exception
	 */
	public static void testCalculeTaux(double montant, double annuite, int duree)throws MonException{
		if(duree<=0){
			throw new ExceptionDuree("Impossible de calculer le taux, dur�e n�gative !", duree);
		}
		else if(montant < annuite){
			throw new ExceptionMontant("Impossible de calculer le taux, l'annuit�e doit �tre inf�rieure au montant !",montant);
		}
		else if (annuite < (montant/duree)){
			throw new ExceptionAnnuite("Impossible de calculer le taux, L'amortissement est sup�rieur � l'annuit�e !", annuite);
		}
		
	}

	
	/**
	 * Retourne un crédit en calculant automatiquement la durée.
	 */
	
	public static Credit calculeDuree(int typeCredit, 
			double montantEmprunte, double annuiteMaximale,
			double taux) throws MonException
	{
		testCalculeDuree(montantEmprunte, annuiteMaximale, taux);
		if(typeCredit == AMORTISSEMENT_CONSTANTS){
			
			double dureeNonArrondie = montantEmprunte/(annuiteMaximale - montantEmprunte*taux);
			
			int duree = (int)(Math.round(montantEmprunte/(annuiteMaximale - montantEmprunte*taux)));
			double testDuree = duree - dureeNonArrondie;
			
			if (testDuree < 0)
				duree = (int)(Math.round(montantEmprunte/(annuiteMaximale - montantEmprunte*taux)+0.5));;
			
			Credit cred = new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
			return cred;
		}
		else if (typeCredit == ANNUITES_CONSTANTES){
			int duree = 1;
			double montant = montantEmprunte;
			double interet = montant * taux;
			double amortissement = annuiteMaximale - interet;
			while( montant>amortissement){
				montant = montant - (amortissement);
				interet = montant * taux;
				amortissement = annuiteMaximale - interet;
				duree ++;
			}
			
			
			return new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		return null;
	}
	/*
	 *  Vérifie que les données renseignées sont logiques, sinon lève une exception
	 */
	public static void testCalculeDuree(double montant, double annuite, double taux) throws MonException{
		if (taux <= 0 || taux >= 1){
			throw new ExceptionTaux("Impossible de calculer la dur�e,taux incorrect!",taux);
		}
		else if (montant < annuite){
			throw new ExceptionMontant("Impossible de calculer la dur�e, montant trop faible !",montant);
		}
		else if (annuite < montant*taux){
			throw new ExceptionAnnuite("Impossible de calculer la dur�e, l'annuit�e doit �tre sup�rieure aux int�rets !",annuite);
	
		}
		else if (annuite < 0){
			throw new ExceptionAnnuite("Impossible de calculer la dur�e, annuit�e n�gative !",annuite);
			
		}
		}
	

	
	
	/**
	 * Retourne un crédit en calculant automatiquement le montant
	 * 	qu'il est possible d'emprunter.
	 * @throws ExceptionCalculeMontant 
	 */
	
	public static Credit calculeMontantEmprunte(int typeCredit, 
			double annuiteMaximale,	double taux, int duree) throws MonException
	{
		testCalculeMontantEmprunte(annuiteMaximale,taux,duree);
			
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
			double montantEmprunte = annuiteMaximale / (taux + 1/(double)(duree));
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		else if (typeCredit == ANNUITES_CONSTANTES){
			double montantEmprunte = annuiteMaximale * (1 - Math.pow(1 + taux, -duree)) / taux;
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 *  Vérifie que les données renseignées sont logiques, sinon lève une exception
	 */
	public static void testCalculeMontantEmprunte(double annuite, double taux, int duree)throws MonException{
		if (duree <= 0){
			throw new ExceptionDuree("impossible de calculer le montant : la dur�e doit �tre positive !",duree);
		}
		else if (taux <=0 || taux >=1){
			throw new ExceptionTaux("impossible de calculer le montant : taux incorrect !",taux);
		}
		else if (annuite < 0)
			throw new ExceptionAnnuite("impossible de calculer le montant : L'annuit�e doit �tre positive",annuite);
	}

	/**
	 * Retourne un crédit en calculant automatiquement
	 * l'annuité maximale.
	 */
	
	public static Credit calculeAnnuiteMaximale(int typeCredit, 
			double montantEmprunte,	double taux, int duree)throws MonException
	{
		testCalculeAnnuiteMaximale(montantEmprunte, taux, duree);
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
			double annuiteMaximale = (montantEmprunte*taux)+(montantEmprunte / duree);
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		else if (typeCredit == ANNUITES_CONSTANTES){
			double annuiteMaximale = montantEmprunte * taux / (1 - Math.pow(1+taux, -duree));
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		return null;
	}
	/*
	 *  Vérifie que les données renseignées sont logiques, sinon lève une exception
	 */
	public static void testCalculeAnnuiteMaximale(double montant, double taux, 
			int duree)throws MonException{
		if (duree <= 0){
			throw new ExceptionDuree ("calcul impossible, dur�e n�gative",duree);
		}
		else if (taux <= 0 || taux >=1){
			throw new ExceptionTaux ("calcul impossible, taux incorrect",taux);
		}
		else if (montant <=0){
			throw new ExceptionMontant("calcul impossible, montant n�gatif",montant);
		}
		else if (montant <duree){
			throw new ExceptionDuree ("calcul impossible, la dur�e ne peut �tre plus grande que le montant ",duree);
		}
		
	}

	
	@Override
	public String toString() {
		return "" + typeCredit + ", " + montantEmprunte + ", " + annuiteMaximale()
		+ ", " + taux + ", " + duree;
	} 
}
