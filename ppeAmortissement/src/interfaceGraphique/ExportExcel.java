package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
/**
 * Classe ExportExcel correspond au bouton export
 * de la fenetre principale. Elle herite de la classe
 * AsbtractAction.
 * @author Jean
 *
 */
public class ExportExcel extends AbstractAction{
	
	private JTable table;
	private FenetrePrincipale fenetre;
	
	/**
	 * Constructeur de la classe ExportExcel. Elle initialise la
	 * valeur sur le bouton de la fenetre principale ainsi que le JTable
	 * table grace a la fenetre principale prise en parametre.
	 * @param fenetre
	 * 		Fenetre principale.
	 * @param texte
	 * 		"export".
	 */
	public ExportExcel(FenetrePrincipale fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
		table = fenetre.getTableau();
	}
	
	/**
	 * Action lorsque l'on clique sur le bouton.
	 * Si la methode verifTableauVide() renvoie faux
	 * appelle un message d'erreur, sinon appelle saveFile().
	 */
	public void actionPerformed(ActionEvent e) { 
		if(!verifTableauVide())
			saveFile();
		else
			MessageErreur.TableauVide(fenetre);
	} 
	
	/**
	 * Permet l'export du tableau. Recupere le chemin d'enregistrement
	 * de celui ci ainsi que le choix de l'utilisateur grace a un fileChooser.
	 * Puis si l'utilisateur a valider son choix, creer un nouveau fichier
	 * en utilisant le chemin mentionne puis appelle la methode drawFile() en 
	 * prenant en parametre ce fichier.
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
	
	/**
	 * Permet d'ecrire dans le fichier pris en parametre le contenu de
	 * la variable table (egale au JTable de la fenetre principale).
	 * Si tout s'est bien passe appelle la methode enregistrementOk().
	 * @param file
	 * 		Fichier creer par la methode saveFile().
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
	
	/**
	 * Vérifie si la variable table contient plus d'une ligne
	 * @return bool
	 * 		true si le tableau est vide sinon false
	 */
	public boolean verifTableauVide(){
        TableModel model = table.getModel();
        return model.getRowCount() < 1;
	}
	
	/**
	 * Appelle la methode statique BienEnregistrer() de la classe
	 * MessageErreur.
	 */
	public void enregistrementOk(){
		MessageErreur.BienEnregistrer(fenetre);
	}
}
