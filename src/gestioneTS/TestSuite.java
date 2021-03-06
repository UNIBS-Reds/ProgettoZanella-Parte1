/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneTS;

import java.io.Serializable;

import gestioneModello.Modello;
import statisticheGuasti.Distanze;
import statisticheGuasti.OrdinaElencoProbabilitaEIntervalliPosizione;

import java.util.Vector;

/**
 * La classe Test Suite.
 * Un Test Suite e' un insieme di prove, strutturate in classi di equivalenza.
 * 
 * INVARIANTE DI CLASSE : Un Test Suite non puo' contenere due classi di equivalenza uguali.
 */
public class TestSuite implements Serializable {
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costante per stampa a video */
	public final static String MSG_INTESTAZIONE_TS = "\nTEST SUITE PER IL MODELLO %s\n";
	
	/** Vector con le classi di equivalenza che costituiscono il Test Suite. */
	private Vector <ClasseEquivalenza> elencoClassi;
	
	/** La diagnosi associata al Test Suite */
	private Diagnosi diag;
	
	/** Il Modello relativo al Test Suite */
	private Modello mod;
	
	/** Gli elenchi delle probabilita' */
	private OrdinaElencoProbabilitaEIntervalliPosizione elencoProb1;
	private OrdinaElencoProbabilitaEIntervalliPosizione elencoProb2;
	
	/** Le distanze */
	private Distanze dist;
	
	/** L'istanza unica di Test Suite */
	private static TestSuite instance = null;
	
	/**
	 * Costruttore della classe TestSuite. 
	 * Crea la struttura dati per contenere le classi di equivalenza inserite dall'utente.
	 */
	private TestSuite() {
		elencoClassi = new Vector <ClasseEquivalenza>();
	}
	
	/**
	 * Fornisce l'istanza singola di TestSuite
	 *
	 * @return Il Test Suite corrente. Se vuoto ne crea uno nuovo.
	 */
	public static TestSuite getInstance() {
		if(instance==null)
			instance = new TestSuite();
		return instance;
	}
	 
	/**
	 * Cambia test suite.
	 *
	 * @param nuovo : il nuovo Test Suite
	 */
	public static void cambiaTestSuite(TestSuite nuovo) {
		instance = nuovo;
	}
	
	/**
	 * Controlla se il Test Suite e' nullo.
	 *
	 * @return true, se non c'e' nessun Test Suite inserito
	 */
	public static boolean isNull() {
		if(instance==null)
			return true;
		return false;
	}
	
	/**
	 * Aggiunge la classe di equivalenza.
	 *
	 * @param clEq : la classe d'equivalenza da aggiungere alla struttura dati
	 */
	public void addClasseEquivalenza(ClasseEquivalenza clEq) {
		//PRECONDIZIONE
		assert clEq!=null : "Violata precondizione metodo addClasseEquivalenza. Passata classe nulla.";
		
		int sizeVecchia = elencoClassi.size();
		elencoClassi.add(clEq);
		
		//POSTCONDIZIONE
		assert elencoClassi.size() == sizeVecchia+1;
	}
	
	/**
	 * Controlla se una classe di equivalenza e' gia' presente nel Test Suite, per evitare
	 * l'inserimento di doppioni
	 *
	 * @param ce : la classe d'equivalenza di cui verificare la presenza.
	 * @return true, se la classe e' gia' presente, false altrimenti.
	 */
	public boolean giaInserita(ClasseEquivalenza ce) {
		//PRECONDIZIONE
		assert ce!=null : "Violata precondizione nel metodo giaInserita. Passata classe nulla.";
		
		for(int i=0; i<elencoClassi.size(); i++)
			if(elencoClassi.elementAt(i).isEqual(ce))
				return true;
		return false;
	} 
		
	/**
	 * Fornisce le classi d'equivalenza del Test Suite.
	 *
	 * @return la struttura dati contenente le classi.
	 */
	public Vector <ClasseEquivalenza> getElencoClassi() {
		return elencoClassi;
	}
	
	/**
	 * Fornisce la diagnosi
	 *
	 * @return la diagnosi
	 */
	public Diagnosi getDiagnosi() {
		return diag;
	}
	
	/**
	 * Fornisce l'elenco probabilita 1
	 *
	 * @return l'elenco probabilita 1
	 */
	public OrdinaElencoProbabilitaEIntervalliPosizione getElencoProb1() {
		return elencoProb1;
	}
	
	/**
	 * Fornisce l'elenco probabilita 2
	 *
	 * @return l'elenco probabilita 2
	 */
	public OrdinaElencoProbabilitaEIntervalliPosizione getElencoProb2() {
		return elencoProb2;
	}
	
	/**
	 * Fornisce le distanze
	 *
	 * @return le distanze
	 */
	public Distanze getDistanze() {
		return dist;
	}
	/**
	 * Fornisce il modello relativo al Test Suite
	 *
	 * @return il modello 
	 */
	public Modello getModello() {
		return mod;
	}
	
	/**
	 * Controlla se il Test Suite ha gia' un insieme delle diagnosi associato
	 * 
	 * @return true, se la diagnosi e' diversa da null, false altrimenti
	 */
	public boolean hasDiagnosi() {
		if(diag == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Controlla se il Test Suite ha associate le probabilita' e le distanze.
	 * 
	 * @return true, se le probabilita' e le distanze sono null, false altrimenti
	 */
	public boolean hasProbabilitaDistanze() {
		if(elencoProb1 == null || elencoProb2 == null || dist == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Confronta due Test Suite verificandone l'uguaglianza.
	 * 
	 * @param altro : il Test Suite con cui confrontare
	 * @return true, se i due Test Suite sono uguali, false altrimenti.
	 */
	public boolean isEqual(TestSuite altro) {
		for(int i=0; i<elencoClassi.size(); i++)
			if(!altro.getElencoClassi().elementAt(i).isEqual(elencoClassi.elementAt(i)))
				return false;
		return true;
	}
	
	/**
	 * Setta l'istanza di Diagnosi
	 * 
	 * @param d : la diagnosi da associare.
	 */
	public void setDiagnosi(Diagnosi d) {
		diag = d;
	}
	
	/**
	 * Setta l'istanza di Probabilita'
	 * 
	 * @param elenco1 : l'elenco di probabilita' col metodo 1 
	 */
	public void setElencoProb1(OrdinaElencoProbabilitaEIntervalliPosizione elenco1) {
		elencoProb1 = elenco1;		
	}
	
	/**
	 * Setta l'istanza di Probabilita'
	 * 
	 * @param elenco2 : l'elenco di probabilita' col metodo 2 
	 */
	public void setElencoProb2(OrdinaElencoProbabilitaEIntervalliPosizione elenco2) {
		elencoProb2 = elenco2;		
	}
	
	/**
	 * Setta le distanze
	 * 
	 * @param di : le distanze 
	 */
	public void setDistanze(Distanze di) {
		dist = di;
	}
	
	/**
	 * Setta l'istanza di modello.
	 *
	 * @param m : il modello da associare
	 */
	public void setModello(Modello m) {
		mod = m;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(MSG_INTESTAZIONE_TS, mod.getNome()));
		for(int i=0; i<elencoClassi.size(); i++) {
			risultato.append("\n\n");
			risultato.append(String.format("- CLASSE DI EQUIVALENZA N.%d\n"+elencoClassi.elementAt(i).toString(),i+1));
		}
		return risultato.toString();
	}
	
}