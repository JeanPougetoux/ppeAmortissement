package interfaceGraphique;

import java.awt.event.*;
import javax.swing.*;

import amortissements.Credit;


import exceptions.MonException;


public class BoutonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private FenetrePrincipale fenetre;
	private int typeCredit;

	/**
	 * Classe li�e au bouton et aux actions qu'il d�clenche
	 */

	public BoutonAction(FenetrePrincipale fenetre, String texte) {
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
	 * R�initialise les champs et la visibilit� du message d'erreur comme dans
	 * la page principale
	 */

	public void initializeNul() {
		fenetre.getErreur().setVisible(false);
		String chaine = "";
		chaine += "Taux : ";
		chaine += "  Dur�e : ";
		chaine += "  Montant emprunt� : ";
		chaine += "  Annuit�e maximale : ";
		fenetre.getLabelBottom().setText(chaine);
	}

	/**
	 * V�rifie que les textFields sont au bon nombre (de 3) et qu'ils ne
	 * contiennent pas de lettre, si tout est bon lance l'impression des valeurs
	 */

	public void initializeValeurs() {
		if (getNumeric(fenetre.getTaux().getText()) && getNumeric(fenetre.getDuree().getText())
				&& getNumeric(fenetre.getEmprunt().getText()) && getNumeric(fenetre.getRemboursement().getText())) {
			if(verifNombreValeurs() >= 3){
				if(verifNombreValeurs() == 4){
					if(!generationCredit4Valeurs()){
						MessageErreur.ErreurValeurs(fenetre);
						fenetre.clearTableau();
					}
				}
				else{	
					generationCredit();
				}
			}
			else{
				MessageErreur.ErreurNombre(fenetre);
				fenetre.clearTableau();
			}
		} else {
			MessageErreur.ErreurLettre(fenetre);
		}
	}

	/**
	 * Met les valeurs valid�es dans les champs s'il n'y a pas d'erreurs
	 */

	public void printValeurs(Credit cred) {
		String chaine = "";
		chaine += "Taux : " + cred.taux() * 100;
		chaine += "%,  Dur�e : " + cred.duree();
		chaine += " ann�e(s),  Montant emprunt� : " + cred.montantEmprunte();
		chaine += " euros,  Annuit�e maximale : ";
		chaine += cred.annuiteMaximale() + " euros";
		fenetre.getLabelBottom().setText(chaine);
	}

	/**
	 * Permet de v�rifier le nombre des valeurs saisies par l'utilisateur
	 */

	private int verifNombreValeurs(){
		int compteur = 0;
		
		if(fenetre.getTaux().getText().length() > 0)
			compteur++;
		if(fenetre.getDuree().getText().length() > 0)
			compteur++;
		if(fenetre.getEmprunt().getText().length() > 0)
			compteur++;
		if(fenetre.getRemboursement().getText().length() > 0)
			compteur++;
		return compteur;
	}

	/*
	 * Voit quel est le type du cr�dit
	 */
	private void defineTypeCredit() {
		if (fenetre.getCombo().getSelectedItem() == "Amortissement constant")
			typeCredit = 1;
		else if (fenetre.getCombo().getSelectedItem() == "Annuit�es constantes")
			typeCredit = 2;
	}

	/*
	 * Permet la g�n�ration d'un cr�dit en utilisant les valeur de notre fen�tre
	 * principale
	 */

	private void generationCredit(){
		defineTypeCredit();
		double taux = 0;
		double remboursement = 0;
		double emprunt = 0;
		int duree = 0;
		if(fenetre.getTaux().getText().length() != 0){
			taux = Double.parseDouble(fenetre.getTaux().getText());
			taux /= 100;
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
		try
		{
			if(fenetre.getEmprunt().getText().length() == 0){
				cred = Credit.calculeMontantEmprunte(typeCredit, remboursement, taux, duree);
				printValeurs(cred);			
				fenetre.drawTableau(cred.getTableauAmortissement().getTableau());
			}
			else if(fenetre.getDuree().getText().length() == 0){
				cred = Credit.calculeDuree(typeCredit, emprunt, remboursement, taux);
				printValeurs(cred);		
				fenetre.drawTableau(cred.getTableauAmortissement().getTableau());
			}
			else if(fenetre.getRemboursement().getText().length() == 0){
				cred = Credit.calculeAnnuiteMaximale(typeCredit, emprunt, taux, duree);
				printValeurs(cred);
				fenetre.drawTableau(cred.getTableauAmortissement().getTableau());
			}
			else if(fenetre.getTaux().getText().length() == 0){
				cred = Credit.calculeTaux(typeCredit, emprunt, remboursement, duree);
				printValeurs(cred);
				fenetre.drawTableau(cred.getTableauAmortissement().getTableau());
			}
		}
		catch(MonException e)
		{
			System.out.println(e);
		}
	}
	
	/*
	 * V�rifie si le cr�dit poss�de 4 bonnes valeurs
	 */
	private boolean generationCredit4Valeurs(){
		double taux = Double.parseDouble(fenetre.getTaux().getText());
		taux /= 100;
		int duree = Integer.parseInt(fenetre.getDuree().getText());
		double emprunt = Double.parseDouble(fenetre.getEmprunt().getText());
		double remboursement = Double.parseDouble(fenetre.getRemboursement().getText());
		defineTypeCredit();
		Credit cred ;
		try {
			cred = Credit.calculeAnnuiteMaximale(typeCredit, emprunt, taux, duree);
			if(cred.annuiteMaximale() == remboursement){
				printValeurs(cred);
				fenetre.drawTableau(cred.getTableauAmortissement().getTableau());
				return(cred.annuiteMaximale() == remboursement);
			}
		} catch (MonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
	}

	/**
	 * V�rifie si une cha�ne contient autre chose que des caract�res num�riques
	 */

	private boolean getNumeric(String chaine) {
		for (int i = 0; i < chaine.length(); i++) {
			if (!Character.isDigit(chaine.charAt(i)) && chaine.charAt(i) != '.')
				return false;
		}
		return true;
	}
}