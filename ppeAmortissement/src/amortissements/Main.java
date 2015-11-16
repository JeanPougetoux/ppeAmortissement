package amortissements;

public class Main {

	public static void main(String[] args) {
		
//		Credit cred = Credit.calculeTaux(2, 2000000, 5640.24, 6);
//		System.out.println(cred.getTableauAmortissement());
		Credit cred = Credit.calculeDuree(2, 100000, 10000, 2);
		System.out.println(cred.getTableauAmortissement());
	}
}
