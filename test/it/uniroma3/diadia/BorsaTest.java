package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {

    private Borsa borsa;
    private Attrezzo attrezzo1;
    private Attrezzo attrezzo2;
    private Attrezzo attrezzoPesante;

    @BeforeEach
    void setUp() {
        borsa = new Borsa();  // Inizializza la borsa
        attrezzo1 = new Attrezzo("chiave", 2);  // Crea un attrezzo leggero
        attrezzo2 = new Attrezzo("mappa", 3);  // Crea un altro attrezzo leggero
        attrezzoPesante = new Attrezzo("martello", 11);  // Crea un attrezzo pesante
    }

    @Test
    void testAddAttrezzo() {
        // Aggiungi un attrezzo e verifica che l'aggiunta sia riuscita
        assertTrue(borsa.addAttrezzo(attrezzo1), "Dovrebbe essere possibile aggiungere l'attrezzo.");
    }

    @Test
    void testAddAttrezzoMaxPeso() {
        // Prova ad aggiungere un attrezzo che superi il peso massimo
        borsa.addAttrezzo(attrezzo1);  // Aggiungi il primo attrezzo
        assertFalse(borsa.addAttrezzo(attrezzoPesante), "Non dovrebbe essere possibile aggiungere un attrezzo troppo pesante.");
    }

    @Test
    void testAddAttrezzoMaxNumero() {
        // Aggiungi 10 attrezzi e verifica che non se ne possano aggiungere altri
        for (int i = 0; i < 10; i++) {
            assertTrue(borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 1)), "Dovrebbe essere possibile aggiungere l'attrezzo " + i);
        }
        assertFalse(borsa.addAttrezzo(attrezzo1), "Non dovrebbe essere possibile aggiungere un undicesimo attrezzo.");
    }

    @Test
    void testGetAttrezzo() {
        borsa.addAttrezzo(attrezzo1);
        // Verifica che l'attrezzo possa essere recuperato
        assertEquals(attrezzo1, borsa.getAttrezzo("chiave"), "Dovrebbe essere possibile recuperare l'attrezzo.");
    }

    @Test
    void testGetAttrezzoNonEsistente() {
        borsa.addAttrezzo(attrezzo1);
        // Verifica che se l'attrezzo non esiste, ritorna null
        assertNull(borsa.getAttrezzo("sedia"), "Non dovrebbe essere possibile recuperare un attrezzo che non esiste.");
    }

    @Test
    void testGetPeso() {
        borsa.addAttrezzo(attrezzo1);
        borsa.addAttrezzo(attrezzo2);
        // Verifica che il peso della borsa sia corretto
        assertEquals(5, borsa.getPeso(), "Il peso della borsa dovrebbe essere 5.");
    }

    @Test
    void testIsEmpty() {
        // Verifica che la borsa sia vuota inizialmente
        assertTrue(borsa.isEmpty(), "La borsa dovrebbe essere vuota.");
        borsa.addAttrezzo(attrezzo1);
        // Verifica che la borsa non sia vuota dopo aver aggiunto un attrezzo
        assertFalse(borsa.isEmpty(), "La borsa non dovrebbe essere vuota dopo aver aggiunto un attrezzo.");
    }

    @Test
    void testRemoveAttrezzo() {
        borsa.addAttrezzo(attrezzo1);
        // Rimuovi l'attrezzo e verifica che venga rimosso correttamente
        assertEquals(attrezzo1, borsa.removeAttrezzo("chiave"), "L'attrezzo rimosso dovrebbe essere quello giusto.");
        assertNull(borsa.getAttrezzo("chiave"), "L'attrezzo dovrebbe essere stato rimosso dalla borsa.");
    }

    @Test
    void testRemoveAttrezzoNonEsistente() {
        borsa.addAttrezzo(attrezzo1);
        // Prova a rimuovere un attrezzo che non esiste
        assertNull(borsa.removeAttrezzo("sedia"), "Non dovrebbe essere possibile rimuovere un attrezzo che non esiste.");
    }

    @Test
    void testHasAttrezzo() {
        borsa.addAttrezzo(attrezzo1);
        // Verifica che la borsa contenga un attrezzo
        assertTrue(borsa.hasAttrezzo("chiave"), "La borsa dovrebbe contenere l'attrezzo 'chiave'.");
    }

    @Test
    void testHasAttrezzoNonPresente() {
        // Verifica che la borsa non contenga un attrezzo non presente
        assertFalse(borsa.hasAttrezzo("sedia"), "La borsa non dovrebbe contenere l'attrezzo 'sedia'.");
    }

    @Test
    void testToString() {
        borsa.addAttrezzo(attrezzo1);
        borsa.addAttrezzo(attrezzo2);
        // Verifica che la stringa restituita rappresenti correttamente il contenuto della borsa
        assertTrue(borsa.toString().contains("Contenuto borsa"), "La rappresentazione della borsa dovrebbe contenere 'Contenuto borsa'.");
    }
}
