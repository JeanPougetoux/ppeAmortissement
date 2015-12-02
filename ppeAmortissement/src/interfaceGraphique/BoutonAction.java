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
	 * Classe liee au bouton et aux actions qu'il declenche
	 */

	public BoutonAction(FenetrePrincipale fenetre, String texte) {
		super(texte);
		this.fenetre = fenetre;
	}

	/**
	 * Represente l'action liee au bouton
	 */

	public void actionPerformed(ActionEvent e) {
		fenetre.getErreur().setVisible(false);
		fenetre.clearTableau();
		initializeNul();
		initializeValeurs();
	}

	/**
	 * Reinitialise les champs et la visibilite du message d'erreur comme dans
	 * la page principale
	 */

	public void initializeNul() {
		fenetre.getErreur().setVisible(false);
		String chaine = "";
		chaine += "Taux : ";
		chaine += "  Duree : ";
		chaine += "  Montant emprunte : ";
		chaine += "  Annuitee maximale : ";
		fenetre.getLabelBottom().setText(chaine);
	}

	/**
	 * Verifie que les textFields sont au bon nombre (de 3) et qu'ils ne
	 * contiennent pas de lettre, si tout est bon lance l'impression des valeurs
	 */

	public void initializeValeurs() {
		if (getNumeric(fenetre.getTaux().getText()) && getNumeric(fenetre.getDuree().getText())
				&& getNumeric(fenetre.getEmprunt().getText()) 
				&& getNumeric(fenetre.getRemboursement().getText())) {
			if(verifNombreValeurs() >= 3){
				if(verifNombreValeurs() == 4){
					if(!generationCredit4Valeurs()){
						MessageErreur.ErreurValeurs(fenetre);
						fenetre.clearTableau();
					}
				}
				else{	
					if(fenetre.getRemboursement().getText().length() != 0 
							&& fenetre.getEmprunt().getText().length() != 0){
						if(Double.parseDouble(fenetre.getRemboursement().getText()) > 
							Double.parseDouble(fenetre.getEmprunt().getText())){
							//MessageErreur.ErreurAnnuite(fenetre);
							getValeurs();
							fenetre.clearTableau();
						}
						else{
							getValeurs();
						}
					}
					else{
						getValeurs();
					}
				}
			}
			else{
				MessageErreur.ErreurNombre(fenetre);
				fenetre.clearTableau();
			}
		} 
		else {
			MessageErreur.ErreurLettre(fenetre);
			fenetre.clearTableau();
		}
	}

	/**
	 * Met les valeurs validees dans les champs s'il n'y a pas d'erreurs
	 */

	public void printValeurs(Credit cred) {
		String chaine = "";
		chaine += "Taux : " + (double)Math.round((cred.taux() * 100)*100)/100;
		chaine += "%,  Duree : " + cred.duree();
		chaine += " annee(s),  Montant emprunte : " + (double)Math.round(cred.montantEmprunte()*100)/100;
		chaine += " euros,  Annuitee maximale : ";
		chaine += (double)Math.round(cred.annuiteMaximale()*100)/100 + " euros";
		fenetre.getLabelBottom().setText(chaine);
	}

	/**
	 * Permet de verifier le nombre des valeurs saisies par l'utilisateur
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
	 * Voit quel est le type du credit
	 */
	private void defineTypeCredit() {
		if (fenetre.getCombo().getSelectedItem() == "Amortissement constant")
			typeCredit = 1;
		else if (fenetre.getCombo().getSelectedItem() == "Annuitees constantes")
			typeCredit = 2;
	}

	/*
	 * Verifie les valeurs tapees par l'utilisateur et initialise les variables
	 */

	private void getValeurs(){
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
		try{
			generationCredit(taux, remboursement, emprunt, duree);
		}
		catch(MonException e){
			
		}
	}
	
	private void generationCredit(double taux, double remboursement, double emprunt, 
			int duree) throws MonException{
		Credit cred;
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
	/*
	 * Verifie si le credit possede 4 bonnes valeurs
	 */
	private boolean generationCredit4Valeurs(){
		double taux = (Double.parseDouble(fenetre.getTaux().getText()))/100;
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
	 * Verifie si une chaene contient autre chose que des caracteres numeriques
	 */

	private boolean getNumeric(String chaine) {
		for (int i = 0; i < chaine.length(); i++) {
			if (!Character.isDigit(chaine.charAt(i)) && chaine.charAt(i) != '.')
				return false;
		}
		return true;
	}
}