package test;

import static org.junit.Assert.*;

import org.junit.Test;

import amortissements.Credit;

import exceptions.MonException;


public class testCredit {

	@Test
	public void testCalculeTaux() throws MonException {
		int type = 1;
		double montant = 100000;
		double annuiteMaximale = 10000;
		int duree = 20;
		double taux = 0.05;
		Credit credit;
		
			credit = Credit.calculeTaux(Credit.AMORTISSEMENT_CONSTANTS, 100000, 10000, 20);
			assertEquals(type, credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.001);
			assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.001);
			assertEquals(duree, credit.duree());
			assertEquals(taux, credit.taux(), 0.001);
		
		
		
		annuiteMaximale = 8024.26;
		type = 2;
		credit = Credit.calculeTaux(Credit.ANNUITES_CONSTANTES, 100000, 8024.26, 20);
		assertEquals(type, credit.typeCredit());
		assertEquals(montant, credit.montantEmprunte(), 0.001);
		assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.001);
		assertEquals(duree, credit.duree());
		assertEquals(taux,credit.taux(),0.001);
	}

	@Test
	public void testCalculTauxAnnuiteConstante() {
		assertEquals(1317.54, Credit.calculTauxAnnuiteConstante(12000, 15, 0.07), 0.01);
	}

	@Test
	public void testCalculeDuree() throws MonException {
		int type = 1;
		double montant = 65000;
		double annuiteMaximale = 11122.22;
		int duree = 10;
		double taux = 0.06;
		Credit credit;
		
			credit = Credit.calculeDuree(Credit.AMORTISSEMENT_CONSTANTS, 65000, 11122.22,0.06);
			assertEquals(type, credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.001);
			assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.001);
			assertEquals(duree, credit.duree());
			assertEquals(taux, credit.taux(), 0.001);
	
		
		
		type = 2;
		annuiteMaximale = 9556.45;
		duree = 9;
		
			credit = Credit.calculeDuree(Credit.ANNUITES_CONSTANTES, 65000, 9556.45, 0.06);
			assertEquals(type, credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.001);
			assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.001);
			assertEquals(duree, credit.duree());
			assertEquals(taux, credit.taux(), 0.001);
		
		
	}

	@Test
	public void testCalculeMontantEmprunte() throws MonException {
		int type = Credit.AMORTISSEMENT_CONSTANTS;
		double montant = 123000;
		double annuiteMaximale = 34440.00;
		int duree = 4;
		double taux = 0.03;
		Credit credit;
		
			credit = Credit.calculeMontantEmprunte(Credit.AMORTISSEMENT_CONSTANTS, 34440.00, 0.03, 4);
			assertEquals(type, credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.001);
			assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.001);
			assertEquals(duree, credit.duree());
			assertEquals(taux, credit.taux(), 0.001);
		
		
		
		type = Credit.ANNUITES_CONSTANTES;
		annuiteMaximale = 34687;
			montant = 122998.385138;
			taux = 0.05;
			credit = Credit.calculeMontantEmprunte(Credit.ANNUITES_CONSTANTES, 34687, 0.05, 4);
			assertEquals(type, credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.01);
			assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.001);
			assertEquals(duree, credit.duree());
			assertEquals(taux, credit.taux(), 0.001);
	}

	@Test(expected=MonException.class)
	public void testCalculeAnnuiteMaximaleTauxNegatif() throws MonException {

			Credit.calculeMontantEmprunte(Credit.ANNUITES_CONSTANTES, 33090.33, 0.05, -4);
		
	}

	@Test
	public void testCalculeAnnuiteMaximale() throws MonException{
		int type = 1;
		double montant = 100000;
		double annuiteMaximale = 10000;
		int duree = 20;
		double taux = 0.05;
		Credit credit;
		
			credit = Credit.calculeAnnuiteMaximale(Credit.AMORTISSEMENT_CONSTANTS, 100000, 0.05, 20);
			assertEquals(type, credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.001);
			assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.001);
			assertEquals(duree, credit.duree());
			assertEquals(taux, credit.taux(), 0.001);
		
		
		
		type = 2;
		annuiteMaximale = 8024.26;
		
			credit = Credit.calculeAnnuiteMaximale(Credit.ANNUITES_CONSTANTES, 100000, 0.05, 20);
			assertEquals(type, credit.typeCredit());
			assertEquals(montant, credit.montantEmprunte(), 0.001);
			assertEquals(annuiteMaximale, credit.annuiteMaximale(), 0.01);
			assertEquals(duree, credit.duree());
			assertEquals(taux, credit.taux(), 0.001);
		
		
	}

}
