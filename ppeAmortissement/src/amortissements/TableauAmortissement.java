package amortissements;

/**
 * ReprÃ©sente le tableau d'amortissement d'un crÃ©dit.
 */

public class TableauAmortissement 
{
	
	/**
	 * CrÃ©e le tableau d'amortissement du crÃ©dit passÃ© en paramÃ¨tre.
	 */
	public final static int COLONNES_TABLEAU = 6;
	
	Ligne[] tableau;
	Credit credit;
	public TableauAmortissement(Credit credit)
	{
		this.credit = credit;
		tableau = new Ligne[credit.duree()];
		tableau[0] = Ligne.premiereLigne(credit);
		
		for (int i=1;i<credit.duree();i++){
			
			tableau[i] = tableau[i-1].ligneSuivante(credit);	
		}
	}
	
	/**
	 * Retourne la durÃ©e de l'amortissement.
	 */
	
	public int getNbLignes()
	{
		return this.credit.duree();
	}
	
	/**
	 * Retourne la i-Ã¨me ligne du tableau d'amortissement.
	 * Les indices commencent Ã  0.
	 */
	
	public Ligne getLigne(int i)
	{
		return tableau[i];		
	}
	
	/**
	 * Retourne une reprÃ©sentation du tableau au format 
	 * chaÃ®ne de caractÃ¨res.
	 */
	
	public String toString() {
		String message = "";
		for (int i=0;i<getNbLignes();i++){
			message += tableau[i].toString()+"\n";
		}
		return message;
	}
}
