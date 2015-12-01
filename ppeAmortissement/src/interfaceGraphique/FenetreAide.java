package interfaceGraphique;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class FenetreAide extends JDialog{
	
	private JPanel panel;
	private JTextArea textArea;
	private JButton bouton;
	private JScrollPane scroll;
	
	public FenetreAide(){
		build();
	}
	
	private void build(){
		setTitle("Aide d'utilisation"); 
		setSize(400,200); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setContentPane(buildContentPane());
		setModal(true);
	}
	private JPanel buildContentPane(){		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.lightGray);
		textArea = new JTextArea(getMessageDaide(), 7, 7);
		bouton = new JButton("OK");
		textArea.setEditable(false);
		scroll = new JScrollPane(textArea);
		panel.add(scroll);
		panel.add(bouton);
		bouton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		    	  BoutonAide.fenetreAide.setVisible(false);
		          BoutonAide.fenetreAide.dispose();
		      }
		    });
		return panel;
	}
	private String getMessageDaide(){
		String message;
		message = "Bonjour et bienvenue dans votre application de Gestion de l'amortissement.\n" +
		"Pour pouvoir generer un tableau d'amortissement il vous faudra connaître au minimum ";
//		"3 valeurs coherentes les unes avec les autres parmis : " +
//		"	- le montant emprunte" +
//		"	- le taux d'emprunt" +
//		"	- la duree du remboursement" +
//		"	- l'annuitee maximale" +
//		"Cependant, si vous souhaitez generer un tableau à partir de 4 valeurs, celles-ci devront" +
//		" etre strictement exactes." +
//		"Veuillez ensuite saison votre jeu de valeurs ainsi que le type de remboursement qui vous " +
//		"convient puis cliquez sur valider pour generer le tableau d'amortissement ainsi que la " +
//		"valeur inconnue si vous n'en aviez saisi que 3." +
//		"Vous avez de plus la possibilite d'exporter votre tableau d'amortissement au format Excel " +
//		"grace au bouton Export.";
		return message;
	}
}
