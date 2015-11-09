package amortissements;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class FenetrePrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField taux, duree, emprunt, remboursement;
	private JLabel printTaux, printDuree, printEmprunt, printRemboursement, messageErreur;
	private JPanel panel;

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
		setSize(800,500); 
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
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
 
		JLabel labelTaux = new JLabel("Taux (en %) : ");
		JLabel labelDuree = new JLabel("Durée (en années) : ");
		JLabel labelEmprunt = new JLabel("Montant emprunté : ");
		JLabel labelRemboursement = new JLabel("Montant des remboursements : ");
        Border current = labelTaux.getBorder();
        Border empty = new EmptyBorder(20, 0, 20, 0);
        labelTaux.setBorder(new CompoundBorder(empty, current));
		taux = new JTextField(04);
		duree = new JTextField(04);
		emprunt = new JTextField(04);
		remboursement = new JTextField(04);
		JButton bouton = new JButton(new BoutonAction(this, "valider"));

		
		panel.add(labelTaux);
		panel.add(taux);
		panel.add(labelDuree);
		panel.add(duree);
		panel.add(labelEmprunt);
		panel.add(emprunt);
		panel.add(labelRemboursement);
		panel.add(remboursement);
		panel.add(bouton);
	
		drawTableau();
		
		printTaux = new JLabel("Taux : ");
        current = printTaux.getBorder();
        empty = new EmptyBorder(20, 55, 20, 0);
        printTaux.setBorder(new CompoundBorder(empty, current));
		printDuree = new JLabel(",  Durée : ");
		printEmprunt = new JLabel(",  Montant emprunté : ");
		printRemboursement = new JLabel(",  Montant de remboursement : ");
        current = printRemboursement.getBorder();
        empty = new EmptyBorder(0, 0, 0, 50);
        printRemboursement.setBorder(new CompoundBorder(empty, current));
        
        messageErreur = new JLabel("Veuillez ne saisir que des valeurs unitaires");
        messageErreur.setForeground(Color.red);
		
		panel.add(printTaux);
		panel.add(printDuree);
		panel.add(printEmprunt);
		panel.add(printRemboursement);

		panel.add(messageErreur);
		current = printRemboursement.getBorder();
		empty = new EmptyBorder(0, 30, 0, 0);
		messageErreur.setBorder(new CompoundBorder(empty, current));
		messageErreur.setVisible(false);
		
		return panel;
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
 
        JTable tableau = new JTable(donnees, entetes);
        JScrollPane scroll = new JScrollPane(tableau);
        Dimension dim = new Dimension(700, 300);
        scroll.setPreferredSize(dim);
        panel.add(scroll);

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
}
