package amortissements;

public class Main {

	public static void main(String[] args) {
		Credit cred = Credit.calculeTaux(Credit.AMORTISSEMENT_CONSTANTS, 5000, 51000, 12);
		System.out.println(cred.getTableauAmortissement());
		System.out.println(cred.taux());
	}
}
