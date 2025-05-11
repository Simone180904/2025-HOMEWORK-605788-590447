package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {

    /**
     * Esegue il comando, con l'oggetto partita come parametro.
     * @param partita L'oggetto partita su cui operare.
     */
    public void esegui(Partita partita);

    /**
     * Imposta il parametro del comando.
     * @param parametro Il parametro (ad esempio la direzione o l'attrezzo) del comando.
     */
    void setParametro(String parametro);

    /**
     * Ottiene il parametro del comando.
     * @return Il parametro (ad esempio la direzione o l'attrezzo) del comando.
     */
    String getParametro();

    /**
     * Imposta l'oggetto IO per gestire input/output.
     * @param io L'oggetto IO per l'input/output.
     */
    public void setIo(IO io);

    /**
     * Ottiene il nome del comando.
     * @return Il nome del comando.
     */
    public String getNome();
}
