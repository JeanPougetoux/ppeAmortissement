package amortissements;

import exceptions.ExceptionCalculeAnnuiteMaximale;
import exceptions.ExceptionCalculeDuree;
import exceptions.ExceptionCalculeMontant;
import exceptions.ExceptionCalculeTaux;

public class Main {

	public static void main(String[] args) {
		Credit cred;
		try {
			cred = Credit.calculeDuree(Credit.AMORTISSEMENT_CONSTANTS, 159000, 60256.589,0.03);
			System.out.println(cred.getTableauAmortissement());
			//System.out.println(cred.taux()*100 + " %");
			
		} catch (ExceptionCalculeDuree e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		Credit cred2;
		try {
			cred2 = Credit.calculeMontantEmprunte(Credit.AMORTISSEMENT_CONSTANTS,30000,0.56,2);
			System.out.println(cred2.getTableauAmortissement());
		} catch (ExceptionCalculeMontant e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
	}
}
