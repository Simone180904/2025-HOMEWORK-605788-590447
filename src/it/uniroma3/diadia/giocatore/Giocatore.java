package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = 20;
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public void decrementaCfu(int valore) {
		this.cfu -= valore;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public boolean addAttrezzoInBorsa(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.borsa.hasAttrezzo(nomeAttrezzo);
	}
	
    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
	    return this.borsa.removeAttrezzo(nomeAttrezzo);
	 }
}
