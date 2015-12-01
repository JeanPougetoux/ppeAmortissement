package interfaceGraphique;

import javax.swing.*;

public class Application {

	public static final FenetrePrincipale fenetre = new FenetrePrincipale();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				fenetre.setVisible(true);
			}
		});	}

}
