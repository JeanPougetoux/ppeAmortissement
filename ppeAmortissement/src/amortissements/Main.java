package amortissements;

import exceptions.ExceptionCalculeDuree;
import exceptions.ExceptionCalculeTaux;

public class Main {

	public static void main(String[] args) {
		Credit cred;
		try {
			cred = Credit.calculeDuree(Credit.ANNUITES_CONSTANTES, 100000, 6670, 0.05);
			System.out.println(cred.getTableauAmortissement());
			System.out.println(cred.taux()*100 + " %");
			
		} catch (ExceptionCalculeDuree e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
