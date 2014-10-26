package gestioneModello;

import java.util.Vector;

import Utilita.GUI;
import Utilita.Menu;
import Utilita.Util;

public class GestoreModello {

	public final static String MSG_TITOLO_MENU_INSERIMENTO_MODELLO = "BENVENUTO NEL MENU INSERIMENTO MODELLO\n\nCosa si desidera fare?";
	public final static String MSG_TITOLO_MENU_BRANCH = "MENU GESTIONE BRANCH %s\n\nCosa si desidera fare?";
	public final static String MSG_TITOLO_MENU_CICLO = "MENU GESTIONE CICLO %s\n\nCosa si desidera fare?";
	public final static String MSG_TITOLO_MENU_FORK = "MENU GESTIONE FORK %s\n\nCosa si desidera fare?";
	
	public final static String MSG_INSERIMENTO_AZIONE = "1 - Inserimento Azione";
	public final static String MSG_INSERIMENTO_BRANCH = "2 - Inserimento Branch";
	public final static String MSG_INSERIMENTO_CICLO = "3 - Inserimento Ciclo";
	public final static String MSG_INSERIMENTO_FORK = "4 - Inserimento Fork";
	public final static String MSG_MODIFICA_MODELLO = "5 - Eliminare l'ultima entita' inserita";
	public final static String MSG_VISUALIZZAZIONE_MODELLO = "6 - Visualizzare Modello";
	public final static String MSG_USCITA_INSERIMENTO = "7 - Tornare al menu principale";
	public final static String MSG_CHIUSURA_RAMO = "7 - Chidere ramo corrente";
	public final static String MSG_INSERIMENTO_NODO_FINALE = "8 - Inserimento Nodo Finale";
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	
	public final static String MSG_TITOLO_AZIONE = "Digitare il titolo dell'azione che si sta inserendo: ";
	public final static String MSG_TITOLO_BRANCH = "Digitare il titolo del branch che si sta inserendo: ";
	public final static String MSG_TITOLO_CICLO = "Digitare il titolo del ciclo che si sta inserendo. ";
	public final static String MSG_TITOLO_FORK = "Digitare il titolo del costrutto fork-join che si sta inserendo: ";
	public final static String MSG_NUM_RAMI_BRANCH = "Quanti flussi d'uscita si vuole che abbia il nuovo Branch? ->";
	public final static String MSG_NUM_RAMI_FORK = "Quanti rami si vuole che abbia il fork? ->";
	public final static String MSG_NUOVA_ENTITA = "La nuova entita' %s e' stata aggiunta a %s";
	public final static String MSG_ERRORE_RAMI = "Tutti i rami di %s sono vuoti. Impossibile creare l'entita'.\nInserire nuovamente le entita' per i singoli rami.";
	
	public final static int MIN_RAMI = 2;
	
	public final static String MSG_RAMO_BRANCH = "%s (ID = %d) - INSERIMENTO ENTITA' PER IL RAMO N. %d";
	public final static String MSG_RAMO_FORK = "%s (ID = %d) - INSERIMENTO ENTITA' PER IL RAMO PARALLELO N. %d";
	public final static String MSG_RAMO_SUCC = "Per chiudere il ramo corrente selezionare l'opzione 'Chiudere ramo corrente'.\n";
	public final static String MSG_CHIUSURA_BRANCH = "Tutti i rami di %s (ID = %d) sono stati completati ed � stato creato il relativo Merge N.%d";
	public final static String MSG_CHIUSURA_CICLO = "E' stato completato l'inserimento di tutti i rami del Ciclo N.%d";
	public final static String MSG_CHIUSURA_FORK = "Tutti i rami paralleli di %s (ID = %d) sono stati completati ed � stato creato il relativo Join N.%d";
	public final static String MSG_ATTIVITA_INIZIALI_CICLO = "CICLO %s - INSERIMENTO ENTITA' PER IL RAMO 'ATTIVITA' INIZIALI'.\nNel caso in cui tale ramo venga lasciato vuoto verra' creato un ciclo\na condizione iniziale, altrimenti il ciclo sar� a condizione finale.\n";
	public final static String MSG_ATTIVITA_COND_USCITA_CICLO = "CICLO %s - INSERIMENTO ENTITA' PER IL RAMO 'CONDIZIONE D'USCITA'.\nTale ramo pu� essere lasciato vuoto.";
	public final static String MSG_ATTIVITA_COND_PERMANENZA_CICLO = "CICLO %s - INSERIMENTO ENTITA' PER IL RAMO 'CONDIZIONE DI PERMANENZA NEL CICLO'.\nTale ramo pu� essere lasciato vuoto (se non sono vuoti gli altri due rami)";
	
	public final static String MSG_RICHIESTA_SALVATAGGIO = "Ritorno al menu' principale. Tutti i progressi non salvati andranno persi.\nSi desidera salvare il modello? (y/n)";
	public final static String MSG_ULTIMA_ENTITA = "E' stata eliminata l'entita' di nome %s (id %d)";
	public final static String MSG_NODO_FINALE = "Nodo finale inserito per il modello %s";
	
	public final static String MSG_RAMI_BRANCH = "Quanti rami si vuole che abbia il branch?";
	public final static String MSG_DESCRIZIONE_AZIONE = "Fornire una breve descrizione dell'azione che si sta inserendo: ";
	public final static String MSG_COND_BRANCH = "Scrivere la condizione relativa al ramo n� %d del branch.\n";
	public final static String MSG_MODELLO_INCOMPLETO = "Attenzione! Per inserire il nodo finale e' necessario che nel modello sia\npresente almeno un'azione.\n";	
	public final static String MSG_ERRORE_MERGE = "Impossibile inserire il merge. Nessun branch da chiudere.\n";
	public final static String MSG_ERRORE_MERGE_2 = "Errore! Impossibile chiudere il merge. Nessuna entita'� tra il merge e il branch da chiudere.\n";
	public final static String MSG_INSERIMENTO_ENTITA_RAMO = "\nInserire le entita'�relative al ramo n� ";
	
	public final static int OPZ_BRANCH = 1;
	public final static int OPZ_CICLO = 2;
	public final static int OPZ_FORK = 3;

	protected static int contatoreEntita = 0;
	
	private Modello mod;
	
	public GestoreModello(Modello _mod) {
		mod = _mod;
	}
		
	public void menuInserimentoPrimario() {
		Vector<String> opzioniMenuInserimento = new Vector<String>();
		opzioniMenuInserimento.add(MSG_INSERIMENTO_AZIONE);      //Voce 1 --> azione
		opzioniMenuInserimento.add(MSG_INSERIMENTO_BRANCH);      //Voce 2 --> branch
		opzioniMenuInserimento.add(MSG_INSERIMENTO_CICLO);       //Voce 3 --> ciclo
		opzioniMenuInserimento.add(MSG_INSERIMENTO_FORK);        //Voce 4 --> fork 
		opzioniMenuInserimento.add(MSG_MODIFICA_MODELLO);        //Voce 5 --> modifica modello
		opzioniMenuInserimento.add(MSG_VISUALIZZAZIONE_MODELLO); //Voce 6 --> visualizza modello parziale
		opzioniMenuInserimento.add(MSG_USCITA_INSERIMENTO);      //Voce 7 --> ritorna al menu' principale (aggiungere richiesta di salvataggio modello)
		opzioniMenuInserimento.add(MSG_INSERIMENTO_NODO_FINALE); //Voce 8 --> nodo finale
		Menu menuInserimentoEntita = new Menu(MSG_TITOLO_MENU_INSERIMENTO_MODELLO,opzioniMenuInserimento);
		
		boolean insFinito = false;
		GUI.setRientro(0);
		
		do {
			switch(menuInserimentoEntita.scegliVoce()) {
				case 1:    
					inserimentoAzione(mod,0);
					break;

				case 2:   
					inserimentoBranch(mod,0);      
					break;
				
				case 3 : 
					inserimentoCiclo(mod,0);
					break;
					
				case 4 :
					inserimentoFork(mod,0);
					break;
					
				case 5: 
					System.out.println(String.format(MSG_ULTIMA_ENTITA, mod.getUltimaEntita().getNome(), mod.getUltimaEntita().getId()));
					mod.eliminaUltimoElemento();
					break;
					
				case 6: 
					System.out.println(mod.toString()); 
					break;
				
				case 7: 
					if(Util.yesOrNo(MSG_RICHIESTA_SALVATAGGIO))
						System.out.println("Implementare il salvataggio...");
					insFinito = true;
					break;
					
				case 8 :
					if(getNumeroAzioni()>=1)
					{
						inserimentoNodoFinale();
						break;
					}
					else
					{
						System.out.println(MSG_MODELLO_INCOMPLETO);
						break;
					}
					
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(insFinito == false);
	}
	
	public void menuInserimentoSecondario(Entita e, int tipo) {
			String t = "";
			
			//METTERE METODO AUSILIARIO IN FASE DI REFACTORING !!!!
			switch(tipo) {
			case OPZ_BRANCH: t=MSG_TITOLO_MENU_BRANCH; break;
			case OPZ_CICLO: t=MSG_TITOLO_MENU_CICLO; break;
			case OPZ_FORK: t=MSG_TITOLO_MENU_FORK; break;
			}
		
			Vector <String> opzioniMenuSecondario = new Vector<String>();
			opzioniMenuSecondario.add(MSG_INSERIMENTO_AZIONE);      //Voce 1 --> azione
			opzioniMenuSecondario.add(MSG_INSERIMENTO_BRANCH);      //Voce 2 --> branch
			opzioniMenuSecondario.add(MSG_INSERIMENTO_CICLO);       //Voce 3 --> ciclo
			opzioniMenuSecondario.add(MSG_INSERIMENTO_FORK);        //Voce 4 --> fork 
			opzioniMenuSecondario.add(MSG_MODIFICA_MODELLO);        //Voce 5 --> modifica modello
			opzioniMenuSecondario.add(MSG_VISUALIZZAZIONE_MODELLO); //Voce 6 --> visualizza modello parziale
			opzioniMenuSecondario.add(MSG_CHIUSURA_RAMO);           //Voce 7 --> Chiudi ramo
			Menu menuSecondario = new Menu(String.format(t, e.getNome()),opzioniMenuSecondario);
			
			for(int i=0; i<e.getRami().length; i++)
			{
				switch(tipo) {
				case OPZ_BRANCH: System.out.println(String.format(MSG_RAMO_BRANCH, e.getNome(), e.getId(), i+1));
								 System.out.println(MSG_RAMO_SUCC);
								 break;
				case OPZ_CICLO:
				{
					if(i==0)
					{
						System.out.println(String.format(MSG_ATTIVITA_INIZIALI_CICLO, e.getNome())); break;
					}
					else if(i==1)
					{
						System.out.println(String.format(MSG_ATTIVITA_COND_USCITA_CICLO, e.getNome())); break;
					}
					else if(i==2)
					{
						System.out.println(String.format(MSG_ATTIVITA_COND_PERMANENZA_CICLO, e.getNome())); break;
					}
				}
				case OPZ_FORK:   System.out.println(String.format(MSG_RAMO_FORK, e.getNome(), e.getId(), i+1));
				                 System.out.println(MSG_RAMO_SUCC);
				                 break;
				}
				
				boolean esci = false;
				do {
					switch(menuSecondario.scegliVoce()) {
					case 1:    
						inserimentoAzione(e,i);
						break;

					case 2:  
						inserimentoBranch(e,i);
						break;
					
					case 3 : 
						inserimentoCiclo(e,i);
						break;
						
					case 4 :
						inserimentoFork(e,i);
						break;
					
					case 5: //Controlla che ci sia almeno un'entita' inserita nel ramo corrente, altrimenti elimina il branch ed esci.
						/*if(ramoCorrente.getEntitaRamo().isEmpty())
						{
							eliminaUltimoElemento();
							esci = true;
							break;
						}
						else
						{
							eliminaUltimoElemento();
							break;
						}
						*/
						System.out.println("Work in progress...");
						break;
					case 6: 
						System.out.println(mod.toString()); 
						break;
					
					case 7: esci = true; break;  
						
					default : System.out.println(MSG_ERRORE);	
					}
				} while(!esci);
			} 
			switch(tipo) {
			case OPZ_BRANCH: System.out.println(String.format(MSG_CHIUSURA_BRANCH, e.getNome(), e.getId(), e.getId())); break;
			case OPZ_CICLO: System.out.println(String.format(MSG_CHIUSURA_CICLO, e.getId())); break;
			case OPZ_FORK: System.out.println(String.format(MSG_CHIUSURA_FORK, e.getNome(), e.getId(), e.getId())); break;
			}
	}
	
	public void inserimentoAzione (Entita e, int qualeRamo) {
		String t = Util.leggiString(MSG_TITOLO_AZIONE);
		Azione action = new Azione(t);
		e.addEntita(action, qualeRamo);
		System.out.println(String.format(MSG_NUOVA_ENTITA,action.getNome(),e.getNome()));
	}
	
	public void inserimentoBranch(Entita e, int qualeRamo) {
		String t = Util.leggiString(MSG_TITOLO_BRANCH);
		int n = Util.leggiIntConMinimo(MSG_NUM_RAMI_BRANCH, MIN_RAMI);
		Branch b = new Branch(t, n);
		GUI.incrementaRientro();
		for (int i=0; i<b.getNumeroRami(); i++)
			b.getRami()[i] = new Ramo();
		e.addEntita(b, qualeRamo);
		boolean ramiVuoti = false;
		do {
			menuInserimentoSecondario(b,OPZ_BRANCH);
			ramiVuoti = b.ramiTuttiVuoti();
			if(ramiVuoti)
				System.out.println(MSG_ERRORE_RAMI);
		} while(ramiVuoti == true);
		System.out.println(String.format(MSG_NUOVA_ENTITA,b.getNome(),e.getNome()));
		GUI.decrementaRientro();
	}
	
	public void inserimentoCiclo(Entita e, int qualeRamo) {
		String t = Util.leggiString(MSG_TITOLO_CICLO);
		Ciclo c = new Ciclo(t);
		for (int i=0; i<c.getNumeroRami(); i++)
			c.getRami()[i] = new Ramo();
		GUI.incrementaRientro();
		e.addEntita(c, qualeRamo);
		boolean ramiVuoti = false;
		do {
			menuInserimentoSecondario(c,OPZ_CICLO);
			ramiVuoti = c.ramiTuttiVuoti();
			if(ramiVuoti)
				System.out.println(MSG_ERRORE_RAMI);
		} while(ramiVuoti == true);
		System.out.println(String.format(MSG_NUOVA_ENTITA,c.getNome(),e.getNome()));
		GUI.decrementaRientro();
	}
	
	public void inserimentoFork(Entita e, int qualeRamo) {
		String t = Util.leggiString(MSG_TITOLO_FORK);
		int n = Util.leggiIntConMinimo(MSG_NUM_RAMI_FORK, MIN_RAMI);
		Fork temp = new Fork(t, n);
		for (int i=0; i<temp.getNumeroRami(); i++)
			temp.getRami()[i] = new Ramo();
		GUI.incrementaRientro();
		e.addEntita(temp, qualeRamo);
		boolean ramiVuoti = false;
		do {
			menuInserimentoSecondario(temp,OPZ_FORK);
			ramiVuoti = temp.ramiTuttiVuoti();
			if(ramiVuoti)
				System.out.println(MSG_ERRORE_RAMI);
		} while(ramiVuoti == true);
		System.out.println(String.format(MSG_NUOVA_ENTITA,temp.getNome(),e.getNome()));
		GUI.decrementaRientro();
	}
	
	public void inserimentoNodoFinale () {
		NodoFinale nodo_f = new NodoFinale();
		mod.addEntita(nodo_f);
		System.out.println(String.format(MSG_NODO_FINALE,mod.getNome()));
	}
	
	private int getNumeroAzioni() {
		Azione tmp = new Azione("");
		tmp.decrementaContatore();
		return tmp.getId()-1;
	}
}