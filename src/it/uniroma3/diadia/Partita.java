package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @see Stanza
 * @version base
 */
public class Partita {

    static final private int CFU_INIZIALI = 20;

    private Labirinto labirinto;
    private Stanza stanzaCorrente;
    private boolean finita;
    private Giocatore giocatore;  // Riferimento a Giocatore

    public Partita() {
        labirinto = new Labirinto();  // Crea un nuovo labirinto
        this.stanzaCorrente = labirinto.getStanzaIniziale();  // Imposta la stanza corrente come la stanza iniziale
        this.finita = false;
        this.giocatore = new Giocatore();  // Inizializza il giocatore
    }

    public Stanza getStanzaCorrente() {
        return this.stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public boolean vinta() {
        // La partita è vinta quando la stanza corrente è quella finale (biblioteca)
        return this.stanzaCorrente == labirinto.getStanzaFinale();
    }

    public boolean isFinita() {
        return finita || vinta() || (giocatore.getCfu() == 0);  // Verifica se la partita è finita usando i CFU del Giocatore
    }

    public void setFinita() {
        this.finita = true;
    }

    public int getCfu() {
        return this.giocatore.getCfu();  // Delegato al Giocatore
    }

    public void setCfu(int cfu) {
        this.giocatore.setCfu(cfu);  // Delegato al Giocatore
    }

    public Stanza getStanzaVincente() {
        return labirinto.getStanzaFinale();  // Aggiunto il metodo per ottenere la stanza finale
    }

    public Giocatore getGiocatore() {
        return this.giocatore;  // Restituisce l'istanza del Giocatore
    }

    public boolean giocatoreIsVivo() {
        return this.giocatore.getCfu() > 0;
    }

}
