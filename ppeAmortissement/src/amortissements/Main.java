package amortissements;

public class Main {

	public static void main(String[] args) {
		
		Credit cred = Credit.calculeTaux(2, 20000, 5640.24,4);
		System.out.println(cred.getTableauAmortissement());
		System.out.println(cred.taux());
		Credit cred2 = Credit.calculeAnnuiteMaximale(2,20000,0.05,4);
		System.out.println(cred2.getTableauAmortissement());
		System.out.println(cred2.montantEmprunte());
		
	}
}
