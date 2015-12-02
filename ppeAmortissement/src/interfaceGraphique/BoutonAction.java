package interfaceGraphique;

import java.awt.event.*;
import javax.swing.*;

import amortissements.Credit;


import exceptions.MonException;

/**
 * Classe BoutonAction est utilisée au clic sur le bouton "valider" de
 * la fenetre principale, elle hérite de la classe AbstractAction.
 * @author Jean
 *
 */
@SuppressWarnings("serial")
public class BoutonAction extends AbstractAction {

	private FenetrePrincipale fenetre;
	private int typeCredit;
	private Credit cred;

	/**
	 * Constructeur de la classe, il initialise la variable fenetre
	 * avec l'objet de la fenetre principale et donne la valeur texte
	 * au bouton.
	 * @param fenetre
	 * 		notre fenetre principale.
	 * @param texte
	 * 		"valider".
	 */
	public BoutonAction(FenetrePrincipale fenetre, String texte) {
		super(texte);
		this.fenetre = fenetre;
	}

	/**
	 * Methode correspondant à l'action du clic
	 * sur le bouton valider. Elle rend invisible le message
	 * d'erreur au cas ou il y en ai un, vide le tableau et
	 * appelle les méthode d'initialisation.
	 */
	public void actionPerformed(ActionEvent e) {
		fenetre.getErreur().setVisible(false);
		fenetre.clearTableau();
		initializeNul();
		initializeValeurs();
	}

	/**
	 * Methode rend invisible le message d'erreur, puis
	 * initialise une variable chaine au valeurs vide et
	 * l'attribue grace à la methode getLabelBottom().setText
	 * au label de la fenetre principale.
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
	 * Permet de vérifier tout d'abord que les chaines saisies par l'utilisateur
	 * ne contiennent pas de lettres, puis selon si 4 ou 3 valeurs ont été saisies,
	 * appelle les methodes generationCredit4Valeurs ou getValeurs.
	 * Si il y a moins de 3 valeurs, appelle un message d'erreur.
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
					getValeurs();
					fenetre.clearTableau();
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
	 * Permet d'initialiser une variable chaine avec les valeurs
	 * du credit pris en parametre, puis attribut cette variable
	 * au label de la fenetre principale grace au getLabelBottom().
	 * @param cred
	 * 		Credit genere auparavent prit en parametre.
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
	 * Initialise une variable compteur a 0, puis
	 * pour chacun des champs à remplir de la fenetre principale
	 * qui est rempli, rajoute +1 a ce compteur.
	 * @return compteur
	 * 		Retourne le compteur (donc le nombre de champs remplis).
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

	/**
	 * Permet en recuperant la valeur du comboBox de la fenetre principale
	 * de savoir quel est le type de remboursement qu'a choisit l'utilisateur,
	 * puis l'attribut à la variable typeCredit.
	 */
	private void defineTypeCredit() {
		if (fenetre.getCombo().getSelectedItem() == "Amortissement constant")
			typeCredit = 1;
		else if (fenetre.getCombo().getSelectedItem() == "Annuitees constantes")
			typeCredit = 2;
	}

	/**
	 * Methode appelee si le nombre de valeurs saisies par l'utilisateur vaut 3.
	 * Appelle la methode pour definir le type de credit, puis vérifie pour chacun
	 * des champs si ceux ci sont remplis ou non. Pour ceux remplis, recupere la
	 * valeur saisie dans la variable correspondante.
	 * Enfin, appelle la methode generationCredit avec en parametre les valeurs.
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
	
	/**
	 * Methode permet selon le champ qui n'a pas ete rempli par l'utilisateur
	 * de creer un nouveau Credit avec les valeurs initialisees dans getValeurs().
	 * Ensuite, appelle la methode printValeurs prenant en parametre ce credit pour
	 * remplir le label du bas.
	 * Puis appelle la methode drawTableau en prenant en parametre le tableau de ce
	 * credit retourne par la methode getTableauAmortissement().getTableau().
	 * @param taux
	 * @param remboursement
	 * @param emprunt
	 * @param duree
	 * @throws MonException
	 */
	private void generationCredit(double taux, double remboursement, double emprunt, 
			int duree) throws MonException{
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

	/**
	 * Initialise les variables taux, duree, emprunt, remboursement avec les
	 * champs saisis par l'utilisateur, puis appelle defineTypeCredit() pour
	 * definir le type de credit.
	 * Ensuite la methode tente de creer un nouveau credit avec emprunt, taux
	 * et duree, puis verifie que l'annuite maximale genere par ce credit est
	 * bien la meme que celle tapee par l'utilisateur.
	 * Si tout est bon, appelle printValeurs et drawTableau pour remplir le
	 * label du bas et le tableau d'amortissement.
	 * @return bool
	 * 		Retourne true si tout s'est bien passe, sinon false.
	 */
	private boolean generationCredit4Valeurs(){
		double taux = (Double.parseDouble(fenetre.getTaux().getText()))/100;
		int duree = Integer.parseInt(fenetre.getDuree().getText());
		double emprunt = Double.parseDouble(fenetre.getEmprunt().getText());
		double remboursement = Double.parseDouble(fenetre.getRemboursement().getText());
		defineTypeCredit();
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
	 * Verifie en parcourant la chaine prise en parametre
	 * si chacun de ses caractere est numerique ou non.
	 * @param chaine
	 * @return bool
	 * 		Retourne true si la chaine est entierement numerique, sinon false.
	 */
	private boolean getNumeric(String chaine) {
		for (int i = 0; i < chaine.length(); i++) {
			if (!Character.isDigit(chaine.charAt(i)) && chaine.charAt(i) != '.')
				return false;
		}
		return true;
	}
}