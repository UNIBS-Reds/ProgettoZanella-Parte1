package progettoINGSW;

public class ProgettoIngeSWMain {
	public final static String CORNICE = "-----------------------------------------------------------------------\n";
	public final static String TITOLO_MENU_PRINCIPALE = "Menu Principale\n"; 
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello :\n";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello :\n";
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.\n";
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	public final static String MSG_USCITA_PROGRAMMA = "Esci dal programma.\n";
	public final static String MSG_ERRORE = "L'opzione inserita è inesistente. Inserire un'opzione valida.\n";
	public final static String MSG_NUOVO_MODELLO = "Crea nuovo modello";
	public final static String MSG_CARICAMENTO_MODELLO = "Carica modello";
	
	public static void main(String[] args) {
		benvenuto();
		
		Menu menuPrincipale = new Menu(TITOLO_MENU_PRINCIPALE);
		menuPrincipale.addVoce(MSG_NUOVO_MODELLO);
		menuPrincipale.addVoce(MSG_CARICAMENTO_MODELLO);
		menuPrincipale.addVoce(MSG_USCITA_PROGRAMMA);
		
		boolean finito = false;
		do {
			switch(menuPrincipale.scegli()) {
				case 1:
					String nome_modello = UtilitaGenerale.leggiString(MSG_NOME_MODELLO);
					String descrizione_modello = UtilitaGenerale.leggiString(MSG_DESCRIZIONE_MODELLO);
					Modello m = new Modello(nome_modello, descrizione_modello);
					m.creaNodoIniziale();
					m.inserimentoEntita(null);
					break;
				case 2: System.out.println("Funzionalità non ancora implementata.\n"); break;
				case 3:
						finito = true; break;
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);
		saluta();
	}   
	
//	public static void caricamentoModello() {};
	
	public static void benvenuto()
	{
		System.out.println(CORNICE);
		System.out.println(MSG_BENVENUTO);
	}
	
	public static void saluta()
	{
		System.out.println(CORNICE);
		System.out.print(MSG_SALUTO);
	}
}