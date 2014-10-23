package Utilita;

import java.util.Vector;
import javax.swing.JOptionPane;

/**
 * Classe contenente metodi per abbellire i programmi.
 * @author Lorenzo Rubagotti.
 */
public class GUI
{

	private final static String SPAZIO = " ";
	
	@SuppressWarnings("unused")
	private final static String ACAPO = "\n";
	
	private static final String RIGASOTTO = "=";
	private static final String RIGALATO = "|";
	private static final int MARGINE = 2;
	
	/**
	 * Metodo che incornicia una stringa. Ideale per il titolo.
	 * @param titolo Stringa da incorniciare.
	 */
	public static void incorniciaTitolo(String titolo)
	{
		rigaSotto(titolo.length());
		System.out.println();
		rigaLato();
		System.out.print(titolo);
		rigaLato();
		rigaSotto(titolo.length());
	}
	
	/**
	 * Metodo che incornicia il menu', o qualunque vettore di stringhe.
	 * @param titoloMenu titolo del menu'.
	 * @param menu Menu' o vettore di stringhe.
	 * @param spunta1 Attiva (1) o disattiva (0) la divisione di ogni riga.
	 */
	public static void incorniciaMenu(String titoloMenu, Vector<String> menu, int spuntaDivisioneRighe)
	{
		//titolo centrato del menu'
		System.out.print(titoloMenu);
		//
		//Cerco la stringa con lunghezza massima.
		int lunghezzaMassima = OurMath.lunghezzaMassimaVectorString(menu);
		//
		
		rigaSotto(lunghezzaMassima);
		System.out.println();
		for (int i = 0; i < menu.size(); i++)
			{
			rigaLato();
			System.out.print(incolonna(menu.get(i), lunghezzaMassima));
			rigaLato();
			
			//Divisione per ogni riga.
			if(spuntaDivisioneRighe == 1)
				rigaSotto(lunghezzaMassima);
			
			if (i != menu.size() - 1)
				System.out.println();
			}
		//toglie l'ultima riga (che sarebbe altrimenti ripetuta), se attivata la divisione per ogni riga.
		if(spuntaDivisioneRighe == 0)
			rigaSotto(lunghezzaMassima);
		
		System.out.println();
	}
	
	//Chiamata rigaSotto perche' pronta per quello scopo, se la uso sopra devo sistemarla con un a capo.
	/**
	 * Metodo che visualizza la riga sotto di cio' che si incornicia.
	 * @param lunghezzaTitolo Lunghezza lungo la quale far passare la riga.
	 */
	public static void rigaSotto(int lunghezzaTitolo)
	{	
		System.out.println();
		for (int i = 0; i < lunghezzaTitolo + MARGINE; i++)
		System.out.print(RIGASOTTO);
	}

	/**
	 * Metodo che stampa la riga di lato.
	 */
	public static void rigaLato()
	{
		System.out.print(RIGALATO);
	}
	
	/**
	 * Metodo che visualizza una finestra per chiederti l'intero da acquisire.
	 * @param messaggio Messaggio che chiede cosa fare.
	 * @return Restituisce il numero acquisito.
	 */
	public static int imputDialog(String messaggio)
	{
		String input = JOptionPane.showInputDialog(messaggio);
		Integer numero = Integer.parseInt(input);
		return numero;
	}
	
	/**
	 * Metodo che incolonna una stringa data la larghezza.
	 * @param s Stringa da incolonnare.
	 * @param larghezza Larghezza della Stringa.
	 * @return Restituisce la stringa incolonnata.
	 */
	public static String incolonna (String s, int larghezza)
		{
		StringBuffer res = new StringBuffer(larghezza);
		int numCharDaStampare = Math.min(larghezza,s.length());
		res.append(s.substring(0, numCharDaStampare));
		for (int i=s.length()+1; i<=larghezza; i++)
			res.append(SPAZIO);
		return res.toString();
		}
	
	 /**
	  * Metodo che centra la stringa data la larghezza.
	  * @param s Stringa da centrare.
	  * @param larghezza Larghezza.
	  * @return Restituisce la stringa centrata.
	  */
	 public static String centrata (String s, int larghezza)
		{
		 StringBuffer res = new StringBuffer(larghezza);
		 if (larghezza <= s.length())
			res.append(s.substring(larghezza));
		 else
			{
			 int spaziPrima = (larghezza - s.length())/2;
			 int spaziDopo = larghezza - spaziPrima - s.length();
			 for (int i=1; i<=spaziPrima; i++)
				res.append(SPAZIO);
				
			 res.append(s);
			
			 for (int i=1; i<=spaziDopo; i++)
				res.append(SPAZIO);
			}
		 	 return res.toString();

		}
	 
	 /**
	  * Metodo che ripete un carattere data una quantita'.
	  * @param carattere Cio' che va ripetuto.
	  * @param lunghezza Quantita'.
	  * @return Restituisce la stringa contente il carattere ripetuto.
	  */
	 public static String ripetiChar (char carattere, int lunghezza)
		{
			StringBuffer result = new StringBuffer(lunghezza);
			for (int i = 0; i < lunghezza; i++)
				{
				result.append(carattere);
				}
			return result.toString();
		}	 

		/**
		  * Metodo che ripete una stringa data una quantita'.
		  * @param stringa Cio' che va ripetuto.
		  * @param lunghezza Quantita'.
		  * @return Restituisce la stringa contente la stringa iniziale ripetuta.
		  */
	 public static String ripetiStringa(String stringa, int lunghezza)
		{
			StringBuffer result = new StringBuffer(lunghezza);
			for (int i = 0; i < lunghezza; i++)
			{
				result.append(stringa);
			}
			return result.toString();
		}
}
