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
		if (annee <= credit.duree()){
			if (credit.typeCredit() == credit.AMORTISSEMENT_CONSTANTS){
				double capitalInital = this.capitalFinal;
				double interets = capitalInital * credit.taux();
				double amortissement = this.amortissements;
				double annuite = interets + amortissement;
				double capitalFinal = capitalInitial - amortissement;
				return new Ligne (annee,capitalInitial,interets,amortissement,annuite,capitalFinal);
			}
			else{
				double capitalInital = this.capitalFinal;
				double annuite = this.annuite;
				double interets = capitalInital * credit.taux();
				double amortissement = annuite - interets;
				
				double capitalFinal = capitalInitial - amortissement;
				return new Ligne (annee,capitalInitial,interets,amortissement,annuite,capitalFinal);
			
			}
			
		}
		else{
			return null;
		}
		
	}
}
