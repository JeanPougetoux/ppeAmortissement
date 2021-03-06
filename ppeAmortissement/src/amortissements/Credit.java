package amortissements;

import exceptions.ExceptionAnnuite;

import exceptions.ExceptionDuree;
import exceptions.ExceptionMontant;
import exceptions.ExceptionTaux;
import exceptions.MonException;


/**
 * Represente un credit.
 * @author thomas
 *
 */

public class Credit 
{ 
	public final static int AMORTISSEMENT_CONSTANTS = 1,
			ANNUITES_CONSTANTES = 2;
	private int typeCredit,duree;
	private double montantEmprunte,annuiteMaximale,taux;

	
	/**
	 * Cree un credit
	 * @param typeCredit
	 * @param montantEmprunte
	 * @param annuiteMaximale
	 * @param taux
	 * @param duree
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
	 * @return int
	 */
	public int typeCredit(){
		return typeCredit;
	}
	
	
	/**
	 *  Retourne le montant emprunte.
	 * @return double
	 */
	
	public double montantEmprunte()
	{
		return montantEmprunte;
	}
	
	
	/**
	 * Retourne le montant de la plus grande annuite.
	 * @return double
	 */
	public double annuiteMaximale()
	{
		return annuiteMaximale;
	}
	
	
	/**
	 * Retourne le taux du credit
	 * @return double
	 */
	public double taux()
	{
		return taux;
	}
	
	
	/**
	 *  Retourne nombre d'annuites à verser.
	 * @return int 
	 */
	public int duree()
	{
		return duree;
	}
	
	/**
	 * Retourne le tableau d'amortissement du credit.
	 * @return TableauAmortissement
	 */
	public TableauAmortissement getTableauAmortissement()
	{
		return new TableauAmortissement(this);
	}
	

	/**
	 * Retourne un credit en calculant automatiquement le taux.
	 * @param typeCredit
	 * @param montantEmprunte
	 * @param annuiteMaximale
	 * @param duree
	 * @return Credit
	 * @throws MonException
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
	
	/**
	 * Realise le calcule de l'annuite en fonction du montant,de la duree et du taux
	 * @param montant
	 * @param duree
	 * @param taux
	 * @return double
	 */
	public static double calculTauxAnnuiteConstante(double montant, int duree, double taux){
		return (montant*taux)/(1-Math.pow(1+taux, -duree));
		
	}

	
	/**
	 * Verifie que les donnees renseignees sont logiques, sinon leve une exception
	 * @param montant
	 * @param annuite
	 * @param duree
	 * @throws MonException
	 */
	public static void testCalculeTaux(double montant, double annuite, int duree)throws MonException{
		if(duree<=0){
			throw new ExceptionDuree("Impossible de calculer le taux, duree negative !", duree);
		}
		else if(montant < annuite){
			throw new ExceptionMontant("Impossible de calculer le taux, l'annuitee doit etre inferieure au montant !",montant);
		}
		else if (annuite < (montant/duree)){
			throw new ExceptionAnnuite("Impossible de calculer le taux, L'amortissement est superieur e l'annuitee !", annuite);
		}
		
	}

	
	
	/**
	 * Retourne un credit en calculant automatiquement la duree.
	 * @param typeCredit
	 * @param montantEmprunte
	 * @param annuiteMaximale
	 * @param taux
	 * @return Credit
	 * @throws MonException
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

	
	/**
	 *  Verifie que les donnees renseignees sont logiques, sinon leve une exception
	 * @param montant
	 * @param annuite
	 * @param taux
	 * @throws MonException
	 */
	public static void testCalculeDuree(double montant, double annuite, double taux) throws MonException{
		if (taux <= 0 || taux >= 1){
			throw new ExceptionTaux("Impossible de calculer la duree,taux incorrect!",taux);
		}
		else if (montant < annuite){
			throw new ExceptionMontant("Impossible de calculer la duree, montant trop faible par rapport a l'annuite !",montant);
		}
		else if (annuite < montant*taux){
			throw new ExceptionAnnuite("Impossible de calculer la duree, l'annuitee doit etre superieure aux interets !",annuite);
	
		}
		else if (annuite < 0){
			throw new ExceptionAnnuite("Impossible de calculer la duree, annuitee negative !",annuite);
			
		}
		}
	

	
	
	
	/**
	 * Retourne un credit en calculant automatiquement le montant
	 * 	qu'il est possible d'emprunter.
	 * @param typeCredit
	 * @param annuiteMaximale
	 * @param taux
	 * @param duree
	 * @return credit
	 * @throws MonException
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
	

	/**
	 * Verifie que les donnees renseignees sont logiques, sinon leve une exception
	 * @param annuite
	 * @param taux
	 * @param duree
	 * @throws MonException
	 */
	public static void testCalculeMontantEmprunte(double annuite, double taux, int duree)throws MonException{
		if (duree <= 0){
			throw new ExceptionDuree("impossible de calculer le montant : la duree doit etre positive !",duree);
		}
		else if (taux <=0 || taux >=1){
			throw new ExceptionTaux("impossible de calculer le montant : taux incorrect !",taux);
		}
		else if (annuite < 0)
			throw new ExceptionAnnuite("impossible de calculer le montant : L'annuitee doit etre positive",annuite);
	}

	
	/**
	 * Retourne un credit en calculant automatiquement
	 * l'annuite maximale.
	 * @param typeCredit
	 * @param montantEmprunte
	 * @param taux
	 * @param duree
	 * @return Credit
	 * @throws MonException
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


	/**
	 * Verifie que les donnees renseignees sont logiques, sinon leve une exception
	 * @param montant
	 * @param taux
	 * @param duree
	 * @throws MonException
	 */
	public static void testCalculeAnnuiteMaximale(double montant, double taux, 
			int duree)throws MonException{
		if (duree <= 0){
			throw new ExceptionDuree ("calcul impossible, duree negative",duree);
		}
		else if (taux <= 0 || taux >=1){
			throw new ExceptionTaux ("calcul impossible, taux incorrect",taux);
		}
		else if (montant <=0){
			throw new ExceptionMontant("calcul impossible, montant negatif",montant);
		}
		else if (montant <duree){
			throw new ExceptionDuree ("calcul impossible, la duree ne peut etre plus grande que le montant ",duree);
		}
		
	}

	
	@Override
	public String toString() {
		return "" + typeCredit + ", " + montantEmprunte + ", " + annuiteMaximale()
		+ ", " + taux + ", " + duree;
	} 
}
