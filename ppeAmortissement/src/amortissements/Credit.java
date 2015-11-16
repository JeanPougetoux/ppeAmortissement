package amortissements;

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
	
	Credit (int typeCredit, double montantEmprunte, double annuiteMaximale,
			double taux, int duree)
	{
		
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
			this.typeCredit = AMORTISSEMENT_CONSTANTS;
		}
		else{
			this.typeCredit = ANNUITES_CONSTANTES;
		}
		this.montantEmprunte = (double)Math.round(montantEmprunte * 100) / 100 ;
		this.annuiteMaximale = (double)Math.round(annuiteMaximale * 100) / 100 ;
		this.taux = (double)Math.round(taux * 100) / 100;
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
			int duree)
	{
		
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
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
			
			while (annuite > annuiteMaximale +1 || annuite < annuiteMaximale -1){
				
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
		double annuite = (montant*taux)/(1-Math.pow(1+taux, -duree));
		return annuite;
	}

	
	/**
	 * Retourne un crédit en calculant automatiquement la durée.
	 */
	
	public static Credit calculeDuree(int typeCredit, 
			double montantEmprunte, double annuiteMaximale,
			double taux)
	{
		if(typeCredit == AMORTISSEMENT_CONSTANTS){
			int duree = (int)(montantEmprunte/(annuiteMaximale - montantEmprunte*taux));
			Credit cred = new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
			return cred;
		}
		else if (typeCredit == ANNUITES_CONSTANTES){
			int duree = 0;
			double montant = montantEmprunte;
			while((montant<-2) || (montant>2)){
				montant = montant - (annuiteMaximale - montant * taux);
				duree ++;
			}
			return new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		return null;
	}
	

	/**
	 * Retourne un crédit en calculant automatiquement le montant
	 * 	qu'il est possible d'emprunter.
	 */
	
	public static Credit calculeMontantEmprunte(int typeCredit, 
			double annuiteMaximale,	double taux, int duree)
	{
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
			double montantEmprunte = annuiteMaximale / (taux + 1/(double)(duree));
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		else if (typeCredit == ANNUITES_CONSTANTES){
			double montantEmprunte = (annuiteMaximale*(1-Math.pow(1+taux, -duree)))/(taux);
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		return null;
	}

	/**
	 * Retourne un crédit en calculant automatiquement
	 * l'annuité maximale.
	 */
	
	public static Credit calculeAnnuiteMaximale(int typeCredit, 
			double montantEmprunte,	double taux, int duree)
	{
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
}
