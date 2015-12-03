package test;

import static org.junit.Assert.*;

import org.junit.Test;

import amortissements.Credit;
import amortissements.Ligne;
import amortissements.TableauAmortissement;

import exceptions.MonException;
import interfaceGraphique.FenetrePrincipale;

/**
 * Classe de test de la classe TableauAmortissement.
 * @author Jean
 *
 */
public class testTableauAmortissement {

	Credit cred;
	double montant;
	double annuiteMaximale;
	double taux;
	TableauAmortissement tab;
	
	/**
	 * Constructeur de la classe testTableauAmortissement,
	 * permet de créer un nouveau tableau d'amortissement a partir
	 * du Credit cred.
	 * @throws MonException
	 */
	public testTableauAmortissement() throws MonException {
		this.montant = 100000;
		this.annuiteMaximale = 10000;
		this.taux = 0.05;
		this.cred = Credit.calculeDuree(Credit.AMORTISSEMENT_CONSTANTS, montant, annuiteMaximale, taux);		
		this.tab = new TableauAmortissement(cred);
	}

	@Test
	/**
	 * Permet de tester si le nombre de lignes du tableau est bon.
	 */
	public void testGetNbLignes() {
		assertEquals(cred.duree(), 20);
		System.out.println(cred.duree());
	}

	@Test
	/**
	 * Permet de recuperer une ligne du tableau et voir si
	 * ses valeurs sont correctes, ici la premiere ligne.
	 */
	public void testGetLigne() {
		Ligne li = tab.getLigne(1);
		assertEquals(li.getAmortissements(), 5000, 0.01);
		assertEquals(li.getAnnee(), 2, 0.01);
		assertEquals(li.getAnnuite(), 9750.00, 0.01);
		assertEquals(li.getCapitalFinal(), 90000.00, 0.01);
		assertEquals(li.getCapitalInitial(), 95000.00, 0.01);
		assertEquals(li.getInterets(), 4750.00, 0.01);
	}
}
