
package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {

	private int contatoreAttrezziPosati;

	private int sogliaMagica;

	private static final int SOGLIA_MAGICA_DEFAULT = 2;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this. contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}


	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		
		if (this.contatoreAttrezziPosati > this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		return false;
	}


}
