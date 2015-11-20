package interfaceGraphique;

import javax.swing.*;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				FenetrePrincipale fenetre = new FenetrePrincipale();
				fenetre.setVisible(true);
			}
		});	}

}
