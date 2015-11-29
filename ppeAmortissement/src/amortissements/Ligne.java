package amortissements;

/**
 * Ligne du tableau d'amortissement d'un crédit.
 */

public class Ligne 
{
	/**
	 * Crée une ligne à partir des valeurs passées en paramètres.
	 */
	private int annee;
	private double capitalInitial,interets,amortissements,annuite,capitalFinal;

	Ligne(int annee, double capitalInitial, double interets, 
			double amortissements, double annuite, double capitalFinal)
	{
		
		this.annee = annee;
		this.capitalInitial = capitalInitial;
		this.interets = interets;
		this.amortissements = amortissements;
		this.annuite = annuite;
		this.capitalFinal = capitalFinal;
	}

	/**
	 * Retourne l'indice de l'année concernée par la ligne.
	 * Les indices commencent à 1.
	 */
	
	public int getAnnee()
	{
		return annee;
	}
	
	/**
	 * Retourne le montant du capital restant dû au début 
	 * de la période.
	 */
	
	public double getCapitalInitial()
	{
		return capitalInitial;
	}
	
	/**
	 * Retourne le montant des intérêts versés pendant la période.
	 */
	
	public double getInterets()
	{
		return interets;
	}

	/**
	 * Retourne le montant du capital amorti pendant la période.
	 */
	
	public double getAmortissements()
	{
		return amortissements;
	}

	/**
	 * Retourne le montant de l'annuité pendant la période.
	 */
	
	public double getAnnuite()
	{
		return annuite;
	}
	
	/**
	 * Retourne le montant du capital restant dû à la fin
	 * de la période.
	 */
	
	public double getCapitalFinal()
	{
		return capitalFinal;
	}
	
	/**
	 * Retourne la première ligne du tableau d'amortissement pour
	 * le crédit passé en paramètre.
	 */
	
	public static Ligne premiereLigne(Credit credit)
	{
		double amortissement = credit.annuiteMaximale()-(credit.montantEmprunte()*credit.taux());
		return new Ligne (0, credit.montantEmprunte(), credit.montantEmprunte()*credit.taux(), 
		amortissement, credit.annuiteMaximale(),credit.montantEmprunte()-amortissement);
	}

	/**
	 * Retourne la ligne suivant la ligne courante pour le 
	 * passé en paramètre. Retoure null si la ligne courante est
	 * la dernière ligne.
	 */
	
	public Ligne ligneSuivante(Credit credit)
	{
		int annee = this.annee + 1;
		if (annee < credit.duree()){
			if (credit.typeCredit() == Credit.AMORTISSEMENT_CONSTANTS){
				double capitalInital = this.capitalFinal;
				double interets = capitalInital * credit.taux();
				double amortissement = this.amortissements;
				double annuite = interets + amortissement;
				double capiFinal = capitalInital - amortissement;
				if((annee == credit.duree()-1) && (capiFinal != 0)){
					amortissement = capitalInital;
					annuite = interets + amortissement;
					capiFinal = capitalInital - amortissement;
				}
					
						
				
				return new Ligne (annee,capitalInital,interets,amortissement,annuite,capiFinal);
			}
			else{
				double 	annuiteFutur = annuite;
				double initial = (double)Math.round(this.capitalFinal * 100) / 100 ;
				double interet = (double)Math.round((this.capitalFinal*credit.taux()) * 100) / 100;
				double amort = (double)Math.round((this.annuite - interet) * 100) / 100;
				double capifinal = (double)Math.round((initial - amort) * 100) / 100;
				if ((annee == credit.duree()-1) && (capifinal != 0))
					
					if (capifinal > 0){
						amort = amort - capifinal;
						annuiteFutur = amort + interet;
						capifinal = 0;
					}
					else{
						amort = amort + capifinal;
						annuiteFutur = amort + interet;
						capifinal = 0;
					}
				return new Ligne (annee,initial,interet,
						amort,annuiteFutur,capifinal);
			}	
		}
		else{
			return null;
		}
	}
	public String toString() {
		return "| "+getAnnee()+ "    |"+ (double)Math.round(getCapitalInitial() * 100) / 100 + "    |"+ (double)Math.round(getInterets() * 100) / 100 + "    |"
	+ (double)Math.round(getAmortissements() * 100) / 100 + "    |"+ (double)Math.round(getAnnuite() * 100) / 100 + "    |"+ (double)Math.round(getCapitalFinal() * 100) / 100 + "    |";
	}
}
