package amortissements;

public class Main {

	public static void main(String[] args) {
		
		Credit cred = Credit.calculeTaux(2, 20000, 5640.24, 4);
		System.out.println(cred.getTableauAmortissement());
		

	}

}
