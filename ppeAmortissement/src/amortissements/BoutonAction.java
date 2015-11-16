package amortissements;

import java.awt.event.*;
import javax.swing.*;

public class BoutonAction extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private FenetrePrincipale fenetre;
	private int typeCredit;

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
		String chaine = "";
		chaine += "Taux : ";
		chaine += "  Durée : ";
		chaine += "  Montant emprunté : ";
		chaine += "  Montant de remboursement : ";
		fenetre.getLabelBottom().setText(chaine);
	}
	
	/**
	 * Vérifie que les textFields sont au bon nombre (de 3) et qu'ils ne contiennent pas de lettre,
	 * si tout est bon lance l'impression des valeurs
	 */
	
	public void initializeValeurs(){
		if(getNumeric(fenetre.getTaux().getText()) && getNumeric(fenetre.getDuree().getText()) &&
				getNumeric(fenetre.getEmprunt().getText()) && getNumeric(fenetre.getRemboursement().getText())){
			
			if(verifNombreValeurs()){
				generationCredit();
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
		String chaine = "";
		chaine += "Taux : " + fenetre.getTaux().getText();
		chaine += "%,  Durée : " + fenetre.getDuree().getText();
		chaine += "année(s),  Montant emprunté : " + fenetre.getEmprunt().getText();
		chaine += "euros,  Montant de remboursement : " ;
		chaine += fenetre.getRemboursement().getText() + " euros";
		fenetre.getLabelBottom().setText(chaine);
	}
	
	/**
	 * Permet de vérifier le nombre des valeurs saisies par l'utilisateur
	 */
	
	private boolean verifNombreValeurs(){
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
	
	/* 
	 * Voit quel est le type du crédit
	 */
	private void defineTypeCredit(){
		if(fenetre.getCombo().getSelectedItem() == "Amortissement constant")
			typeCredit = 1;
		else if(fenetre.getCombo().getSelectedItem() == "Annuitées constantes")
			typeCredit = 2;
	}
	
	/*
	 * Permet la génération d'un crédit en utilisant les valeur de notre fenêtre principale
	 */
	
	private void generationCredit(){
		defineTypeCredit();
		double taux = 0;
		double remboursement = 0;
		double emprunt = 0;
		int duree = 0;
		if(fenetre.getTaux().getText().length() != 0){
			taux = Double.parseDouble(fenetre.getTaux().getText());
		}
		if(fenetre.getDuree().getText().length() != 0){
			duree = Integer.parseInt(fenetre.getDuree().getText());
		}
		if(fenetre.getEmprunt().getText().length() != 0){
			emprunt = Double.parseDouble(fenetre.getEmprunt().getText());
		}
		if(fenetre.getRemboursement().getText().length() != 0){
			remboursement = Double.parseDouble(fenetre.getRemboursement().getText());
		}
		Credit cred;
		System.out.println("ok2");
		if(fenetre.getEmprunt().getText().length() == 0){
			cred = Credit.calculeMontantEmprunte(typeCredit, remboursement, taux, duree);
			System.out.println(cred.getTableauAmortissement());
		}
		else if(fenetre.getDuree().getText().length() == 0){
			cred = Credit.calculeDuree(typeCredit, emprunt, remboursement, taux);
			System.out.println(cred.getTableauAmortissement());
		}
		else if(fenetre.getRemboursement().getText().length() == 0){
			cred = Credit.calculeAnnuiteMaximale(typeCredit, emprunt, taux, duree);
			System.out.println(cred.getTableauAmortissement());
		}
		else if(fenetre.getTaux().getText().length() == 0){
			cred = Credit.calculeTaux(typeCredit, emprunt, remboursement, duree);
			System.out.println(cred.getTableauAmortissement());
		}
	}
		
	/**
	 * Vérifie si une chaîne contient autre chose que des caractères numériques
	 */
	
	private boolean getNumeric(String chaine){
		for(int i = 0; i < chaine.length(); i++)
		{
			if(!Character.isDigit(chaine.charAt(i)) && chaine.charAt(i) != '.')
				return false;
		}
		return true;
	}
}
