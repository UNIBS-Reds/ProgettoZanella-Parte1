/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.GUI;
import utilita.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class Ciclo.
 */
public class Ciclo implements Entita{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_CICLO. */
	public final static String MSG_CICLO = "< INIZIO CICLO %s (ID = %d) - MERGE";
	
	/** The Constant MSG_ATTIVITA_INIZIALI. */
	public final static String MSG_ATTIVITA_INIZIALI = "%s - ENTITA' RAMO N.1 (ATTIVITA' INIZIALI)\n";
	
	/** The Constant MSG_COND_PERMANENZA_CICLO. */
	public final static String MSG_COND_PERMANENZA_CICLO = "%s - ENTITA RAMO N.2 (CONDIZIONE DI PERMANENZA NEL CICLO)\n";
	
	/** The Constant MSG_BRANCH_CICLO. */
	public final static String MSG_BRANCH_CICLO = "FINE CICLO %s (ID = %d) - BRANCH >\n";
	
	/** The Constant NUM_RAMI_CICLO. */
	public final static int NUM_RAMI_CICLO = 2;
	
	/** The id. */
	private int id;
	
	/** The titolo. */
	private String titolo;
	
	/** The num rami. */
	private int numRami;
	
	/** The elenco rami. */
	private Ramo [] elencoRami;
	
	/** The elenco entita. */
	private Vector<Entita> elencoEntita;
	
	/** The valore indentazione. */
	private int valoreIndentazione;
	
	/** The id tipo. */
	private String idTipo;
	
	/**
	 * Instantiates a new ciclo.
	 *
	 * @param _titolo the _titolo
	 */
	public Ciclo(String _titolo)
	{
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		titolo = _titolo;
		numRami = NUM_RAMI_CICLO;
		elencoRami = new Ramo [NUM_RAMI_CICLO]; 
		elencoEntita = new Vector<Entita>();
		idTipo = ID_TIPO_CICLO;
		valoreIndentazione = GestoreModello.getRientro();
	}
	
	/**
	 * Adds the entita.
	 *
	 * @param e the e
	 */
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#addEntita(gestioneModello.Entita, int)
	 */
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getEntita()
	 */
	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			for(int j=0; j<entitaRamo.size(); j++) {
				addEntita(entitaRamo.elementAt(j));
			}
		}
		return elencoEntita;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getId()
	 */
	public int getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIndentazione()
	 */
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getNome()
	 */
	public String getNome() {
		return titolo;
	}
	
	/**
	 * Gets the numero rami.
	 *
	 * @return the numero rami
	 */
	public int getNumeroRami() {
		return numRami;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getRami()
	 */
	public Ramo[] getRami() {
		return elencoRami;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#giaPresente(java.lang.String)
	 */
	public boolean giaPresente(String nome) {
		if(titolo.equalsIgnoreCase(nome))
			return true;
		boolean trovata = false;
		int i=0;
		while(trovata == false && i<NUM_RAMI_CICLO) {
			int j=0;
			while(trovata == false && j<getRami()[i].getNumeroEntita()) {
				Entita e = getRami()[i].getEntitaAt(j);
				if(e.getNome().equalsIgnoreCase(nome))
					return true;
				else {
					trovata = e.giaPresente(nome);
					j++;
				}
			}
			i++;
		}
		return trovata;	
	}
  
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#rimuoviEntitaAt(int)
	 */
	public void rimuoviEntitaAt(int id) {
		//Per ogni ramo metto le entita' in un vector. Se una di quelle soddisfa la condizione, la tolgo dal ramo
		for (int i=0; i<numRami; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			//Ricerca l'entita' da eliminare tra le entita' interne del ramo i-esimo
			for(int j=0; j<entitaRamo.size(); j++) {
				Entita e = entitaRamo.elementAt(j);
				//Se la trova la elimina dalle entita' del ramo i-esimo di this e restituisce true
				if(e.getId()==id)
				{
					if(Util.yesOrNo(String.format(MSG_CONFERMA_CANCELLAZIONE,e.getNome()))) {
						elencoRami[i].eliminaEntitaRamo(j);
						Modello.getInstance().decrementaContatore();
						if(e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE))
							Modello.getInstance().rimuoviAzione(e.getNome());
						System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId()));
					}
				}
				else 
					e.rimuoviEntitaAt(id);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("\n");
        risultato.append(GUI.indenta(String.format(MSG_CICLO, titolo.toUpperCase(), id),SPAZIO,valoreIndentazione-GestoreModello.FATTORE_INCREMENTO));
		risultato.append("\n");
		if(elencoRami[0].getEntitaRamo().isEmpty())
		{
			risultato.append(GUI.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		}
		else
		{
			risultato.append(GUI.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			for(int j=0; j<elencoRami[0].getEntitaRamo().size(); j++) {
				risultato.append(elencoRami[0].toString());
			}
		}
		risultato.append(GUI.indenta(String.format(MSG_COND_PERMANENZA_CICLO,titolo), SPAZIO, valoreIndentazione));
		if(elencoRami[1].getEntitaRamo().isEmpty())
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		else
			risultato.append(elencoRami[1].toString());
		if(valoreIndentazione >= GestoreModello.FATTORE_INCREMENTO)
			risultato.append(GUI.indenta(String.format(MSG_BRANCH_CICLO, titolo.toUpperCase(),id),SPAZIO,valoreIndentazione - GestoreModello.FATTORE_INCREMENTO));
		else
			risultato.append(String.format(MSG_BRANCH_CICLO, titolo.toUpperCase(),id));
		return risultato.toString();
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIdTipo()
	 */
	public String getIdTipo() {
		return idTipo;
	}
}