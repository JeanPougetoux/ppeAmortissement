package amortissements;

import java.awt.*;
import javax.swing.*;

public class FenetrePrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField taux, duree, emprunt, remboursement;
	private JLabel printTaux, printDuree, printEmprunt, printRemboursement, messageErreur;
	private JLabel labelTaux, labelDuree, labelEmprunt, labelRemboursement, labelBottom;
	private JPanel panel, panel2;
	private JButton bouton, bouton2;
	private JScrollPane scroll;
	private JTable tableau;
	private GridBagConstraints gbc;

	/**
	 * Page principale où se passe toutes les interactions
	 */
	
	public FenetrePrincipale(){
		super();
		
		build();
	}
	
	/**
	 * Permet de changer les paramètres de la page
	 */
	
	private void build(){
		setTitle("Gestion de l'amortissement"); 
		setSize(850,480); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setContentPane(buildContentPaneHead());
		getContentPane().add(buildContentPane());
	}
	
	private JPanel buildContentPaneHead(){
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		drawHead();
		panelHead();
		return panel2;
	}
	/**
	 * Permet de remplir le JPanel avec les différents composants et
	 * l'intègre au JFrame (page principale)
	 */
	
	private JPanel buildContentPane(){		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.white);
		drawTableau();
		drawBody();
		drawBottom();
		panelTableau();
		panelBottom();
		return panel;
	}
	
	/*
	 * Initialise le haut de l'application (label + champs)
	 */
	private void drawHead(){
		labelTaux = new JLabel("Taux (en %) : ");
		taux = new JTextField(04);
		labelDuree = new JLabel("Durée (en années) : ");				
		duree = new JTextField(04);
		labelEmprunt = new JLabel("Montant emprunté : ");		
		emprunt = new JTextField(04);
		labelRemboursement = new JLabel("Annuité maximale : ");		
		remboursement = new JTextField(04);
		
		bouton = new JButton(new BoutonAction(this, "valider"));
	}
	
	/*
	 * Concatène les chaines en bas pour n'en créer qu'une seule
	 */
	
	private void drawPanelBottom(){
		labelBottom = new JLabel(printTaux.getText() + printDuree.getText() +
				printEmprunt.getText() + printRemboursement.getText());
	}
	/*
	 * Initialise ce qui est au milieu de l'application (labels)
	 */
	private void drawBody(){
		printTaux = new JLabel("Taux : ");
		printDuree = new JLabel(",  Durée : ");
		printEmprunt = new JLabel(",  Montant emprunté : ");
		printRemboursement = new JLabel(",  Montant de remboursement : ");

		bouton2 = new JButton(new ExportExcel(this, "export"));
	}
	
	/*
	 * Initialise le message d'erreur en bas de l'application
	 */
	
	private void drawBottom(){
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
		panel2.add(bouton);
	}
	
	/*
	 * Place le tableau
	 */
	
	private void panelTableau(){
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 0, 0, 0);
		panel.add(scroll, gbc);
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;	
		gbc.insets = new Insets(10, 300, 10, 0);
		gbc.gridx = 8;
		gbc.gridy = 3;
		panel.add(bouton2, gbc);
	}
	
	/*
	 * Place les informations et le message d'erreur
	 */
	
	private void panelBottom(){
		gbc = new GridBagConstraints();
		drawPanelBottom();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 8;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(labelBottom, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 8;
		panel.add(messageErreur, gbc);
	}
	/**
	 * Créer un tableau, le remplit de valeurs et l'ajoute au JPanel
	 */
	
	public void drawTableau(){
		Object[][] donnees = {
                {"3", "2000", "200", "1000", "300", "700"},
                {"3", "2000", "200", "1000", "300", "700"},

        };
 
        String[] entetes = {"Années", "Restant dû", "Intérêt", "Amortissement", "Annuité", "Valeur nette"};
 
        tableau = new JTable(donnees, entetes);
        scroll = new JScrollPane(tableau);
        Dimension dim = new Dimension(760, 300);
        scroll.setPreferredSize(dim);
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
	
	public JLabel getModifTaux(){
		return printTaux;
	}
	
	public JLabel getModifDuree(){
		return printDuree;
	}
	
	public JLabel getModifEmprunt(){
		return printEmprunt;
	}
	
	public JLabel getModifRemboursement(){
		return printRemboursement;
	}
	
	public JLabel getErreur(){
		return messageErreur;
	}
	
	public JTable getTableau(){
		return tableau;
	}
}
