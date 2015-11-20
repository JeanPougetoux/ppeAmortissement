package amortissements;

public class Main {

	public static void main(String[] args) {
		Credit cred;
		try {
			cred = Credit.calculeTaux(Credit.AMORTISSEMENT_CONSTANTS, 50000, 49000, 12);
			System.out.println(cred.getTableauAmortissement());
			System.out.println(cred.taux());
			
		} catch (ExceptionCalculeTaux e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
