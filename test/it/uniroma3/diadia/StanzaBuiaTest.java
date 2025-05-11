package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

    private StanzaBuia stanzaBuia;
    private Attrezzo lanterna;

    @BeforeEach
    void setUp() {
        stanzaBuia = new StanzaBuia("cantina", "lanterna");
        lanterna = new Attrezzo("lanterna", 1);
    }

    @Test
    void testDescrizioneSenzaAttrezzoLucente() {
        // Se non c'è la lanterna, la stanza è buia
        assertEquals("Qui c'è un buio pesto. Non vedi nulla.", stanzaBuia.getDescrizione());
    }

    @Test
    void testDescrizioneConAttrezzoLucente() {
        stanzaBuia.addAttrezzo(lanterna);
        // Con la lanterna la stanza dovrebbe mostrare la descrizione normale
        assertTrue(stanzaBuia.getDescrizione().contains("cantina"));
    }

    @Test
    void testDescrizioneConAttrezzoDiverso() {
        stanzaBuia.addAttrezzo(new Attrezzo("candela", 1));
        // La candela non è quella che illumina la stanza, quindi è ancora buia
        assertEquals("Qui c'è un buio pesto. Non vedi nulla.", stanzaBuia.getDescrizione());
    }

    @Test
    void testAddAttrezzoLucente() {
        assertTrue(stanzaBuia.addAttrezzo(lanterna));
        assertTrue(stanzaBuia.hasAttrezzo("lanterna"));
    }

    @Test
    void testNomeStanza() {
        assertEquals("cantina", stanzaBuia.getNome());
    }
}
