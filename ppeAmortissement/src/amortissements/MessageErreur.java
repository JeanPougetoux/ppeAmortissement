package amortissements;

import java.awt.Color;

public class MessageErreur {

	/**
	 * Erreur nombre de champs remplis
	 */
	public static void ErreurNombre(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Il faut au minimum trois champs.");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Erreur caractère dans les champs
	 */
	public static void ErreurLettre(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Veuillez ne saisir que des valeurs unitaires");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Export bien effecté
	 */
	public static void BienEnregistrer(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Tableau bien exporté");
		fenetre.getErreur().setForeground(Color.green);
		fenetre.getErreur().setVisible(true);
	}
}
