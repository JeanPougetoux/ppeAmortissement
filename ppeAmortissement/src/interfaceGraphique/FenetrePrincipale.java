package interfaceGraphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import amortissements.Ligne;

@SuppressWarnings("serial")
/**
 * Classe FenetrePrincipale represente la fenetre
 * genere par la classe Application et qui sera active
 * tout au long de l'utilisation du logiciel. Elle herite
 * de la classe JFrame.
 * @author Jean
 *
 */
public class FenetrePrincipale extends JFrame {

	private JTextField taux, duree, emprunt, remboursement;
	private JLabel messageErreur;
	private JLabel labelTaux, labelDuree, labelEmprunt, labelRemboursement, labelBottom;
	private JPanel panel, panel2, panelTxt, p;
	private JButton bouton, bouton2, bouton3;
	private JScrollPane scroll;
	private JTable tableau;
	private JComboBox<String> choix;
	private DefaultTableModel model;

	/**
	 * Constructeur de la classe FenetrePrincipale, appelle
	 * la methode build().
	 */
	public FenetrePrincipale(){
		build();
	}
	
	/**
	 * Permet de donner ses caracteristiques au JFrame, puis attribue
	 * comme panel principale le JPanel retourne par la methode
	 * buildContentPaneHead(), et y ajoute les JPanel retournes
	 * par les methodes buildContentPane() et buildContentPaneBot().
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
	
	/**
	 * Definit le JPanel correspondant au header de l'application,
	 * donc la partie � saisir pour l'utilisateur et le bouton valider.
	 * Appelle les methodes drawHead() et panelHead().
	 * @return panel2
	 * 		retourne ce JPanel.
	 */
	private JPanel buildContentPaneHead(){
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		drawHead();
		panelHead();
		return panel2;
	}	
	
	/**
	 * Definit le JPanel correspondant au tableau de l'application.
	 * Appelle les methodes initTableau() et panelTableau().
	 * @return panel
	 * 		retourne ce JPanel.
	 */
	private JPanel buildContentPane(){		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
		initTableau();
		return panel;
	}
	
	/**
	 * Definit le JPanel correspondant au bas de l'application,
	 * donc les boutons export et aide ainsi que le label des valeurs
	 * et le message d'erreur s'il y a.
	 * Appelle les methodes drawBottom() et panelBottom().
	 * @return panelTxt
	 * 		retourne ce JPanel.
	 */
	private JPanel buildContentPaneBot(){
		panelTxt = new JPanel();
		panelTxt.setLayout(new GridLayout(3,1));
		panelTxt.setBackground(Color.white);
		drawBottom();
		panelBottom();
		return panelTxt;
	}
	
	/**
	 * Initialise tous les composants du header.
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
	
	/**
	 * Ajoute les composants au JPanel correspondant au header.
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
	
	/**
	 * Initialise le tableau avec des valeurs vides et l'ajoute a panel.
	 */
	public void initTableau(){
		Object[][] donnees = {};
 
        String[] entetes = {"Annees", "Capital initial", "Interet", "Amortissement", "Annuitee", "Capital final"};
 
        model = new DefaultTableModel(donnees, entetes);
        tableau = new JTable(model);
        scroll = new JScrollPane(tableau);
        Dimension dim = new Dimension(760, 300);
        scroll.setPreferredSize(dim);
        panel.add(scroll);
	}
	
	/**
	 * Permet de changer le model du tableau avec les valeurs du
	 * tableau d'objets Ligne prit en parametre.
	 * @param tab
	 * 		Tableau de Lignes correspondant a un Credit.
	 */
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
	
	/**
	 * Initialise les elements du panel du bas, dont le bouton export, aide,
	 * le labelBottom ainsi que le message d'erreur.
	 */
	private void drawBottom(){
		 labelBottom = new JLabel("Taux :   Duree :   Montant emprunte :   Annuitee maximale : ");
		 bouton2 = new JButton(new ExportExcel(this, "export"));
		 bouton3 = new JButton(new BoutonAide("aide"));
		 messageErreur = new JLabel("Veuillez ne saisir que des valeurs unitaires");
	     messageErreur.setForeground(Color.red);
	     messageErreur.setVisible(false);
	}
	
	/**
	 * Creer un JPanel p pour les deux bouton export
	 * et documentation puis ajoute p au JPanel
	 * panelTxt ainsi que labelBottom et messageErreur.
	 */
	private void panelBottom(){
		p = new JPanel(); 
		p.add(bouton2); 
		bouton2.setPreferredSize(new Dimension(80, 25));
		p.setBorder(new EmptyBorder(0, 589, 0, 0));
		p.add(bouton3);
		bouton3.setPreferredSize(new Dimension(80, 25));
		p.setBackground(Color.white);
		panelTxt.add(p);
		panelTxt.add(labelBottom);
		panelTxt.add(messageErreur);
		panelTxt.setBorder(new EmptyBorder(0, 7, 0, 0));
	}

	/**
	 * Permet d'effacer les donnees du model
	 * correspondant au tableau.
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