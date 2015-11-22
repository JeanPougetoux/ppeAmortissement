package amortissements;

import exceptions.ExceptionCalculeAnnuiteMaximale;
import exceptions.ExceptionCalculeDuree;
import exceptions.ExceptionCalculeMontant;
import exceptions.ExceptionCalculeTaux;

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
			int duree)throws ExceptionCalculeTaux
	{
		
		if (typeCredit == AMORTISSEMENT_CONSTANTS){
			
				if(!testCalculeTaux(montantEmprunte,annuiteMaximale,duree)){
					throw new ExceptionCalculeTaux("les données saisies ne sont pas correctes");
				}
			
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
			if (!testCalculeTaux(montantEmprunte,annuiteMaximale,duree))
				throw new ExceptionCalculeTaux("Impossible de calculer le taux, données incorrect");
			
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
	public static boolean testCalculeTaux(double montant, double annuite, int duree){
		
		return (duree > 0 )&& (montant > annuite) && (annuite > (montant / duree));
	}

	
	/**
	 * Retourne un crédit en calculant automatiquement la durée.
	 */
	
	public static Credit calculeDuree(int typeCredit, 
			double montantEmprunte, double annuiteMaximale,
			double taux) throws ExceptionCalculeDuree
	{
		if (!testCalculeDuree(montantEmprunte, annuiteMaximale, taux))
			throw new ExceptionCalculeDuree("Impossible de calculer la durée, données inorrectes !");
		if(typeCredit == AMORTISSEMENT_CONSTANTS){
			
			
			System.out.println("duréé nécéssaire pour l'amortissement total :"+"\n" +montantEmprunte/(annuiteMaximale - montantEmprunte*taux)+"\n");
			int duree = (int)(Math.round(montantEmprunte/(annuiteMaximale - montantEmprunte*taux)));
			System.out.println("durée arrondie"+"\n"+duree);
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
public static boolean testCalculeDuree(double montant, double annuite, double taux){
		
		return (taux > 0 && taux < 1 )&& (montant > annuite)&& (annuite > montant*taux) && (annuite >0);
	}
	

	/**
	 * Retourne un crédit en calculant automatiquement le montant
	 * 	qu'il est possible d'emprunter.
	 */
	
	public static Credit calculeMontantEmprunte(int typeCredit, 
			double annuiteMaximale,	double taux, int duree)throws ExceptionCalculeMontant
	{
		if (!testCalculeMontantEmprunte(annuiteMaximale,taux,duree))
			throw new ExceptionCalculeMontant("impossible de calculer le montant : données incorrectes !");
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
	public static boolean testCalculeMontantEmprunte(double annuite, double taux, int duree){
		
		return (duree > 0)&& (taux > 0 && taux <1) && (annuite > 0);
	}

	/**
	 * Retourne un crédit en calculant automatiquement
	 * l'annuité maximale.
	 */
	
	public static Credit calculeAnnuiteMaximale(int typeCredit, 
			double montantEmprunte,	double taux, int duree)throws ExceptionCalculeAnnuiteMaximale
	{
		if (!testCalculeAnnuiteMaximale(montantEmprunte, taux, duree))
			throw new ExceptionCalculeAnnuiteMaximale("Calcul de l'annuite impossible, données éronnées !");
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
	public static boolean testCalculeAnnuiteMaximale(double montant, double taux, int duree){
		
		return (duree > 0)&& (taux > 0 && taux <1) && (montant > 0)&& (montant > duree);
	}

	
	@Override
	public String toString() {
		return "" + typeCredit + ", " + montantEmprunte + ", " + annuiteMaximale()
		+ ", " + taux + ", " + duree;
	} 
}
