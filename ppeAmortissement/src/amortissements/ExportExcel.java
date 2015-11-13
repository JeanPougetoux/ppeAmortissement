package amortissements;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

public class ExportExcel extends AbstractAction{
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private FenetrePrincipale fenetre;
	
	/*
	 * Constructeur dont les param�tres sont la fen�tre principale et le texte du bouton.
	 * Permet aussi d'initialiser la variable table � celle de l'objet fenetre.
	 */
	
	public ExportExcel(FenetrePrincipale fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
		table = fenetre.getTableau();
	}
	
	/*
	 * R�agit au clic du bouton
	 */
	
	public void actionPerformed(ActionEvent e) { 
		saveFile();
	} 
	
	/*
	 * Ouvre une boite de dialogue pour enregistrer et si elle est valid�e,
	 * lance la m�thode drawFile avec en param�tre le chemin du fichier cr��.
	 */
	
	public void saveFile(){	
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Exporter tableau");   
		String[] extensions = new String[] {"xls"};
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File (*.xls)", extensions);  		
		fileChooser.setSelectedFile(new File("fichier.xls"));

		fileChooser.setFileFilter(filter);

		 
		int userSelection = fileChooser.showSaveDialog(fenetre);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    drawFile(fileToSave);
		}
	}
	
	/*
	 * Permet gr�ce au chemin du fichier cr�� pr�c�demment d'�crire dans celui-ci
	 * chaque ligne du JTable prit en param�tre dans cette classe.
	 */
	
	public void drawFile(File file){
	 try{
	        TableModel model = table.getModel();
	        FileWriter excel = new FileWriter(file);

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + "\t");
	        }

	        excel.write("\n");

	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	                excel.write(model.getValueAt(i,j).toString()+"\t");
	            }
	            excel.write("\n");
	        }

	        excel.close();
	        enregistrementOk();

	    }catch(IOException e){ System.out.println(e); }
	}
	
	/*
	 * Permet si la cr�ation et l'enregistrement du fichier sont ok
	 * de modifier le message d'erreur pr�sent dans le JFrame principal.
	 */
	
	public void enregistrementOk(){
		MessageErreur.BienEnregistrer(fenetre);
	}
}
