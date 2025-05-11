package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {

    private IO io;
    private final static String NOME = "non valido";

    @Override
    public void esegui(Partita partita) {
        // Mostra un messaggio di errore quando il comando non è valido
        io.mostraMessaggio("Comando non riconosciuto. Per favore, prova con uno dei comandi validi.");
    }

    @Override
    public void setParametro(String parametro) {
        // Questo comando non ha parametri, quindi non facciamo nulla
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
