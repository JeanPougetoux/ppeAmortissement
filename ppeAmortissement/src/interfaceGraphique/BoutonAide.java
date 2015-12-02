package interfaceGraphique;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Classe BoutonAide correspond au bouton aide
 * de la fenetre principale. Elle herite de la classe
 * AbstractAction.
 * @author Jean
 *
 */
public class BoutonAide extends AbstractAction{

	private static final long serialVersionUID = 1L;
	public final static FenetreAide fenetreAide = new FenetreAide();
	
	/**
	 * Constructeur de la classe BoutonAide.
	 * Permet d'initialiser la valeur sur le bouton de 
	 * la fenetre principale.
	 * @param texte 
	 * 		"aide".
	 */
	public BoutonAide(String texte){
		super(texte);		
	}
	
	@Override
	/**
	 * Action realisee au clic sur le bouton. Rend
	 * visible le JFrame fenetreAide.
	 */
	public void actionPerformed(ActionEvent e) {
		fenetreAide.setVisible(true);
	}

}
