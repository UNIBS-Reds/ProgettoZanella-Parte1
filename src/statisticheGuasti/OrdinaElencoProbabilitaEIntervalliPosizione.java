/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package statisticheGuasti;
import java.io.Serializable;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class OrdinaElencoProbabilitaEIntervalliPosizione.
 */
public class OrdinaElencoProbabilitaEIntervalliPosizione implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private Vector<Float> probabilitaTestSuite;
	private int metodo;
	
	public OrdinaElencoProbabilitaEIntervalliPosizione (Vector<Float> probabilitaTestSuite, int metodo)
	{
		this.probabilitaTestSuite = probabilitaTestSuite;
		this.metodo = metodo;
		
		elencoProbabilitaOrdinatoSenzaDoppioni();
	}
	
	/**
	 * Elenco probabilita ordinato senza doppioni.
	 *
	 * @return the vector
	 */
	public Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni()
	{
		Vector<Tupla> risultato = RimuoviDoppioniElencoProbabilitaOrdinato(OrdinaElencoProbabilita(ElencoProbabilita(probabilitaTestSuite) ) );
		ToString(risultato, metodo);
		return risultato;
	}
	
	/**
	 * Elenco probabilita.
	 *
	 * @return the vector
	 */
	private Vector<Tupla> ElencoProbabilita (Vector<Float> probabilitaTestSuite)
	{
		//Inserisco l'elenco probabilita' in un vettore di tuple
		Vector<Tupla> elencoProbabilita = new Vector<Tupla>();
		for (int i = 0; i < probabilitaTestSuite.size(); i++)
		{
				Tupla tuplaDaInserire = new Tupla(probabilitaTestSuite.get(i), null );
				Vector<Integer> listaAzioni = new Vector<Integer>();
				listaAzioni.add(i + 1);
				tuplaDaInserire.setListaAzioni(listaAzioni);
				elencoProbabilita.add(tuplaDaInserire);
		}
		
		return elencoProbabilita;
	}
	
	/**
	 * Ordina elenco probabilita.
	 *
	 * @param elencoProbabilita the elenco probabilita
	 * @return the vector
	 */
	private Vector<Tupla> OrdinaElencoProbabilita(Vector<Tupla> elencoProbabilita)
	{
		Vector<Tupla> elencoProbabilitaT = elencoProbabilita;
				//Ordino le tuple
				Tupla temp;
				for (int i = 0; i < elencoProbabilitaT.size()-1; i++)
				{
					int iMax = i;
					Tupla tuplaConProbabilitaMassima = elencoProbabilitaT.get(i);
					for (int j = i; j < elencoProbabilitaT.size(); j++)
					{
						if (tuplaConProbabilitaMassima.getProbabilita() < elencoProbabilitaT.get(j).getProbabilita() )
							{
							tuplaConProbabilitaMassima = elencoProbabilitaT.get(j);
							iMax = j;
							}
					}
				temp = elencoProbabilitaT.get(i);
				elencoProbabilitaT.set(i, tuplaConProbabilitaMassima);
				elencoProbabilitaT.set(iMax, temp);
				
				
				}
		return elencoProbabilitaT;
	}

	/**
	 * Rimuovi doppioni elenco probabilita ordinato.
	 *
	 * @param elencoProbabilita the elenco probabilita
	 * @return the vector
	 */
	private Vector<Tupla> RimuoviDoppioniElencoProbabilitaOrdinato(Vector<Tupla> elencoProbabilita)
	{
		//Rimuovo i doppioni tenendo traccia di quali erano
		Vector<Integer> tupleDaEliminare = new Vector<Integer>();
			for (int j = 0; j < elencoProbabilita.size() - 1; j++)
			{
				for (int k = j+1; k < elencoProbabilita.size(); k++)
					if (elencoProbabilita.get(j).getProbabilita() == elencoProbabilita.get(k).getProbabilita() )
					{
						elencoProbabilita.get(j).getListaAzioni().add(elencoProbabilita.get(k).getListaAzioni().get(0) );
						boolean tuplaDaEliminarePresente = false;
						for (int t = 0; t < tupleDaEliminare.size(); t++)
							if (k == tupleDaEliminare.get(t) )
								tuplaDaEliminarePresente = true;
						if(!tuplaDaEliminarePresente)
						tupleDaEliminare.add(k);
					}
			}
			
			for ( int i = tupleDaEliminare.size() - 1; i >= 0; i--)
			{
				int daEliminare = tupleDaEliminare.get(i);
				elencoProbabilita.remove(daEliminare);
			}			
			
		return elencoProbabilita;
	}
	
	
	/**
	 * Intervalli posizione.
	 *
	 * @return the vector
	 */
	public Vector<int[]> IntervalliiPosizione()
	{
		Vector<int[]> intervalliPosizione = new Vector<int[]>();
		Vector<Integer> azioniOrdinatePerProbabilita = new Vector<Integer>();
		
		Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni = elencoProbabilitaOrdinatoSenzaDoppioni();
		
		int posizione = 1;
		for (int i = 0; i < elencoProbabilitaOrdinatoSenzaDoppioni.size(); i++)
		{
			int[] posizioni = new int[2];
			if (i > 0)
				posizione++;
			posizioni[0]= posizione;
			posizione += elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size() - 1;
			posizioni[1]= posizione;
			for (int j = 0; j < elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size(); j++)
				{
				intervalliPosizione.add(posizioni);
				azioniOrdinatePerProbabilita.add(elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().get(j) );
				}
		}
		
		Vector<int[]> intervalliPosizioneOrdinatiPerAzione = new Vector<int[]>();
		for (int i = 0; i < azioniOrdinatePerProbabilita.size(); i++)
		{
			int posizioneAzioneMinima = i;
			int azioneMinima = azioniOrdinatePerProbabilita.get(i);
			for (int j = i + 1; j < azioniOrdinatePerProbabilita.size(); j++)
			{
				if (azioneMinima > azioniOrdinatePerProbabilita.get(j) )
					{
					azioneMinima = azioniOrdinatePerProbabilita.get(j);
					posizioneAzioneMinima = j;
					}
			}
			intervalliPosizioneOrdinatiPerAzione.add(intervalliPosizione.get(posizioneAzioneMinima) );
			azioniOrdinatePerProbabilita.remove(posizioneAzioneMinima);
			intervalliPosizione.remove(posizioneAzioneMinima);
			i = -1
					;
		}
		
		return intervalliPosizioneOrdinatiPerAzione;
	}
	
	public void ToString(Vector<Tupla> elencoProbabilita, int metodo) 
	{
		System.out.println("ELENCO PROBABILITA' METODO " + metodo + " ORDINATO");
		for(int i=0; i<elencoProbabilita.size(); i++) {
			System.out.print("Azioni: ");
			for (int j = 0; j < elencoProbabilita.get(i).getListaAzioni().size(); j++)
			{
				System.out.print(elencoProbabilita.get(i).getListaAzioni().get(j) + " ");
			}
		
			System.out.println("| Probabilita': " + elencoProbabilita.get(i).getProbabilita() );
		}
	}
	
}