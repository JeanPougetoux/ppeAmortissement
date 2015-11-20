package test;

import static org.junit.Assert.*;

import org.junit.Test;

import amortissements.Credit;
import amortissements.Ligne;
import amortissements.TableauAmortissement;

public class testTableauAmortissement {

	double montant = 100000;
	double annuiteMaximale = 10000;
	double taux = 0.05;
	Credit cred = Credit.calculeDuree(Credit.AMORTISSEMENT_CONSTANTS, montant, annuiteMaximale, taux);		
	TableauAmortissement tab = new TableauAmortissement(cred);

	@Test
	public void testGetNbLignes() {
		assertEquals(cred.duree(), 20, 0.01);
	}

	@Test
	public void testGetLigne() {
		Ligne li = tab.getLigne(1);
		assertTrue(li.getAmortissements() == 5000);
		assertTrue(li.getAnnee() == 1);
		assertTrue(li.getAnnuite() == 9750.00);
		assertTrue(li.getCapitalFinal() == 90000.00);
		assertTrue(li.getCapitalInitial() == 95000.00);
		assertTrue(li.getInterets() == 4750.00);
	}
}
