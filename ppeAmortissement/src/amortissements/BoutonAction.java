package amortissements;

import java.awt.event.*;
import javax.swing.*;

public class BoutonAction extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private FenetrePrincipale fenetre;

	/**
	 * Classe liée au bouton et aux actions qu'il déclenche
	 */
	
	public BoutonAction(FenetrePrincipale fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
	}
 
	/**
	 * Représente l'action liée au bouton
	 */
	
	public void actionPerformed(ActionEvent e) { 
		fenetre.getErreur().setVisible(false);
		initializeNul();
		initializeValeurs();
	} 
	
	/**
	 * Réinitialise les champs et la visibilité du message d'erreur
	 * comme dans la page principale
	 */
	
	public void initializeNul(){
		fenetre.getErreur().setVisible(false);
		fenetre.getModifTaux().setText("Taux : ");
		fenetre.getModifDuree().setText(",  Durée : ");
		fenetre.getModifEmprunt().setText(",  Montant emprunté : ");
		fenetre.getModifRemboursement().setText(",  Montant de remboursement : ");
	}
	
	/**
	 * Vérifie que les textFields sont au bon nombre (de 3) et qu'ils ne contiennent pas de lettre,
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
	 * Met les valeurs validées dans les champs s'il n'y a pas d'erreurs
	 */
	
	public void printValeurs(){
		fenetre.getModifTaux().setText("Taux : " + fenetre.getTaux().getText());
		fenetre.getModifDuree().setText("%,  Durée : " + fenetre.getDuree().getText());
		fenetre.getModifEmprunt().setText("année(s),  Montant emprunté : " + fenetre.getEmprunt().getText());
		fenetre.getModifRemboursement().setText("euros,  Montant de remboursement : " 
				+ fenetre.getRemboursement().getText() + " euros");
	}
	
	/**
	 * Permet de vérifier le nombre des valeurs saisies par l'utilisateur
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
	 * Vérifie si une chaîne contient autre chose que des caractères numériques
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
