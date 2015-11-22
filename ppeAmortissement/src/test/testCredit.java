package test;

import static org.junit.Assert.*;

import org.junit.Test;

import amortissements.Credit;
import exceptions.ExceptionCalculeAnnuiteMaximale;
import exceptions.ExceptionCalculeDuree;
import exceptions.ExceptionCalculeMontant;
import exceptions.ExceptionCalculeTaux;


public class testCredit {

	@Test
	public void testCalculeTaux() throws ExceptionCalculeTaux {
		int type = 1;
		double montant = 100000;
		double annuiteMaximale = 10000;
		int duree = 20;
		double taux = 0.05;
		Credit credit;
		try {
			credit = Credit.calculeTaux(Credit.AMORTISSEMENT_CONSTANTS, 100000, 10000, 20);
			assertTrue(type == credit.typeCredit());
			assertTrue(montant == credit.montantEmprunte());
			assertTrue(annuiteMaximale == credit.annuiteMaximale());
			assertTrue(duree == credit.duree());
			assertTrue(taux == credit.taux());
		} catch (ExceptionCalculeTaux e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		annuiteMaximale = 8024.26;
		type = 2;
		credit = Credit.calculeTaux(Credit.ANNUITES_CONSTANTES, 100000, 8024.26, 20);
		assertTrue(type == credit.typeCredit());
		assertTrue(montant == credit.montantEmprunte());
		assertTrue(annuiteMaximale == credit.annuiteMaximale());
		assertTrue(duree == credit.duree());
		assertTrue(taux == credit.taux());
	}

	@Test
	public void testCalculTauxAnnuiteConstante() {
		assertEquals(1317.54, Credit.calculTauxAnnuiteConstante(12000, 15, 0.07), 0.01);
	}

	@Test
	public void testCalculeDuree() {
		int type = 1;
		double montant = 65000;
		double annuiteMaximale = 11122.22;
		int duree = 9;
		double taux = 0.06;
		Credit credit;
		try {
			credit = Credit.calculeDuree(Credit.AMORTISSEMENT_CONSTANTS, 65000, 11122.22, 0.06);
			assertTrue(type == credit.typeCredit());
			assertTrue(montant == credit.montantEmprunte());
			assertTrue(annuiteMaximale == credit.annuiteMaximale());
			assertTrue(duree == credit.duree());
			assertTrue(taux == credit.taux());
		} catch (ExceptionCalculeDuree e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		type = 2;
		annuiteMaximale = 9556.45;
		try {
			credit = Credit.calculeDuree(Credit.ANNUITES_CONSTANTES, 65000, 9556.45, 0.06);
			assertTrue(type == credit.typeCredit());
			assertTrue(montant == credit.montantEmprunte());
			assertTrue(annuiteMaximale == credit.annuiteMaximale());
			assertTrue(duree == credit.duree());
			assertTrue(taux == credit.taux());
		} catch (ExceptionCalculeDuree e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCalculeMontantEmprunte() {
		int type = 1;
		double montant = 123000;
		double annuiteMaximale = 34440.00;
		int duree = 4;
		double taux = 0.03;
		Credit credit;
		try {
			credit = Credit.calculeMontantEmprunte(Credit.AMORTISSEMENT_CONSTANTS, 34440.00, 0.03, 4);
			assertTrue(type == credit.typeCredit());
			assertTrue(montant == credit.montantEmprunte());
			assertTrue(annuiteMaximale == credit.annuiteMaximale());
			assertTrue(duree == credit.duree());
			assertTrue(taux == credit.taux());
		} catch (ExceptionCalculeMontant e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		type = 2;
		annuiteMaximale = 33090.33;
		try {
			credit = Credit.calculeMontantEmprunte(Credit.ANNUITES_CONSTANTES, 33090.33, 0.03, 4);
			assertTrue(type == credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.01);
			assertTrue(annuiteMaximale == credit.annuiteMaximale());
			assertTrue(duree == credit.duree());
			assertTrue(taux == credit.taux());
		} catch (ExceptionCalculeMontant e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCalculeAnnuiteMaximale() {
		int type = 1;
		double montant = 100000;
		double annuiteMaximale = 10000;
		int duree = 20;
		double taux = 0.05;
		Credit credit;
		try {
			credit = Credit.calculeAnnuiteMaximale(Credit.AMORTISSEMENT_CONSTANTS, 100000, 0.05, 20);
			assertTrue(type == credit.typeCredit());
			assertTrue(montant == credit.montantEmprunte());
			assertTrue(annuiteMaximale == credit.annuiteMaximale());
			assertTrue(duree == credit.duree());
			assertTrue(taux == credit.taux());
		} catch (ExceptionCalculeAnnuiteMaximale e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		type = 2;
		annuiteMaximale = 8024.26;
		try {
			credit = Credit.calculeAnnuiteMaximale(Credit.ANNUITES_CONSTANTES, 100000, 0.05, 20);
			assertTrue(type == credit.typeCredit());
			assertTrue(montant == credit.montantEmprunte());
			assertTrue(annuiteMaximale == credit.annuiteMaximale());
			assertTrue(duree == credit.duree());
			assertTrue(taux == credit.taux());
		} catch (ExceptionCalculeAnnuiteMaximale e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
