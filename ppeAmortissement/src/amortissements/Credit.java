package amortissements;

/**
 * Représente un crédit.
 */

public class Credit 
{
	public final static int AMORTISSEMENT_CONSTANTS = 1,
			ANNUITES_CONSTANTES = 2;
	public int typeCredit,duree;
	public double montantEmprunte,annuiteMaximale,taux;

	/**
	 * Créée un crédit.
	 */
	
	Credit (int typeCredit, double montantEmprunte, double annuiteMaximale,
			double taux, int duree)
	{
		if (typeCredit == 1){
			this.typeCredit = AMORTISSEMENT_CONSTANTS;
		}
		else{
			this.typeCredit = ANNUITES_CONSTANTES;
		}
		this.montantEmprunte = montantEmprunte;
		this.annuiteMaximale = annuiteMaximale;
		this.taux = taux;
		this.duree = duree;
		
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
		
		if (typeCredit == 1){
			double amortissement = montantEmprunte/duree;
			double interet = (annuiteMaximale - amortissement);
			double taux = interet/montantEmprunte;
			return new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
			
		}
		else if (typeCredit == 2){
			double annuite = annuiteMaximale +1;
			double tauxProbable = 1;
			double tauxMax = tauxProbable;
			double tauxMin;

			while (annuite != annuiteMaximale){
				
				annuite = AnnuiteCalculeTaux(montantEmprunte,duree,tauxProbable);
				if (annuite > annuiteMaximale){
					tauxMax = tauxProbable;
					tauxProbable = tauxProbable/2;
					
				}
				else if (annuite < annuiteMaximale){
					tauxMin = tauxProbable;
					tauxProbable = tauxProbable + ((tauxMax - tauxProbable)/2);
					
				}
			}
			return new Credit(typeCredit, montantEmprunte, annuiteMaximale, tauxProbable, duree);
		
			
		}
		return null;
	}
	public static double AnnuiteCalculeTaux(double montant, int duree, double taux){
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
		if(typeCredit == 1){
			int duree = (int)(montantEmprunte/(annuiteMaximale - montantEmprunte*taux));
			Credit cred = new Credit(typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
			return cred;
		}
		else if (typeCredit == 2){
			int duree = 0;
			while(montantEmprunte>0){
				montantEmprunte = montantEmprunte - (annuiteMaximale - montantEmprunte * taux);
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
		if (typeCredit == 1){
			double montantEmprunte = annuiteMaximale / (taux + 1/(double)(duree));
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		else if (typeCredit == 2){
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
		if (typeCredit == 1){
			double annuiteMaximale = (montantEmprunte*taux)+(montantEmprunte / duree);
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		else if (typeCredit == 2){
			double annuiteMaximale = montantEmprunte * taux / (1 - Math.pow(1+taux, -duree));
			return new Credit (typeCredit, montantEmprunte, annuiteMaximale, taux, duree);
		}
		return null;
	}
	public static void main(String[] args){
		/*Credit cred = calculeMontantEmprunte(2,22391.8773,0.06,7);
		double montant = cred.montantEmprunte();
		System.out.println("Le montant emprunte est de  : " + montant + "euros");
		//double a = AnnuiteCalculeTaux(20000,4,0.05);
		*/
		Credit mega = calculeAnnuiteMaximale(2, 35000,	0.07, 5);
		System.out.println(mega.annuiteMaximale());
		
		Credit omega = calculeTaux (2,35000,8536.17,5);
		System.out.println("letaux du pret est de :"+omega.taux()*100+" %");
	}
}
