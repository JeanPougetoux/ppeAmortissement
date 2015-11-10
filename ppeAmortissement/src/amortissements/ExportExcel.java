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
	
	public ExportExcel(FenetrePrincipale fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
		table = fenetre.getTableau();
	}
	
	public void actionPerformed(ActionEvent e) { 
		saveFile();
	} 
	
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
	
	public void enregistrementOk(){
		MessageErreur.BienEnregistrer(fenetre);
	}
}
