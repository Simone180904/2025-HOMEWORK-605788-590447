package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai aiuto fine prendi posa"};

	private Partita partita;
	private IOConsole ioConsole; //Aggiungi un'istanza di IOConsole

	public DiaDia(IOConsole ioConsole) {
		this.partita = new Partita();
		this.ioConsole = ioConsole;  //Istanza di IOConsole
	}

	public void gioca() {
		String istruzione; 
		//Scanner scannerDiLinee;  // Non serve più
		ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);  // Usa IOConsole per stampare il messaggio
		do		
			istruzione = ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
			else if (comandoDaEseguire.getNome().equals("prendi"))
				this.prendi(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("posa"))
				this.posa(comandoDaEseguire.getParametro());
        else
			ioConsole.mostraMessaggio("Comando sconosciuto"); //Usa IOConsole per stampare
		
		if (this.partita.vinta()) {
			ioConsole.mostraMessaggio("Hai vinto!"); //Usa IOConsole per stampare
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			ioConsole.mostraMessaggio(elencoComandi[i] + " ");  // Usa IOConsole per stampare
        ioConsole.mostraMessaggio("");  // Aggiungi un salto di linea alla fine
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioConsole.mostraMessaggio("Dove vuoi andare?");  //Usa IOConsole per stampare
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu--);
		}
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	   /**
     * Prendi un attrezzo dalla stanza e mettilo nella borsa
     */
    private void prendi(String nomeAttrezzo) {
        Stanza stanzaCorrente = this.partita.getStanzaCorrente();
        Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
        if (attrezzo != null) {
            if (this.partita.getGiocatore().addAttrezzoInBorsa(attrezzo)) {
                stanzaCorrente.removeAttrezzo(attrezzo);
                ioConsole.mostraMessaggio("Hai preso " + attrezzo);
            } else {
                ioConsole.mostraMessaggio("La tua borsa è piena!");
            }
        } else {
        	ioConsole.mostraMessaggio("Non c'è un attrezzo chiamato " + nomeAttrezzo + " qui.");
        }
    }
    /**
     * Posare un attrezzo dalla borsa nella stanza corrente
     */
    private void posa(String nomeAttrezzo) {
        // Prova a rimuovere l'attrezzo dalla borsa del giocatore
        Attrezzo attrezzo = this.partita.getGiocatore().removeAttrezzo(nomeAttrezzo);

        // Se l'attrezzo è stato trovato nella borsa e rimosso
        if (attrezzo != null) {
            // Aggiungi l'attrezzo alla stanza corrente
            if (this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
            	ioConsole.mostraMessaggio("Hai posato " + attrezzo.getNome() + " nella stanza.");
            } else {
            	ioConsole.mostraMessaggio("Non posso posare l'attrezzo nella stanza, spazio insufficiente!");
            }
        } else {
            // Se l'attrezzo non è nella borsa, mostra un messaggio di errore
        	ioConsole.mostraMessaggio("Non hai un attrezzo chiamato " + nomeAttrezzo + " nella tua borsa.");
        }
    }

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		// Creazione dell'istanza di IOConsole
	    IOConsole ioConsole = new IOConsole();
	    
	    // Creazione del gioco e passaggio di ioConsole a tutte le classi che lo richiedono
	    DiaDia gioco = new DiaDia(ioConsole);
	    gioco.gioca();
	}
}

