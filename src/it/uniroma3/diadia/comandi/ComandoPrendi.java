package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
    private String nomeAttrezzo;
    private IO io;  // Variabile per gestire IO, se necessario.

    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Attrezzo attrezzo = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);

        if (attrezzo == null) {
            // Se l'attrezzo non è nella stanza
            io.mostraMessaggio("Attrezzo inesistente nella stanza.");
            return;
        }

        boolean preso = partita.getGiocatore().addAttrezzoInBorsa(attrezzo);
        if (preso) {
            stanzaCorrente.removeAttrezzo(attrezzo.getNome());
            io.mostraMessaggio("Hai preso: " + attrezzo.getNome());
        } else {
            io.mostraMessaggio("Borsa piena o troppo pesante. Non puoi prendere: " + attrezzo.getNome());
        }
    }

    @Override
    public void setParametro(String parametro) {
        // Imposta il nome dell'attrezzo da prendere
        this.nomeAttrezzo = parametro;
    }

    @Override
    public String getParametro() {
        // Restituisce il nome dell'attrezzo
        return this.nomeAttrezzo;
    }

    @Override
    public void setIo(IO io) {
        // Assegna l'oggetto IO per la gestione dell'input/output
        this.io = io;
    }

    @Override
    public String getNome() {
        // Restituisce il nome del comando
        return "prendi";
    }
}
