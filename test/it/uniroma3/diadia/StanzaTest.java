package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
    
    private Stanza atrio;       // Variabile per la stanza atrio
    private Stanza biblioteca;  // Variabile per la stanza biblioteca

    // Questo metodo verrà eseguito prima di ogni test per preparare l'ambiente di test
    @BeforeEach
    void setUp() {
        // Crea le stanze atrio e biblioteca
        biblioteca = new Stanza("Biblioteca");
        atrio = new Stanza("Atrio");
    }

    // ========================
    // Test per la gestione delle stanze adiacenti
    // ========================
    
    // Test 1: Impostazione corretta di una stanza adiacente
    @Test
    void testImpostaStanzaAdiacente() {
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        assertEquals(biblioteca, atrio.getStanzaAdiacente("nord"));
    }
    
    // Test 2: Direzione non impostata
    @Test
    void testDirezioneNonImpostata() {
        assertNull(atrio.getStanzaAdiacente("sud"));  // Direzione sud non impostata
    }

    // Test 3: Sostituzione della stanza adiacente
    @Test
    void testSostituzioneStanzaAdiacente() {
        Stanza corridoio = new Stanza("Corridoio");
        atrio.impostaStanzaAdiacente("nord", biblioteca);  // Prima impostazione
        atrio.impostaStanzaAdiacente("nord", corridoio);  // Sostituzione
        assertEquals(corridoio, atrio.getStanzaAdiacente("nord"));
    }

    // Test 4: Impostazione stanza adiacente con parametri nulli
    @Test
    void testImpostaStanzaAdiacenteNull() {
        atrio.impostaStanzaAdiacente("", null);
        assertNull(atrio.getStanzaAdiacente(""));
    }

    // ========================
    // Test per la gestione degli attrezzi
    // ========================
    
    // Test 1: Verifica l'assenza di un attrezzo che non esiste
    @Test
    void testAttrezzoNonEsistente() {
        assertNull(atrio.getAttrezzo("spada"));  // L'attrezzo non esiste, quindi deve restituire null
    }



    // Test 2: aggiungere un attrezzo
    @Test
    void aggiungiAttrezzo() {
    	Attrezzo pistola = new Attrezzo("pistola", 7);
    	biblioteca.addAttrezzo(pistola);
    	assertEquals(pistola,biblioteca.getAttrezzo("pistola"));
    }

    // ========================
    // Test per le uscite dalle stanze
    // ========================
    
    // Test 1: Verifica che l'uscita porti alla stanza corretta
    @Test
    void testUscitaCorretta() {
        Stanza corridoio = new Stanza("Corridoio");
        biblioteca.impostaStanzaAdiacente("nord", corridoio);
        assertEquals(corridoio, biblioteca.getStanzaAdiacente("nord"));
    }

    // Test 2: Verifica che un'uscita non impostata restituisca null
    @Test
    void testUscitaNonImpostata() {
        Stanza biblioteca = new Stanza("Biblioteca");
        assertNull(biblioteca.getStanzaAdiacente("sud"));
    }

    // Test 3: Sostituzione dell'uscita
    @Test
    void testSostituzioneUscita() {
        Stanza corridoio1 = new Stanza("Corridoio1");
        Stanza corridoio2 = new Stanza("Corridoio2");
        biblioteca.impostaStanzaAdiacente("nord", corridoio1);
        assertEquals(corridoio1, biblioteca.getStanzaAdiacente("nord"));
        biblioteca.impostaStanzaAdiacente("nord", corridoio2);
        assertEquals(corridoio2, biblioteca.getStanzaAdiacente("nord"));
    }
}
