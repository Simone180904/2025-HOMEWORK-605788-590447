package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai implements Comando {

    private String direzione;
    private IO io;
    private static final String NOME = "vai";

    @Override
    public void esegui(Partita partita) {
        if (this.direzione == null || this.direzione.isEmpty()) {
            this.io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione valida (es. nord, sud, est, ovest).");
            return;
        }

        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);

        if (prossimaStanza == null) {
            this.io.mostraMessaggio("Direzione '" + this.direzione + "' inesistente. Non puoi andare in quella direzione.");
            return;
        }

        // Verifica se il giocatore ha ancora CFU per muoversi
        Giocatore giocatore = partita.getGiocatore();
        if (giocatore.getCfu() <= 0) {
            this.io.mostraMessaggio("Non hai più CFU. Non puoi muoverti.");
            return;
        }

        // Sposta il giocatore nella prossima stanza
        partita.setStanzaCorrente(prossimaStanza);
        this.io.mostraMessaggio("Ti sei spostato in: " + prossimaStanza.getNome());

        // Decrementa i CFU del giocatore
        giocatore.decrementaCfu(1);
        this.io.mostraMessaggio("CFU rimasti: " + giocatore.getCfu());
    }

    @Override
    public void setParametro(String parametro) {
        this.direzione = parametro;
    }

    @Override
    public String getParametro() {
        return this.direzione;
    }

    @Override
    public void setIo(IO io) {
        this.io = io;
    }

    @Override
    public String getNome() {
        return NOME;
    }
}
