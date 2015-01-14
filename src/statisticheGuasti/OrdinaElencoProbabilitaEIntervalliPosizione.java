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
	/**
	 * Elenco probabilita ordinato senza doppioni.
	 *
	 * @return the vector
	 */
	public static Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni(Vector<Float> probabilitaTestSuite)
	{
		return RimuoviDoppioniElencoProbabilitaOrdinato(OrdinaElencoProbabilita(ElencoProbabilita(probabilitaTestSuite) ) );
	}
	
	/**
	 * Elenco probabilita.
	 *
	 * @return the vector
	 */
	private static Vector<Tupla> ElencoProbabilita (Vector<Float> probabilitaTestSuite)
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
	private static Vector<Tupla> OrdinaElencoProbabilita(Vector<Tupla> elencoProbabilita)
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
	private static Vector<Tupla> RimuoviDoppioniElencoProbabilitaOrdinato(Vector<Tupla> elencoProbabilita)
	{
		//Rimuovo i doppioni tenendo traccia di quali erano
		Vector<Integer> tupleDaEliminare = new Vector<Integer>();
			for (int j = 0; j < elencoProbabilita.size() - 1; j++)
			{
				if (elencoProbabilita.get(j).getProbabilita() == elencoProbabilita.get(j+1).getProbabilita() )
				{
					elencoProbabilita.get(j).getListaAzioni().add(elencoProbabilita.get(j+1).getListaAzioni().get(0) );
					tupleDaEliminare.add(j+1);
				}
			}
			
			for ( int i = tupleDaEliminare.size() - 1; i >= 0; i--)
			{
				System.out.println(tupleDaEliminare.get(i) );
				int daEliminare = tupleDaEliminare.get(i);
				elencoProbabilita.remove(daEliminare);
			}
			System.out.println("asdasdaads");			
			
		return elencoProbabilita;
	}
	
	
	/**
	 * Intervalli posizione.
	 *
	 * @return the vector
	 */
	public static Vector<int[]> IntervalliiPosizione(Vector<Float> probabilitaTestSuite)
	{
		Vector<int[]> intervalliPosizione = new Vector<int[]>();
		Vector<Integer> azioniOrdinatePerProbabilita = new Vector<Integer>();
		
		Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni = elencoProbabilitaOrdinatoSenzaDoppioni(probabilitaTestSuite);
		
		int posizione = 1;
		for (int i = 0; i < elencoProbabilitaOrdinatoSenzaDoppioni.size(); i++)
		{
			int[] posizioni = new int[2];
			posizione += i;
			posizioni[0]= posizione;
			posizione += elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size() - 1;
			posizioni[1]= posizione;
			for (int j = 0; j < elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size(); j++)
				{
				intervalliPosizione.add(posizioni);
				azioniOrdinatePerProbabilita.add(elencoProbabilitaOrdinatoSenzaDoppioni.get(j).getListaAzioni().get(j) );
				}
		}
		
		Vector<int[]> intervalliPosizioneOrdinatiPerAzione = new Vector<int[]>();
		for (int i = 0; i < azioniOrdinatePerProbabilita.size(); i++)
		{
			int azioneMinima = azioniOrdinatePerProbabilita.get(i);
			for (int j = i + 1; j < azioniOrdinatePerProbabilita.size(); j++)
			{
				if (azioneMinima > azioniOrdinatePerProbabilita.get(j) )
					azioneMinima = azioniOrdinatePerProbabilita.get(j);
			}
			intervalliPosizioneOrdinatiPerAzione.add(intervalliPosizione.get(azioneMinima) );
		}
		
		return intervalliPosizioneOrdinatiPerAzione;
	}
	
	
}
