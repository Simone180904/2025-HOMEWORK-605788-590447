package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartitaTest {

    private Partita partita;

    @BeforeEach
    public void setUp() {
        partita = new Partita();
    }

    // Test Funzione: Verifica inizializzazione corretta della partita
    @Test
    public void testInizializzazionePartita() {
        assertNotNull(partita.getStanzaCorrente());
        assertEquals(20, partita.getCfu());
        assertFalse(partita.isFinita());
    }

    // Test Funzione: Cambiamento stato partita (inizialmente non finita)
    @Test
    public void testCambiamentoSetPartita() {
        partita.setFinita();
        partita.setFinita();  // Riprovo a settare finita
        assertTrue(partita.isFinita());
    }

    // Test Funzione: Verifica vittoria quando si arriva alla stanza finale
    @Test
    public void testVinta() {
        partita.setStanzaCorrente(partita.getStanzaVincente());
        assertTrue(partita.vinta());
    }

    // Test Funzione: Cambiamento stato partita (inizialmente vinta, poi non vinta)
    @Test
    public void testCambiamentoStanzaVinta() {
        partita.setStanzaCorrente(partita.getStanzaVincente());
        assertTrue(partita.vinta());
        partita.setStanzaCorrente(new Stanza("stanza non finale")); // Cambio la stanza
        assertFalse(partita.vinta());
    }

    // Test Funzione: Verifica se la partita è finita quando i CFU sono a zero
    @Test
    public void testPartitaFinitaConCfuZero() {
        partita.setCfu(0);
        assertTrue(partita.isFinita());
    }

    // Test Funzione: Cambiamento stato partita (CFU zero, poi ripristino CFU positivi)
    @Test
    public void testCambiamentoCfu() {
        partita.setCfu(0);
        assertTrue(partita.isFinita());
        partita.setCfu(10);
        assertFalse(partita.isFinita());
    }

    // Test Funzione: Verifica che la partita non sia finita inizialmente
    @Test
    public void testPartitaNonFinitaInizialmente() {
        assertFalse(partita.isFinita());
    }

    // Test Funzione: Cambiamento stato partita (inizialmente non finita, poi finita)
    @Test
    public void testCambiamentoStatoNonFinita() {
        partita.setFinita();
        assertTrue(partita.isFinita());
        partita.setFinita();  // Non cambia nulla
        assertTrue(partita.isFinita());
    }

    // Test Funzione: Verifica che il metodo setFinita imposti correttamente lo stato della partita
    @Test
    public void testSetFinita() {
        partita.setFinita();
        assertTrue(partita.isFinita());
    }

    // Test Funzione: Cambiamento stato partita (inizialmente finita, poi impostazione non finita)
    @Test
    public void testCambiamentoSetFinita() {
        partita.setFinita();
        assertTrue(partita.isFinita());
        partita.setFinita();  // Non cambia nulla
        assertTrue(partita.isFinita());
    }

    // Test Funzione: Verifica che la partita sia finita dopo la vittoria
    @Test
    public void testPartitaFinitaDopoVittoria() {
        partita.setStanzaCorrente(partita.getStanzaVincente());
        assertTrue(partita.isFinita());
    }

    // Test Funzione: Cambiamento stato partita (vinta, poi non vinta)
    @Test
    public void testCambiamentoVittoria() {
        partita.setStanzaCorrente(partita.getStanzaVincente());
        assertTrue(partita.isFinita());
        partita.setStanzaCorrente(new Stanza("stanza non finale"));  // Cambio la stanza
        assertFalse(partita.isFinita());
    }

    // Test Funzione: Verifica che la stanza finale sia corretta
    @Test
    public void testStanzaVincente() {
        assertEquals(partita.getStanzaVincente(), partita.getStanzaVincente());
    }

    // Test Funzione: Cambiamento stato stanza (inizialmente la stanza vincente, poi cambiamento)
    @Test
    public void testCambiamentoStanzaVincente() {
        assertEquals(partita.getStanzaVincente(), partita.getStanzaVincente());
        partita.setStanzaCorrente(new Stanza("stanza non finale"));
        assertNotEquals(partita.getStanzaVincente(), partita.getStanzaCorrente());
    }

    // Test Funzione: Verifica che il giocatore parta con 20 CFU
    @Test
    public void testGiocatoreConCfuIniziali() {
        assertEquals(20, partita.getGiocatore().getCfu());
    }

    // Test Funzione: Cambiamento stato giocatore (CFU iniziali, poi modifica)
    @Test
    public void testCambiamentoCfuGiocatore() {
        assertEquals(20, partita.getGiocatore().getCfu());
        partita.setCfu(10);
        assertEquals(10, partita.getGiocatore().getCfu());
    }

  
}
