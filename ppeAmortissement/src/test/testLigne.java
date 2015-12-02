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
		assertTrue(li.getAmortissements() == 5000);
		assertTrue(li.getAnnee() == 0);
		assertTrue(li.getAnnuite() == annuiteMaximale);
		assertTrue(li.getCapitalFinal() == 95000.00);
		assertTrue(li.getCapitalInitial() == montant);
		assertTrue(li.getInterets() == 5000.00);
	}

	@Test
	/**
	 * Test des valeurs de la ligne suivante a la premiere ligne,
	 * ici la seconde.
	 * @throws MonException
	 */
	public void testLigneSuivante()throws MonException {
		Ligne liSuivante = li.ligneSuivante(cred);
		assertTrue(liSuivante.getAmortissements() == 5000);
		assertTrue(liSuivante.getAnnee() == 1);
		assertTrue(liSuivante.getAnnuite() == 9750.00);
		assertTrue(liSuivante.getCapitalFinal() == 90000.00);
		assertTrue(liSuivante.getCapitalInitial() == 95000.00);
		assertTrue(liSuivante.getInterets() == 4750.00);
		
		annuiteMaximale = 12950.46;
		taux = 0.05;
		cred = Credit.calculeDuree(Credit.ANNUITES_CONSTANTES, montant, annuiteMaximale, taux);
		li = Ligne.premiereLigne(cred);
		liSuivante = li.ligneSuivante(cred);
		assertTrue(liSuivante.getAmortissements() == 8347.98);
		assertTrue(liSuivante.getAnnee() == 1);
		assertTrue(liSuivante.getAnnuite() == annuiteMaximale);
		assertEquals(liSuivante.getCapitalFinal(), 83701.56, 0.001);
		assertTrue(liSuivante.getCapitalInitial() == 92049.54);
		assertTrue(liSuivante.getInterets() == 4602.48);
	}

}
