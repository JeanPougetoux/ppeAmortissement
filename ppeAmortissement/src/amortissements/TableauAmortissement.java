package amortissements;


/**
 * Represente le tableau d'amortissement d'un credit.
 * @author thomas
 *
 */
public class TableauAmortissement 
{
	public final static int COLONNES_TABLEAU = 6;
	
	Ligne[] tableau;
	Credit credit;
	/**
	 *  Creer le tableau d'amortissement du credit passe en parametre.
	 * @param credit
	 */
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
	 * @return int 
	 */
	public int getNbLignes()
	{
		return this.credit.duree();
	}
	
	
	/**
	 * Retourne la i-eme ligne du tableau d'amortissement.
	 * Les indices commencent a  0.
	 * @param i
	 * @return Ligne
	 */
	public Ligne getLigne(int i)
	{
		return tableau[i];		
	}
	
	
	/**
	 *  Retourne le tableau de ligne qui correspond au tableau d'amortissement final
	 * @return Ligne[]
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
