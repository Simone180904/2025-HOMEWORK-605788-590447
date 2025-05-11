package it.uniroma3.diadia;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

    private StanzaBloccata stanzaBloccata;
    private Stanza stanzaLibera;
    private Attrezzo chiave;

    @BeforeEach
    void setUp() {
        // Crea una stanza bloccata verso "nord" senza la "chiave"
        stanzaBloccata = new StanzaBloccata("portaChiusa", "nord", "chiave");
        stanzaLibera = new Stanza("giardino");
        chiave = new Attrezzo("chiave", 1);

        // Imposta la stanza adiacente
        stanzaBloccata.impostaStanzaAdiacente("nord", stanzaLibera);
    }

    @Test
    void testGetStanzaAdiacenteBloccata() {
        // Senza la chiave, non si può accedere alla stanza a nord
        assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    void testGetStanzaAdiacenteSbloccata() {
        // Con la chiave, si accede alla stanza adiacente
        stanzaBloccata.addAttrezzo(chiave);
        assertEquals(stanzaLibera, stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    void testGetStanzaAdiacenteAltraDirezione() {
        // Una direzione diversa da quella bloccata deve funzionare normalmente
        stanzaBloccata.impostaStanzaAdiacente("est", stanzaLibera);
        assertEquals(stanzaLibera, stanzaBloccata.getStanzaAdiacente("est"));
    }

    @Test
    void testGetDescrizioneBloccata() {
        // Senza chiave, deve restituire messaggio di blocco
        String descrizione = stanzaBloccata.getDescrizione();
        assertTrue(descrizione.contains("Stanza bloccata nella direzione"));
        assertTrue(descrizione.contains("chiave"));
    }

    @Test
    void testGetDescrizioneSbloccata() {
        stanzaBloccata.addAttrezzo(chiave);
        String descrizione = stanzaBloccata.getDescrizione();
        assertFalse(descrizione.contains("Stanza bloccata nella direzione"));
        assertTrue(descrizione.contains("portaChiusa"));  // Nome della stanza
    }
}

