package mainProgram;

import java.io.File;
import java.util.Vector;

import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.TestSuite;
import utilita.*;
import gestioneModello.Azione;
import gestioneModello.Entita;
import gestioneModello.Modello;
import gestioneModello.NodoIniziale;
import testSuiteDiagnosi.*;

public class ElaboratoParte1Main {

	public final static String MSG_TITOLO_MENU_PRINCIPALE = "BENVENUTO NEL MENU' PRINCIPALE\n\nCosa si desidera fare?"; 
	public final static String MSG_NUOVO_MODELLO = "1 - Crea Nuovo Modello";
	public final static String MSG_VISUALIZZAZIONE_MODELLO = "2 - Visualizza Modello";
	public final static String MSG_INS_TEST_SUITE = "3 - Inserimento Test Suite";
	public final static String MSG_VISUALIZZAZIONE_DIAGNOSI = "4 - Visualizza Insiemi delle Diagnosi";
	public final static String MSG_VISUALIZZAZIONE_PROBABILITA = "5 - Visualizza Probabilita' e Distanze";
	public final static String MSG_VISUALIZZAZIONE_REPORT = "6 - Visualizza Report Completo";
	public final static String MSG_CARICAMENTO_MODELLO = "7 - Carica";
	public final static String MSG_USCITA_PROGRAMMA = "8 - Esci dal programma.";
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	
	public final static String MSG_NO_MODELLO = "Errore! Nessun modello inserito.";
	public final static String MSG_STAMPA_MOD_CORRENTE = "STAMPA DEL MODELLO CORRENTE\n";
	public final static String MSG_MODELLO_CARICATO = "Il modello %s e' stato caricato con successo.";
	public final static String MSG_TS = "\n\nCREAZIONE DEL TEST SUITE RELATIVO AL MODELLO %s\n\n";
	public final static String MSG_CAMM_GLOBALE_1 = "Scegliere le azioni facenti parte del cammino globale relativo alla classe di equivalenza.";
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	public final static String MSG_AGGIUNTA_INS_CAMM = "Si desidera aggiungere l'azione %s all'insieme del cammino?";
	public final static String MSG_INS_COP = "INSERIMENTO INSIEME DI COPERTURA";
	public final static String MSG_INS_CAMMINO = "Scegliere le azioni da aggiungere all'insieme del cammino";
	public final static String MSG_INS_CLASSE_EQ = "CLASSE DI EQUIVALENZA N. %d - INSERIMENTO INFORMAZIONI";
	public final static String MSG_CARD_CE = "Inserire la cardinalita' relativa alla classe di equivalenza : ";
	public final static String MSG_CONTINUA_SI_NO_CE = "Si desidera inserire un'altra classe di equivalenza?";
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	public final static String MSG_CONTINUA_SI_NO_COPPIA = "Si desidera inserire un'altra coppia (insieme del cammino ; valore della rilevazione)?";
	public final static String MSG_VAL_RILEV = "Inserire il valore della rilevazione relativa all'insieme del cammino";
	public final static String MSG_SINTESI_TS = "Si desidera vedere una sintesi delle classi di equivalenza e degli insiemi di copertura inseriti\nper il TS corrente?";
	
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello: ";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";
	
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.";
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	
	private static final String MSG_NOME_MODELLO_PREESISTENTE = "Nome modello da caricare:";
	
	public static void main(String[] args) {
		benvenuto();
		Vector<String> vociMenuPrincipale = new Vector<String>();
		
		vociMenuPrincipale.add(MSG_NUOVO_MODELLO);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_MODELLO);
		vociMenuPrincipale.add(MSG_INS_TEST_SUITE);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_DIAGNOSI);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_PROBABILITA);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_REPORT);
		vociMenuPrincipale.add(MSG_CARICAMENTO_MODELLO);
		vociMenuPrincipale.add(MSG_USCITA_PROGRAMMA);
		Menu menuPrincipale = new Menu(MSG_TITOLO_MENU_PRINCIPALE, vociMenuPrincipale);
		
		boolean finito = false;
		do {
			switch(menuPrincipale.scegliVoce()) {
				case 1:
					inserimentoNuovoModello();
					break;
				
				case 2: 
					visualizzaModelloCorrente();
					break;  
				
				case 3:
					inserimentoTS();
					break;
				
				case 4:
					TestSuite ts = TestSuite.getInstance();
					if(ts==null)
						System.out.println("TS nullo");
					Diagnosi d1 = new Diagnosi(1, ts);
					Diagnosi d2 = new Diagnosi(2, ts);
					break;	
					
				case 5:
					System.out.println("Visualizzazione probabilita' e distanze da implementare...");
					break;
				
				case 6:
					System.out.println("Visualizzazione report completo da implementare...");
					break;
					
				case 7:
					caricamentoModello();
					break;
				
				case 8 : 
					saluta(); 
					finito = true; 
					break;
					
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);
	}   
	
	public static void inserimentoNuovoModello() {
		String nome_modello = Util.leggiString(MSG_NOME_MODELLO);
		String descrizione_modello = Util.leggiString(MSG_DESCRIZIONE_MODELLO);
		Modello m = Modello.getInstance();
		m.setNome(nome_modello);
		m.setDescrizione(descrizione_modello);
		NodoIniziale ni = new NodoIniziale();
		m.addEntita(ni);
		m.getGm().menuInserimentoPrimario();
	}
	
	public static void visualizzaModelloCorrente() {
		if(Modello.isNull())
			System.out.println(MSG_NO_MODELLO);
		else 
		{
			Modello daVisualizzare = Modello.getInstance();
			System.out.println(MSG_STAMPA_MOD_CORRENTE);
			System.out.println(daVisualizzare.toString());
		}
	}
	
	public static void inserimentoTS() { 
		TestSuite ts = TestSuite.getInstance();
		boolean continua = false;
		Modello modelloCorrente = null;
		if(Modello.isNull()==false) {
			modelloCorrente = Modello.getInstance();
			ts.setModello(modelloCorrente);
		}
		else 
		{
			System.out.println(MSG_NO_MODELLO);
			continua = true;
		}
	/*	ClasseEquivalenza c1 = new ClasseEquivalenza(2);
		Vector <Azione> insCamm1 = new Vector<Azione>();
		Vector <Azione> insGlobale = modelloCorrente.getElencoAzioni();
		insCamm1.add(insGlobale.elementAt(0));
		insCamm1.add(insGlobale.elementAt(2));
		insCamm1.add(insGlobale.elementAt(3));
		insCamm1.add(insGlobale.elementAt(4));
		Coppia coppia1 = new Coppia(insCamm1, "KO");
		Vector <Azione> insCamm2 = new Vector<Azione>();
		insCamm2.add(insGlobale.elementAt(0));
		Coppia coppia2 = new Coppia(insCamm2, "OK");
		c1.addCoppia(coppia1);
		c1.addCoppia(coppia2);
		
		/*
		ClasseEquivalenza c2 = new ClasseEquivalenza(1);
		Vector <Azione> insCamm3 = new Vector<Azione>();
		insCamm3.add(insGlobale.elementAt(0));
		insCamm3.add(insGlobale.elementAt(2));
		insCamm3.add(insGlobale.elementAt(3));
		insCamm3.add(insGlobale.elementAt(4));
		Coppia coppia3 = new Coppia(insCamm3, "KO");
		Vector <Azione> insCamm4 = new Vector<Azione>();
		insCamm4.add(insGlobale.elementAt(0));
		Coppia coppia4 = new Coppia(insCamm4, "OK");
		c2.addCoppia(coppia3);
		c2.addCoppia(coppia4);
		Vector <Azione> insCamm5 = new Vector<Azione>();
		insCamm5.add(insGlobale.elementAt(0));
		insCamm5.add(insGlobale.elementAt(2));
		insCamm5.add(insGlobale.elementAt(3));
		Coppia coppia5 = new Coppia(insCamm5, "KO");
		c2.addCoppia(coppia5);
		
		ClasseEquivalenza c3 = new ClasseEquivalenza(1);
		Vector <Azione> insCamm6 = new Vector<Azione>();
		insCamm6.add(insGlobale.elementAt(0));
		insCamm6.add(insGlobale.elementAt(1));
		insCamm6.add(insGlobale.elementAt(2));
		insCamm6.add(insGlobale.elementAt(3));
		insCamm6.add(insGlobale.elementAt(5));
		Coppia coppia6 = new Coppia(insCamm6, "KO");
		Vector <Azione> insCamm7 = new Vector<Azione>();
		insCamm7.add(insGlobale.elementAt(0));
		Coppia coppia7 = new Coppia(insCamm7, "OK");
		c3.addCoppia(coppia6);
		c3.addCoppia(coppia7);
		Vector <Azione> insCamm8 = new Vector<Azione>();
		insCamm8.add(insGlobale.elementAt(0));
		insCamm8.add(insGlobale.elementAt(1));
		insCamm8.add(insGlobale.elementAt(2));
		Coppia coppia8 = new Coppia(insCamm8, "OK");
		c3.addCoppia(coppia8);       */
		
	//	ts.addClasseEquivalenza(c1);
	//	ts.addClasseEquivalenza(c2);
	//	ts.addClasseEquivalenza(c3);   */
		//Inserimento delle classi di equivalenza per il TS considerato
	
		if(continua) {
			System.out.println(String.format(MSG_TS, modelloCorrente.getNome()));		
			Vector <Azione> azioniModello = modelloCorrente.getElencoAzioni();
			int i=1;
			//Inserimento classi di equivalenza
			do {
				System.out.println(String.format(MSG_INS_CLASSE_EQ, i));
				//Inserimento cardinalita', creazione classe di equivalenza e aggiunta al TS
				int cardinalita = Util.leggiIntConMinimo(MSG_CARD_CE, 1);
				ClasseEquivalenza ce = new ClasseEquivalenza(cardinalita);
				ts.addClasseEquivalenza(ce);
				CamminoAzioni cammGlob = new CamminoAzioni();
				System.out.println(MSG_CAMM_GLOBALE_1);
				//Inserimento cammino globale
				for(int j=0; j<azioniModello.size(); j++) {
					Azione a = azioniModello.elementAt(j);
					if(Util.yesOrNo(String.format(MSG_AGGIUNTA_CAMM_GLOBALE,azioniModello.elementAt(j).getNome())))
						cammGlob.aggiungiAzione(a);
				}
				System.out.println(cammGlob.toString());
				System.out.println(MSG_INS_COP);
				//Inserimento insieme di copertura (insiemi di coppie insieme cammino - val rilev)
				do {
					CamminoAzioni insCamm = new CamminoAzioni();
					System.out.println(MSG_INS_CAMMINO);
					/*
					 * Le azioni che l'utente pu� inserire nell'insieme del cammino sono quelle del
					 * cammino globale, quindi e' garantito che ciascun insieme del cammino sia un 
					 * sottoinsieme del cammino globale.
					 */
					 
					for(int j=0; j<cammGlob.getNumeroAzioni(); j++) {
						Azione a = cammGlob.getAzioneAt(j);
						if(Util.yesOrNo(String.format(MSG_AGGIUNTA_INS_CAMM,azioniModello.elementAt(j).getNome())))
							insCamm.aggiungiAzione(a);
					}
					System.out.println(insCamm.toString());
					String valoreRilevazione = Util.okOrKo(MSG_VAL_RILEV);
					Coppia c = new Coppia(insCamm, valoreRilevazione);
					ce.addCoppia(c);
					System.out.println(String.format(MSG_COPPIA_AGGIUNTA,i));					
				} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_COPPIA));  			
			} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_CE));	
		
			boolean visualizzaSiNo = Util.yesOrNo(MSG_SINTESI_TS);
			if(visualizzaSiNo)
				System.out.println(ts.toString());
		}
	}
		
	public static void caricamentoModello() {
		File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_PREESISTENTE));
		Modello modelloCaricato = null;
		Stream.caricaFile(nomeFile, modelloCaricato);
		Modello.cambiaModello(modelloCaricato);
		System.out.println(MSG_MODELLO_CARICATO);
	};
	
	public static void benvenuto()
	{
		System.out.println(MSG_BENVENUTO);
	}
	
	public static void saluta()
	{
		System.out.print(MSG_SALUTO);
	}
}
