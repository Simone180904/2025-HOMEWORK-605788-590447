package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

    private Partita partita;
    private Stanza atrio, biblioteca, salaStudio;
    private ComandoVai comandoVai;
    private IO io;

    @BeforeEach
    void setUp() {
        partita = new Partita();
        atrio = new Stanza("Atrio");
        biblioteca = new Stanza("Biblioteca");
        salaStudio = new Stanza("Sala Studio");

        // Imposta le stanze adiacenti
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        atrio.impostaStanzaAdiacente("est", salaStudio);
        partita.setStanzaCorrente(atrio);

        // Inizializza comando e IOConsole
        comandoVai = new ComandoVai();
        io = new IOConsole();
        comandoVai.setIo(io);

        // Imposta CFU iniziali
        partita.getGiocatore().setCfu(10);
    }

    @Test
    void testVaiDirezioneCorretta() {
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
        assertEquals(9, partita.getGiocatore().getCfu());
    }

    @Test
    void testVaiDirezioneNonValida() {
        comandoVai.setParametro("sud");
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
    }

    @Test
    void testVaiSenzaDirezione() {
        comandoVai.setParametro(null);
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
    }

    @Test
    void testVaiConCfuZero() {
        partita.getGiocatore().setCfu(0);
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
    }


    @Test
    void testVaiAltraDirezione() {
        comandoVai.setParametro("est");
        comandoVai.esegui(partita);
        assertEquals("Sala Studio", partita.getStanzaCorrente().getNome());
        assertEquals(9, partita.getGiocatore().getCfu());
    }
}
