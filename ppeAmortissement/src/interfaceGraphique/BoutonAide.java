package interfaceGraphique;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class BoutonAide extends AbstractAction{

	public final static FenetreAide fenetreAide = new FenetreAide();
	
	/**
	 * Bouton qui permet de generer une fenetre d'aide
	 * @param texte est le message sur le bouton
	 */
	public BoutonAide(String texte){
		super(texte);		
	}
	
	@Override
	/**
	 * Affiche la fenetre d'aide au clic sur le bouton
	 */
	public void actionPerformed(ActionEvent e) {
		fenetreAide.setVisible(true);
	}

}
