package amortissements;

import java.awt.event.*;
import javax.swing.*;

public class BoutonAction extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private FenetrePrincipale fenetre;

	/**
	 * Classe li�e au bouton et aux actions qu'il d�clenche
	 */
	
	public BoutonAction(FenetrePrincipale fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
	}
 
	/**
	 * Repr�sente l'action li�e au bouton
	 */
	
	public void actionPerformed(ActionEvent e) { 
		fenetre.getErreur().setVisible(false);
		initializeNul();
		initializeValeurs();
	} 
	
	/**
	 * R�initialise les champs et la visibilit� du message d'erreur
	 * comme dans la page principale
	 */
	
	public void initializeNul(){
		fenetre.getErreur().setVisible(false);
		fenetre.getModifTaux().setText("Taux : ");
		fenetre.getModifDuree().setText(",  Dur�e : ");
		fenetre.getModifEmprunt().setText(",  Montant emprunt� : ");
		fenetre.getModifRemboursement().setText(",  Montant de remboursement : ");
	}
	
	/**
	 * V�rifie que les textFields sont au bon nombre (de 3) et qu'ils ne contiennent pas de lettre,
	 * si tout est bon lance l'impression des valeurs
	 */
	
	public void initializeValeurs(){
		if(getNumeric(fenetre.getTaux().getText()) && getNumeric(fenetre.getDuree().getText()) &&
				getNumeric(fenetre.getEmprunt().getText()) && getNumeric(fenetre.getRemboursement().getText())){
			
			if(verifNombreValeurs()){
				printValeurs();
			}
			else{
				MessageErreur.ErreurNombre(fenetre);
			}
		}
		else{
			MessageErreur.ErreurLettre(fenetre);
		}
	}
	
	/**
	 * Met les valeurs valid�es dans les champs s'il n'y a pas d'erreurs
	 */
	
	public void printValeurs(){
		fenetre.getModifTaux().setText("Taux : " + fenetre.getTaux().getText());
		fenetre.getModifDuree().setText("%,  Dur�e : " + fenetre.getDuree().getText());
		fenetre.getModifEmprunt().setText("ann�e(s),  Montant emprunt� : " + fenetre.getEmprunt().getText());
		fenetre.getModifRemboursement().setText("euros,  Montant de remboursement : " 
				+ fenetre.getRemboursement().getText() + " euros");
	}
	
	/**
	 * Permet de v�rifier le nombre des valeurs saisies par l'utilisateur
	 */
	
	public boolean verifNombreValeurs(){
		int compteur = 0;
		boolean verif = false;
		
		if(fenetre.getTaux().getText().length() > 0)
			compteur++;
		if(fenetre.getDuree().getText().length() > 0)
			compteur++;
		if(fenetre.getEmprunt().getText().length() > 0)
			compteur++;
		if(fenetre.getRemboursement().getText().length() > 0)
			compteur++;
		if(compteur >= 3)
			verif = true;
		return verif;
	}
	
	/**
	 * V�rifie si une cha�ne contient autre chose que des caract�res num�riques
	 */
	
	public boolean getNumeric(String chaine){
		for(int i = 0; i < chaine.length(); i++)
		{
			if(!Character.isDigit(chaine.charAt(i)))
				return false;
		}
		return true;
	}
}
