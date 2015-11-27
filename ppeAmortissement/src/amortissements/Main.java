package amortissements;


import exceptions.MonException;

public class Main {

	public static void main(String[] args) {
		Credit cred;
		try {
			cred = Credit.calculeDuree(Credit.ANNUITES_CONSTANTES, 125000, 35251,0.05);
			System.out.println(cred.getTableauAmortissement());
			//System.out.println(cred.taux()*100 + " %");
			
		} catch (MonException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		Credit cred2;
		try {
			cred2 = Credit.calculeMontantEmprunte(Credit.ANNUITES_CONSTANTES,34687,0.05,4);
			System.out.println(cred2.getTableauAmortissement());
		} catch (MonException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
	}
}
