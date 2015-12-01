package amortissements;

/**
 * Represente le tableau d'amortissement d'un credit.
 */

public class TableauAmortissement 
{
	
	/**
	 * Creer le tableau d'amortissement du credit passe en parametre.
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
	 * Retourne la duree de l'amortissement.
	 */
	
	public int getNbLignes()
	{
		return this.credit.duree();
	}
	
	/**
	 * Retourne la i-eme ligne du tableau d'amortissement.
	 * Les indices commencent a  0.
	 */
	
	public Ligne getLigne(int i)
	{
		return tableau[i];		
	}
	
	/**
	 * Retourne le tableau de ligne qui correspond au tableau d'amortissement final
	 */
	public Ligne[] getTableau()
	{
		return tableau;
	}
	

	public String toString() {
		String message = "";
		for (int i=0;i<getNbLignes();i++){
			message += tableau[i].toString()+"\n";
		}
		return message;
	}
}
