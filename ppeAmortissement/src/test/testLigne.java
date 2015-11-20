package test;

import static org.junit.Assert.*;

import org.junit.Test;

import amortissements.Credit;
import amortissements.Ligne;

public class testLigne {

	double montant = 100000;
	double annuiteMaximale = 10000;
	double taux = 0.05;
	Credit cred = Credit.calculeDuree(Credit.AMORTISSEMENT_CONSTANTS, montant, annuiteMaximale, taux);
	Ligne li = Ligne.premiereLigne(cred);
	
	@Test
	public void testPremiereLigne() {
		assertTrue(li.getAmortissements() == 5000);
		assertTrue(li.getAnnee() == 0);
		assertTrue(li.getAnnuite() == annuiteMaximale);
		assertTrue(li.getCapitalFinal() == 95000.00);
		assertTrue(li.getCapitalInitial() == montant);
		assertTrue(li.getInterets() == 5000.00);
	}

	@Test
	public void testLigneSuivante() {
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
