package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoAiuto;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.ComandoGuarda;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;


public class FabbricaDiComandiFisarmonicaTest {

    private FabbricaDiComandiFisarmonica fabbrica;

    @BeforeEach
    void setUp() {
        IO io = new IOConsole();
        fabbrica = new FabbricaDiComandiFisarmonica(io);
    }

    @Test
    void testCostruisciComandoVai() {
        Comando comando = fabbrica.costruisciComando("vai nord");
        assertTrue(comando instanceof ComandoVai, "Dovrebbe essere un'istanza di ComandoVai");
        assertEquals("nord", comando.getParametro(), "Il parametro dovrebbe essere 'nord'");
    }

    @Test
    void testCostruisciComandoPrendi() {
        Comando comando = fabbrica.costruisciComando("prendi chiave");
        assertTrue(comando instanceof ComandoPrendi, "Dovrebbe essere un'istanza di ComandoPrendi");
        assertEquals("chiave", comando.getParametro());
    }

    @Test
    void testCostruisciComandoPosa() {
        Comando comando = fabbrica.costruisciComando("posa libro");
        assertTrue(comando instanceof ComandoPosa, "Dovrebbe essere un'istanza di ComandoPosa");
        assertEquals("libro", comando.getParametro());
    }

    @Test
    void testCostruisciComandoAiuto() {
        Comando comando = fabbrica.costruisciComando("aiuto");
        assertTrue(comando instanceof ComandoAiuto, "Dovrebbe essere un'istanza di ComandoAiuto");
    }

    @Test
    void testCostruisciComandoFine() {
        Comando comando = fabbrica.costruisciComando("fine");
        assertTrue(comando instanceof ComandoFine, "Dovrebbe essere un'istanza di ComandoFine");
    }

    @Test
    void testCostruisciComandoGuarda() {
        Comando comando = fabbrica.costruisciComando("guarda");
        assertTrue(comando instanceof ComandoGuarda, "Dovrebbe essere un'istanza di ComandoGuarda");
    }

    @Test
    void testComandoNonValido() {
        Comando comando = fabbrica.costruisciComando("vola su");
        assertTrue(comando instanceof ComandoNonValido, "Comando sconosciuto dovrebbe restituire ComandoNonValido");
    }

    @Test
    void testComandoSenzaParametro() {
        Comando comando = fabbrica.costruisciComando("vai");
        assertTrue(comando instanceof ComandoVai);
        assertNull(comando.getParametro(), "Parametro dovrebbe essere null se non fornito");
    }

    @Test
    void testComandoVuoto() {
        Comando comando = fabbrica.costruisciComando("");
        assertTrue(comando instanceof ComandoNonValido, "Comando vuoto dovrebbe essere non valido");
    }
}

