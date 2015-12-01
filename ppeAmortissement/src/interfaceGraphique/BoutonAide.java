package interfaceGraphique;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class BoutonAide extends AbstractAction{

	public final static FenetreAide fenetreAide = new FenetreAide();
	public BoutonAide(String texte){
		super(texte);		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetreAide.setVisible(true);
	}

}
