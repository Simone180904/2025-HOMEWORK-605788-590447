package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

    private IO io;
    private final static String NOME = "guarda";  // Nome del comando

    @Override
    public void esegui(Partita partita) {
        // Mostra la stanza corrente
        io.mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().getNome());
        
        // Descrizione della stanza
        io.mostraMessaggio("Descrizione: " + partita.getStanzaCorrente().getDescrizione());

        // Non stampiamo più gli oggetti nella stanza, visto che la logica è già gestita nella Stanza

        // Contenuto della borsa
        io.mostraMessaggio("Contenuto della borsa: " + partita.getGiocatore().getBorsa());

        // CFU rimanenti
        io.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
    }

    @Override
    public void setParametro(String parametro) {
        // Questo comando non richiede un parametro, quindi non facciamo nulla
    }

    @Override
    public String getParametro() {
        return null;  // Nessun parametro da restituire
    }

    @Override
    public void setIo(IO io) {
        this.io = io;  // Imposta l'oggetto IO
    }

    @Override
    public String getNome() {
        return NOME;  // Restituisce il nome del comando
    }
}
