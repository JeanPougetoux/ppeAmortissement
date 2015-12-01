package interfaceGraphique;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class BoutonAide extends AbstractAction{

	public BoutonAide(String texte){
		super(texte);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		FenetreAide fenetreAide = new FenetreAide();
		fenetreAide.setVisible(true);
	}

}
