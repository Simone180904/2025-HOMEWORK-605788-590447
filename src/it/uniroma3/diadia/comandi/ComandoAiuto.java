package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

    static final public String[] ELENCO_COMANDI = {
        "vai: ti permette di muoverti in una direzione (es. 'vai nord')",
        "aiuto: mostra questo messaggio di aiuto",
        "fine: termina il gioco",
        "prendi: prendi un attrezzo dalla stanza (es. 'prendi spada')",
        "posa: posa un attrezzo nella stanza (es. 'posa spada')",
        "guarda: guarda la stanza per visualizzare gli oggetti e la descrizione"
    };
    private IO io;
    private final static String NOME = "aiuto";

    @Override
    public void esegui(Partita partita) {
        for (String comando : ELENCO_COMANDI) {
            io.mostraMessaggio(comando);
        }
        io.mostraMessaggio("");  // Righe vuote per migliorare la leggibilità
    }

    @Override
    public void setParametro(String parametro) {
        // Non è necessario in questo caso, poiché il comando "aiuto" non ha parametri
    }

    @Override
    public String getParametro() {
        // Anche qui, nessun parametro da restituire
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
