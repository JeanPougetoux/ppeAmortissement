package interfaceGraphique;

import java.awt.Color;

/**
 * Classe MessageErreur correspond a tous les
 * message d'erreurs qui peuvent apparaitre
 * dans la fenetre principale.
 * @author Jean
 *
 */
public class MessageErreur {

	/**
	 * Erreur trop petit nombre de champs remplis.
	 * @param fenetre
	 */
	public static void ErreurNombre(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Il faut au minimum trois champs.");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Erreur s'il y a un caractere dans un des champs.
	 * @param fenetre
	 */
	public static void ErreurLettre(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Veuillez ne saisir que des valeurs unitaires");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Erreur si les quatre valeurs ne sont pas bonnes.
	 * @param fenetre
	 */
	public static void ErreurValeurs(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Les valeurs ne correspondent pas");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
	
	/**
	 * Message d'erreur si le tableau d'amortissement est vide.
	 * @param fenetre
	 */
	public static void TableauVide(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Le tableau d'amortissement est vide, export impossible");
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}

	/**
	 * Message si l'export est bien effectue.
	 * @param fenetre
	 */
	public static void BienEnregistrer(FenetrePrincipale fenetre){
		fenetre.getErreur().setText("Tableau bien exporte");
		fenetre.getErreur().setForeground(Color.green);
		fenetre.getErreur().setVisible(true);
	}

	/**
	 * Message d'erreur qui s'adapte selon la chaine prise en parametre.
	 * Il est donc lie aux exceptions.
	 * @param fenetre
	 * @param texte
	 */
	public static void ErreurException(FenetrePrincipale fenetre, String texte){
		fenetre.getErreur().setText(texte);
		fenetre.getErreur().setForeground(Color.red);
		fenetre.getErreur().setVisible(true);
	}
}
