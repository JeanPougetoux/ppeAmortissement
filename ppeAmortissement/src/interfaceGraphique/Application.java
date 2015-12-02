package interfaceGraphique;

import javax.swing.*;

/**
 * Classe principale de l'application, elle contient le main et
 * est donc lancee en premier lorsque l'utilisateur demarre le logiciel.
 * @author Jean
 *
 */
public class Application {

	public static final FenetrePrincipale fenetre = new FenetrePrincipale();

	/**
	 * Methode main qui utilise la methode invokeLater de la classe 
	 * SwingUtilities pour creer un nouveau "runnable" contenant la
	 * methode de lancement
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			/**
			 * Methode qui permet de rendre l'objet fenetre visible
			 */
			public void run(){
				fenetre.setVisible(true);
			}
		});	}

}
