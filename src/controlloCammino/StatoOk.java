package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public class StatoOk implements StatoCammino {

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
	}

	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return STATO_OK;
	}	
}