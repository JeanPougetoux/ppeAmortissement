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
	private JPanel panel;
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
		setSize(850,500); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setContentPane(buildContentPane());
	}
	
	/**
	 * Permet de remplir le JPanel avec les différents composants et
	 * l'intègre au JFrame (page principale)
	 */
	
	private JPanel buildContentPane(){		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.white);
		drawHead();
		drawTableau();
		drawBody();
		drawBottom();
		panelHead();
		panelTableau();
		panelBottom();
		return panel;
	}
	
	/*
	 * Initialise le haut de l'application (label + champs)
	 */
	private void drawHead(){
		labelTaux = new JLabel("Taux (en %) : ");
		taux = new JTextField();
		labelDuree = new JLabel("Durée (en années) : ");				
		duree = new JTextField();
		labelEmprunt = new JLabel("Montant emprunté : ");		
		emprunt = new JTextField();
		labelRemboursement = new JLabel("Montant des remboursements : ");		
		remboursement = new JTextField();
		
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
//	     messageErreur.setVisible(false);
	}
	
	/*
	 * Place le header 
	 */
	private void panelHead(){
		gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		panel.add(labelTaux, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(taux, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(labelDuree, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		panel.add(duree, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 0;
		panel.add(labelEmprunt, gbc);
		gbc.gridx = 5;
		gbc.gridy = 0;
		panel.add(emprunt, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 6;
		gbc.gridy = 0;
		panel.add(labelRemboursement, gbc);
		gbc.gridx = 7;
		gbc.gridy = 0;			
		panel.add(remboursement, gbc);		
		gbc = new GridBagConstraints();;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 8;
		gbc.gridy = 0;		
		panel.add(bouton, gbc);
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
		panel.add(scroll, gbc);
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;		
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
        Dimension dim = new Dimension(800, 300);
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
