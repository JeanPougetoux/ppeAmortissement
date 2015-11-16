package amortissements;

public class Main {

	public static void main(String[] args) {
		
	Credit cred = Credit.calculeAnnuiteMaximale(2,98000, 0.05,4);
	System.out.println(cred.getTableauAmortissement());
	System.out.println(cred.duree());
	Credit cred2 = Credit.calculeAnnuiteMaximale(1,150000, 0.05, 4);
	System.out.println(cred2.getTableauAmortissement());
	System.out.println(cred2.taux());
	}
}
