package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPosa implements Comando {
    private String nomeAttrezzo;
    private IO io;

    @Override
    public void esegui(Partita partita) {
        if (this.nomeAttrezzo == null || this.nomeAttrezzo.isEmpty()) {
            io.mostraMessaggio("Devi specificare il nome dell'attrezzo da posare.");
            return;
        }

        // Cerca l'attrezzo nella borsa del giocatore
        Attrezzo attrezzo = partita.getGiocatore().removeAttrezzo(this.nomeAttrezzo);

        if (attrezzo == null) {
            // Se l'attrezzo non è nella borsa
            io.mostraMessaggio("Non hai questo attrezzo nella borsa.");
            return;
        }

        // Aggiungi l'attrezzo alla stanza corrente
        boolean posato = partita.getStanzaCorrente().addAttrezzo(attrezzo);
        if (posato) {
            io.mostraMessaggio("Hai posato: " + attrezzo.getNome());
        } else {
            io.mostraMessaggio("Impossibile posare l'attrezzo nella stanza: la stanza è piena.");
            // Potresti aggiungere altre logiche di gestione errori (es. limite di attrezzi)
        }
    }

    @Override
    public void setParametro(String parametro) {
        // Imposta il nome dell'attrezzo da posare
        this.nomeAttrezzo = parametro;
    }

    @Override
    public String getParametro() {
        // Ritorna il nome dell'attrezzo
        return this.nomeAttrezzo;
    }

    @Override
    public void setIo(IO io) {
        // Assegna l'oggetto IO per la gestione dell'input/output
        this.io = io;
    }

    @Override
    public String getNome() {
        // Ritorna il nome del comando
        return "posa";
    }
}
