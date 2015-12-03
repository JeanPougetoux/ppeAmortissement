package test;

import static org.junit.Assert.*;

import org.junit.Test;

import amortissements.Credit;
import amortissements.Ligne;

import exceptions.MonException;

/**
 * Classe de tests de la classe Ligne.
 * @author Jean
 *
 */
public class testLigne {
	
	double montant ;
	double annuiteMaximale;
	double taux ;
	Credit cred ;
	Ligne li ;

	/**
	 * Constructeur de la classe testLigne(), permet d'appeller premiereLigne().
	 * @throws MonException
	 */
	public testLigne()throws MonException{
		this.montant = 100000;
		this.annuiteMaximale = 10000;
		this.taux = 0.05;
		this.cred = Credit.calculeDuree(Credit.AMORTISSEMENT_CONSTANTS, montant, annuiteMaximale, taux);
		this.li = Ligne.premiereLigne(cred);
	}
	
	
	@Test
	/**
	 * Test des valeurs de la premiere ligne.
	 */
	public void testPremiereLigne() {
		assertEquals(li.getAmortissements(), 5000, 0.01);
		assertEquals(li.getAnnee(), 1, 0.01);
		assertEquals(li.getAnnuite(), annuiteMaximale, 0.01);
		assertEquals(li.getCapitalFinal(), 95000.00, 0.01);
		assertEquals(li.getCapitalInitial(), montant, 0.01);
		assertEquals(li.getInterets(), 5000.00, 0.01);
	}

	@Test
	/**
	 * Test des valeurs de la ligne suivante a la premiere ligne,
	 * ici la seconde.
	 * @throws MonException
	 */
	public void testLigneSuivante()throws MonException {
		Ligne liSuivante = li.ligneSuivante(cred);
		assertEquals(liSuivante.getAmortissements(), 5000, 0.01);
		assertEquals(liSuivante.getAnnee(), 2, 0.01);
		assertEquals(liSuivante.getAnnuite(), 9750.00, 0.01);
		assertEquals(liSuivante.getCapitalFinal(), 90000.00, 0.01);
		assertEquals(liSuivante.getCapitalInitial(), 95000.00, 0.01);
		assertEquals(liSuivante.getInterets(), 4750.00, 0.01);
		
		annuiteMaximale = 12950.46;
		taux = 0.05;
		cred = Credit.calculeDuree(Credit.ANNUITES_CONSTANTES, montant, annuiteMaximale, taux);
		li = Ligne.premiereLigne(cred);
		liSuivante = li.ligneSuivante(cred);
		assertEquals(liSuivante.getAmortissements(), 8347.98, 0.01);
		assertEquals(liSuivante.getAnnee(), 2, 0.01);
		assertEquals(liSuivante.getAnnuite(), annuiteMaximale, 0.01);
		assertEquals(liSuivante.getCapitalFinal(), 83701.56, 0.001);
		assertEquals(liSuivante.getCapitalInitial(), 92049.54, 0.01);
		assertEquals(liSuivante.getInterets(), 4602.48, 0.01);
	}

}
