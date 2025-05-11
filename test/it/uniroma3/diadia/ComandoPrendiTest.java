package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

    private ComandoPrendi comandoPrendi;
    private Partita partita;
    private IOConsole io;
    private Attrezzo attrezzo;

    @BeforeEach
    void setUp() {
        comandoPrendi = new ComandoPrendi();
        io = new IOConsole();
        comandoPrendi.setIo(io);

        partita = new Partita();
        Stanza stanza = new Stanza("Atrio");
        partita.setStanzaCorrente(stanza);

        attrezzo = new Attrezzo("lanterna", 1);
        stanza.addAttrezzo(attrezzo);
    }

    @Test
    void testPrendiAttrezzoPresente() {
        comandoPrendi.setParametro("lanterna");
        comandoPrendi.esegui(partita);

        assertNull(partita.getStanzaCorrente().getAttrezzo("lanterna"),
            "L'attrezzo dovrebbe essere stato rimosso dalla stanza.");
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"),
            "L'attrezzo dovrebbe essere stato aggiunto alla borsa.");
    }

    @Test
    void testPrendiAttrezzoNonPresente() {
        comandoPrendi.setParametro("chiave");
        comandoPrendi.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"),
            "L'attrezzo non dovrebbe essere stato aggiunto alla borsa perché non è presente nella stanza.");
    }

    @Test
    void testPrendiAttrezzoSenzaParametro() {
        comandoPrendi.setParametro(null);
        comandoPrendi.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"),
            "Senza parametro, l'attrezzo non dovrebbe essere stato preso.");
    }

    @Test
    void testPrendiAttrezzoBorsaTroppoPiena() {
        // Riempi la borsa con 10 attrezzi da 1
        for (int i = 0; i < 10; i++) {
            partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("a" + i, 1));
        }

        comandoPrendi.setParametro("lanterna");
        comandoPrendi.esegui(partita);

        assertNotNull(partita.getStanzaCorrente().getAttrezzo("lanterna"),
            "L'attrezzo dovrebbe rimanere nella stanza se la borsa è piena.");
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"),
            "L'attrezzo non dovrebbe essere stato aggiunto alla borsa se è piena.");
    }

    @Test
    void testPrendiAttrezzoTroppoPesante() {
        // Borsa quasi piena di peso (9), aggiungiamo uno da 2 (limite 10)
        partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("peso9", 9));
        Stanza stanza = partita.getStanzaCorrente();
        stanza.removeAttrezzo("lanterna");
        Attrezzo pesante = new Attrezzo("incudine", 2);
        stanza.addAttrezzo(pesante);

        comandoPrendi.setParametro("incudine");
        comandoPrendi.esegui(partita);

        assertTrue(stanza.hasAttrezzo("incudine"),
            "L'attrezzo troppo pesante dovrebbe restare nella stanza.");
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("incudine"),
            "L'attrezzo troppo pesante non dovrebbe essere nella borsa.");
    }
}
