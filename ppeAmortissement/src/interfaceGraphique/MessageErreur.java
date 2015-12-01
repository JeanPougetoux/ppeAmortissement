package interfaceGraphique;

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
	 * Erreur caractere dans les champs
	 */
	public static void ErreurLettre(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Veuillez ne saisir que des valeurs unitaires");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Erreur si les quatres valeurs saisies ne sont pas bonnes
	 */
	public static void ErreurValeurs(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Les valeurs ne correspondent pas");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Erreur si l'annuite max est superieur au montant emprunte
	 */
	public static void ErreurAnnuite(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("L'annuitee maximale est superieur au montant emprunte");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Export bien effecte
	 */
	public static void BienEnregistrer(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Tableau bien exporte");
		fenetre.getErreur().setForeground(Color.green);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Message d'erreur pour toutes les exceptions
	 */
	public static void ErreurException(FenetrePrincipale fenetre, String texte){
		fenetre.getErreur().setText(texte);
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
}
