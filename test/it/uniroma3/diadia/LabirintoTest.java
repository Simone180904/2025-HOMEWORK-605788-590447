package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;

public class LabirintoTest {

    private Labirinto labirinto;

    @BeforeEach
    public void setUp() {
        labirinto = new Labirinto();
    }

    // Test Funzione: Verifica che la stanza iniziale sia corretta
    @Test
    public void testStanzaInizialeVerificata() {
        assertNotNull(labirinto.getStanzaIniziale());
        assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
    }
    
    @Test
    public void testStanzaFinaleNonNull() {
    	assertNotNull(labirinto.getStanzaFinale(), "La stanza non dovrebbe essere nulla");
    }

    // Test Funzione: Verifica che la stanza finale sia corretta
    @Test
    public void testStanzaFinale() {
        assertNotNull(labirinto.getStanzaFinale());
        assertEquals("Biblioteca", labirinto.getStanzaFinale().getNome());
    }
    @Test
    public void testStanzaInizialeNonNull() {
        // Verifica che la stanza iniziale sia nulla
    	assertNotNull(labirinto.getStanzaIniziale(), "La stanza iniziale non dovrebbe essere nulla");
    	}

    // Test Funzione: Verifica che le stanze siano collegate correttamente
    @Test
    public void testCollegamentiStanze() {
        Stanza atrio = labirinto.getStanzaIniziale();
        Stanza biblioteca = labirinto.getStanzaFinale();
        assertNotNull(atrio.getStanzaAdiacente("nord"));
        assertEquals(biblioteca, atrio.getStanzaAdiacente("nord"));
    }

    @Test
    public void testCollegamentiStanzeAlternativi() {
        Stanza atrio = labirinto.getStanzaIniziale();
        Stanza laboratorio = new Stanza("Laboratorio");
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        assertNotNull(atrio.getStanzaAdiacente("ovest"));
        assertEquals(laboratorio, atrio.getStanzaAdiacente("ovest"));
    }
    


    // Test Funzione: Verifica che la stanza finale sia collegata correttamente
    @Test
    public void testCollegamentoStanzaFinale() {
        Stanza biblioteca = labirinto.getStanzaFinale();
        Stanza atrio = labirinto.getStanzaIniziale();
        assertNotNull(biblioteca.getStanzaAdiacente("sud"));
        assertEquals(atrio, biblioteca.getStanzaAdiacente("sud"));
    }

    @Test
    public void testCollegamentoStanzaFinaleAlternativo() {
        Stanza aulaN11 = new Stanza("Aula N11");
        Stanza aulaN10 = new Stanza("Aula N10");
        aulaN10.impostaStanzaAdiacente("ovest", aulaN11);
        aulaN11.impostaStanzaAdiacente("est", aulaN10);
        assertNotNull(aulaN10.getStanzaAdiacente("ovest"));
        assertEquals(aulaN11, aulaN10.getStanzaAdiacente("ovest"));
    }

    // Test Funzione: Verifica che le stanze siano collegate anche in altre direzioni
    @Test
    public void testCollegamentiAltreDirezioni() {
        Stanza atrio = labirinto.getStanzaIniziale();
        Stanza aulaN11 = new Stanza("Aula N11");
        atrio.impostaStanzaAdiacente("est", aulaN11);
        assertNotNull(atrio.getStanzaAdiacente("est"));
        assertEquals(aulaN11, atrio.getStanzaAdiacente("est"));
    }

    @Test
    public void testCollegamentiAltreDirezioniAlternativo() {
        Stanza atrio = labirinto.getStanzaIniziale();
        Stanza laboratorio = new Stanza("Laboratorio");
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        laboratorio.impostaStanzaAdiacente("est", atrio);
        assertNotNull(laboratorio.getStanzaAdiacente("est"));
        assertEquals(atrio, laboratorio.getStanzaAdiacente("est"));
    }
    @Test
    public void testCollegamentiNonNulli() {
        // Ottieni una stanza dal labirinto
        Stanza atrio = labirinto.getStanzaIniziale();
        
        // Verifica che non ci sia un collegamento "nord" tra Atrio e una stanza che non esiste
        assertNotNull(atrio.getStanzaAdiacente("nord"), "La stanza non dovrebbe avere un collegamento verso nord");
        
        // Verifica che non ci sia un collegamento "sud" tra Atrio e una stanza che non esiste
        assertNotNull(atrio.getStanzaAdiacente("sud"), "La stanza non dovrebbe avere un collegamento verso sud");
    }
    @Test
    public void testCollegamentiNulli() {
    	Stanza atrio=labirinto.getStanzaIniziale();
    	atrio.impostaStanzaAdiacente("nord",null);
    	assertNull(atrio.getStanzaAdiacente("nord"));
    }
}

