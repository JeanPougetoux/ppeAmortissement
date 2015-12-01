package interfaceGraphique;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class FenetreAide extends JDialog{
	
	private JPanel panel;
	private JTextArea textArea;
	
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
		panel.setBackground(Color.white);
		textArea = new JTextArea();
		panel.add(textArea);
		return panel;
	}
}
