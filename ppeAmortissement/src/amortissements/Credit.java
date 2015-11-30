package amortissements;

import exceptions.ExceptionAnnuite;

import exceptions.ExceptionDuree;
import exceptions.ExceptionMontant;
import exceptions.ExceptionTaux;
import exceptions.MonException;
import interfaceGraphique.FenetrePrincipale;

/**
 * ReprÃ©sente un crÃ©dit.
 */

public class Credit 
{ 
	public final static int AMORTISSEMENT_CONSTANTS = 1,
			ANNUITES_CONSTANTES = 2;
	private int typeCredit,duree;
	private double montantEmprunte,annuiteMaximale,taux;

	/**
	 * CrÃ©Ã©e un crÃ©dit.
	 */
	
	Credit (int typeCredit, double montantEmprunte, double annuiteMaximale,
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
	 * RetournÃ© le montant empruntÃ©.
	 */
	
	public double montantEmprunte()
	{
		return montantEmprunte;
	}
	
	/**
	 * Retourne le montant de la plus grande annuitÃ©. 
	 */
	
	public double annuiteMaximale()
	{
		return annuiteMaximale;
	}
	
	/**
	 * Retourne le taux du crÃ©dit.
	 */
	
	public double taux()
	{
		return taux;
	}
	
	/**
	 * Retourne nombre d'annuitÃ©s Ã  verser.
	 */
	
	public int duree()
	{
		return duree;
	}
	
	/**
	 * Retourne le tableau d'amortissement du crÃ©dit.
	 */
	
	public TableauAmortissement getTableauAmortissement()
	{
		return new TableauAmortissement(this);
	}
	
	/**
	 * Retourne un crÃ©dit en calculant automatiquement le taux.
	 */
	
	public static Credit calculeTaux(FenetrePrincipale f, int typeCredit, 
			double montantEmprunte, double annuiteMaximale,
			int duree)throws MonException
	{
		
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
			
				testCalculeTaux(f, montantEmprunte,annuiteMaximale,duree);
					
			
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
			testCalculeTaux(f, montantEmprunte,annuiteMaximale,duree);
				
			
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
	
	public static double calculTauxAnnuiteConstante(double montant, int duree, double taux){
		return (montant*taux)/(1-Math.pow(1+taux, -duree));
		
	}
	public static void testCalculeTaux(FenetrePrincipale f, double montant, double annuite, int duree)throws MonException{
		if(duree<=0){
			throw new ExceptionDuree("Impossible de calculer le taux, durée négative !", duree, f);
		}
		else if(montant < annuite){
			throw new ExceptionMontant("Impossible de calculer le taux, l'annuitée doit être inférieure au montant !",montant, f);
		}
		else if (annuite < (montant/duree)){
			throw new ExceptionAnnuite("Impossible de calculer le taux, L'amortissement est supérieur à l'annuitée !", annuite, f);
		}
		
	}

	
	/**
	 * Retourne un crÃ©dit en calculant automatiquement la durÃ©e.
	 */
	
	public static Credit calculeDuree(FenetrePrincipale f, int typeCredit, 
			double montantEmprunte, double annuiteMaximale,
			double taux) throws MonException
	{
		testCalculeDuree(f, montantEmprunte, annuiteMaximale, taux);
		if(typeCredit == AMORTISSEMENT_CONSTANTS){
			
			double dureeNonArrondie = montantEmprunte/(annuiteMaximale - montantEmprunte*taux);
			System.out.println("durée nécessaire pour l'amortissement total :"+"\n" +dureeNonArrondie+"\n");
			int duree = (int)(Math.round(montantEmprunte/(annuiteMaximale - montantEmprunte*taux)));
			double testDuree = duree - dureeNonArrondie;
			System.out.println("ecart = "+ -testDuree);
			System.out.println("durée arrondie"+"\n"+duree);
			if (testDuree < 0)
				duree = (int)(Math.round(montantEmprunte/(annuiteMaximale - montantEmprunte*taux)+0.5));;
			System.out.println("nouvelle durée arrondie "+duree);
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
			
			System.out.println(montant);
			System.out.println(duree);
			return new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		return null;
	}
public static void testCalculeDuree(FenetrePrincipale f, double montant, double annuite, double taux) throws MonException{
		if (taux <= 0 || taux >= 1){
			throw new ExceptionTaux("Impossible de calculer la durée,taux incorrect!",taux, f);
		}
		else if (montant < annuite){
			throw new ExceptionMontant("Impossible de calculer la durée, montant trop faible !",montant, f);
		}
		else if (annuite < montant*taux){
			throw new ExceptionAnnuite("Impossible de calculer la durée, l'annuitée doit être supérieure aux intérets !",annuite, f);
	
		}
		else if (annuite < 0){
			throw new ExceptionAnnuite("Impossible de calculer la durée, annuitée négative !",annuite, f);
			
		}
		}
	

	/**
	 * Retourne un crÃ©dit en calculant automatiquement le montant
	 * 	qu'il est possible d'emprunter.
	 * @throws ExceptionCalculeMontant 
	 */
	
	public static Credit calculeMontantEmprunte(FenetrePrincipale f, int typeCredit, 
			double annuiteMaximale,	double taux, int duree) throws MonException
	{
		testCalculeMontantEmprunte(f, annuiteMaximale,taux,duree);
			
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
	public static void testCalculeMontantEmprunte(FenetrePrincipale f, double annuite, double taux, int duree)throws MonException{
		if (duree <= 0){
			throw new ExceptionDuree("impossible de calculer le montant : la durée doit être positive !",duree, f);
		}
		else if (taux <=0 || taux >=1){
			throw new ExceptionTaux("impossible de calculer le montant : taux incorrect !",taux, f);
		}
		else if (annuite < 0)
			throw new ExceptionAnnuite("impossible de calculer le montant : L'annuitée doit être positive",annuite, f);
	}

	/**
	 * Retourne un crÃ©dit en calculant automatiquement
	 * l'annuitÃ© maximale.
	 */
	
	public static Credit calculeAnnuiteMaximale(FenetrePrincipale f, int typeCredit, 
			double montantEmprunte,	double taux, int duree)throws MonException
	{
		testCalculeAnnuiteMaximale(f, montantEmprunte, taux, duree);
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
	public static void testCalculeAnnuiteMaximale(FenetrePrincipale f, double montant, double taux, 
			int duree)throws MonException{
		if (duree <= 0){
			throw new ExceptionDuree ("calcul impossible, durée négative",duree, f);
		}
		else if (taux <= 0 || taux >=1){
			throw new ExceptionTaux ("calcul impossible, taux incorrect",taux, f);
		}
		else if (montant <=0){
			throw new ExceptionMontant("calcul impossible, montant négatif",montant, f);
		}
		else if (montant <duree){
			throw new ExceptionDuree ("calcul impossible, la durée ne peut être plus grande que le montant ",duree, f);
		}
		
	}

	
	@Override
	public String toString() {
		return "" + typeCredit + ", " + montantEmprunte + ", " + annuiteMaximale()
		+ ", " + taux + ", " + duree;
	} 
}
