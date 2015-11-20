package amortissements;

public class ExceptionCalculeTaux extends Exception{
	public ExceptionCalculeTaux()
    {
        System.out.println("Une exception a été levé...");
    }
	public String toString()
    {
      return "Les données renseignées ne sont pas corrects !";
    }
}
