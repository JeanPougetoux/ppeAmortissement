package interfaceGraphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import amortissements.Ligne;

public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField taux, duree, emprunt, remboursement;
	private JLabel messageErreur;
	private JLabel labelTaux, labelDuree, labelEmprunt, labelRemboursement, labelBottom;
	private JPanel panel, panel2, panelTxt;
	private JButton bouton, bouton2, bouton3;
	private JScrollPane scroll;
	private JTable tableau;
	private JComboBox<String> choix;
	private DefaultTableModel model;

	/**
	 * Page principale oe se passe toutes les interactions
	 */
	
	public FenetrePrincipale(){
		build();
	}
	
	/**
	 * Permet de changer les parametres de la page
	 */
	
	private void build(){
		setTitle("Gestion de l'amortissement"); 
		setSize(850,520); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setContentPane(buildContentPaneHead());
		getContentPane().add(buildContentPane());
		getContentPane().add(buildContentPaneBot());
	}
	
	/*
	 * Definit le panel ou seront tous les elements du Header
	 */
	private JPanel buildContentPaneHead(){
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		drawHead();
		panelHead();
		return panel2;
	}
	
	private JPanel buildContentPaneBot(){
		panelTxt = new JPanel();
		panelTxt.setLayout(new GridLayout(3,1));
		panelTxt.setBackground(Color.white);
		drawBottom();
		panelBottom();
		return panelTxt;
	}
	/**
	 * Permet de remplir le JPanel avec les differents composants et
	 * l'integre au JFrame (page principale)
	 */
	
	private JPanel buildContentPane(){		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
		initTableau();
		panelTableau();
		return panel;
	}
	
	/*
	 * Initialise le haut de l'application (label + champs)
	 */
	private void drawHead(){
		labelTaux = new JLabel("Taux (en %) : ");
		taux = new JTextField(04);
		labelDuree = new JLabel("Duree (en annees) : ");				
		duree = new JTextField(04);
		labelEmprunt = new JLabel("Montant emprunte : ");		
		emprunt = new JTextField(04);
		labelRemboursement = new JLabel("Annuitee maximale : ");		
		remboursement = new JTextField(04);
		
		String[] choixCredit = { "Annuitees constantes", "Amortissement constant"};
		choix = new JComboBox<String>(choixCredit);
		choix.setSelectedIndex(1);
		bouton = new JButton(new BoutonAction(this, "valider"));
	}
	
	
	/*
	 * Initialise le message d'erreur en bas de l'application
	 */
	
	private void drawBottom(){
		 labelBottom = new JLabel("Taux :   Duree :   Montant emprunte :   Annuitee maximale : ");
		 bouton2 = new JButton(new ExportExcel(this, "export"));
		 bouton3 = new JButton(new BoutonAide("aide"));
		 messageErreur = new JLabel("Veuillez ne saisir que des valeurs unitaires");
	     messageErreur.setForeground(Color.red);
	     messageErreur.setVisible(false);
	}
	
	/*
	 * Place le header 
	 */
	private void panelHead(){
		panel2.add(labelTaux);
		panel2.add(taux);
		panel2.add(labelDuree);
		panel2.add(duree);
		panel2.add(labelEmprunt);
		panel2.add(emprunt);
		panel2.add(labelRemboursement);
		panel2.add(remboursement);
		panel2.add(choix);
		panel2.add(bouton);
	}
	
	/*
	 * Place le tableau
	 */
	
	private void panelTableau(){

		panel.add(scroll);
	}
	
	/*
	 * Place les informations et le message d'erreur
	 */
	
	private void panelBottom(){
		JPanel p = new JPanel(); 
		p.add(bouton2); 
		p.add(bouton3);
		p.setBackground(Color.white);
		panelTxt.add(p);
		panelTxt.add(labelBottom);
		panelTxt.add(messageErreur);
	}
	/**
	 * Creer un tableau, le remplit de valeurs et l'ajoute au JPanel
	 */
	
	public void initTableau(){
		Object[][] donnees = {};
 
        String[] entetes = {"Annees", "Capital initial", "Interet", "Amortissement", "Annuitee", "Capital final"};
 
        model = new DefaultTableModel(donnees, entetes);
        tableau = new JTable(model);
        scroll = new JScrollPane(tableau);
        Dimension dim = new Dimension(760, 300);
        scroll.setPreferredSize(dim);
	}
	
	public void drawTableau(Ligne[] tab){
		Object[][] donnees = new Object[tab.length][6];
		for(int i = 0; i < tab.length; i++){
			donnees[i][0] = tab[i].getAnnee();
			donnees[i][1] = (double)Math.round(tab[i].getCapitalInitial()*100)/100;
			donnees[i][2] = (double)Math.round(tab[i].getInterets()*100)/100;
			donnees[i][3] = (double)Math.round(tab[i].getAmortissements()*100)/100;
			donnees[i][4] = (double)Math.round(tab[i].getAnnuite()*100)/100;
			donnees[i][5] = (double)Math.round(tab[i].getCapitalFinal());
		}
        String[] entetes = {"Annees", "Capital initial", "Interet", "Amortissement", "Annuite", "Capital final"};
		model = new DefaultTableModel(donnees, entetes);
		tableau.setModel(model);
	}
	
	/*
	 * Efface toutes les donnees du tableau
	 */
	
	public void clearTableau(){
		model.getDataVector().clear();
		model.fireTableDataChanged();
		tableau.setModel(model);
	}
	
	public FenetrePrincipale getThis(){
		return this;
	}
	
	public JTextField getTaux(){
		return taux;
	}
	
	public JTextField getDuree(){
		return duree;
	}
	
	public JTextField getEmprunt(){
		return emprunt;
	}
	
	public JTextField getRemboursement(){
		return remboursement;
	}
	
	public JLabel getLabelBottom(){
		return labelBottom;
	}
	
	public JLabel getErreur(){
		return messageErreur;
	}
	
	public JTable getTableau(){
		return tableau;
	}
	
	public JComboBox<String> getCombo(){
		return choix;
	}
}