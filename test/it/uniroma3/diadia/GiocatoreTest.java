package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {

    private Giocatore giocatore;
    private Attrezzo attrezzo1;

    @BeforeEach
    void setUp() {
        giocatore = new Giocatore();  // Crea un nuovo giocatore
        attrezzo1 = new Attrezzo("chiave", 2);  // Crea un attrezzo
    }

    @Test
    void testCfuIniziali() {
        // Verifica che i CFU iniziali del giocatore siano 20
        assertEquals(20, giocatore.getCfu(), "Il giocatore dovrebbe avere 20 CFU iniziali.");
    }

    @Test
    void testDecrementaCfu() {
        giocatore.decrementaCfu(5);
        // Verifica che i CFU vengano decrementati correttamente
        assertEquals(15, giocatore.getCfu(), "Dopo aver decrementato i CFU, dovrebbero essere 15.");
    }

    @Test
    void testGetBorsa() {
        // Verifica che la borsa del giocatore non sia null
        assertNotNull(giocatore.getBorsa(), "La borsa del giocatore non dovrebbe essere null.");
    }

    @Test
    void testAddAttrezzoInBorsa() {
        // Aggiungi un attrezzo nella borsa
        assertTrue(giocatore.addAttrezzoInBorsa(attrezzo1), "L'attrezzo dovrebbe essere aggiunto con successo.");
    }

    @Test
    void testHasAttrezzo() {
        giocatore.addAttrezzoInBorsa(attrezzo1);
        // Verifica che l'attrezzo sia presente nella borsa
        assertTrue(giocatore.hasAttrezzo("chiave"), "Il giocatore dovrebbe avere l'attrezzo 'chiave' nella borsa.");
    }

    @Test
    void testHasAttrezzoNonPresente() {
        // Verifica che l'attrezzo non sia presente nella borsa
        assertFalse(giocatore.hasAttrezzo("martello"), "Il giocatore non dovrebbe avere l'attrezzo 'martello' nella borsa.");
    }

    @Test
    void testRemoveAttrezzo() {
        giocatore.addAttrezzoInBorsa(attrezzo1);
        // Rimuovi un attrezzo dalla borsa e verifica che sia stato rimosso
        assertEquals(attrezzo1, giocatore.removeAttrezzo("chiave"), "L'attrezzo rimosso dovrebbe essere 'chiave'.");
        assertFalse(giocatore.hasAttrezzo("chiave"), "L'attrezzo 'chiave' dovrebbe essere rimosso dalla borsa.");
    }

    @Test
    void testRemoveAttrezzoNonEsistente() {
        // Verifica che non si possa rimuovere un attrezzo che non esiste
        assertNull(giocatore.removeAttrezzo("martello"), "Non dovrebbe essere possibile rimuovere un attrezzo che non esiste.");
    }
}
