package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

    private IO io;
    private final static String NOME = "fine";
    public final static String MESSAGGIO_FINE = "Grazie di aver giocato! La tua avventura è finita.";

    @Override
    public void esegui(Partita partita) {
        partita.setFinita();  // Termina la partita
        io.mostraMessaggio(MESSAGGIO_FINE);  // Messaggio di fine gioco
        io.mostraMessaggio("Premi invio per uscire.");
    }

    @Override
    public void setParametro(String parametro) {
        // Il comando "fine" non richiede un parametro, quindi non facciamo nulla
    }

    @Override
    public String getParametro() {
        // Nessun parametro da restituire
        return null;
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
