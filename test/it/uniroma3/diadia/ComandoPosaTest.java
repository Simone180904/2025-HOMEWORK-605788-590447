package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoPosa;
public class ComandoPosaTest {

    private ComandoPosa comandoPosa;
    private Partita partita;
    private IOConsole io;
    private Attrezzo attrezzo;

    @BeforeEach
    void setUp() {
        comandoPosa = new ComandoPosa();
        io = new IOConsole();
        comandoPosa.setIo(io);

        partita = new Partita();
        partita.setStanzaCorrente(new Stanza("Atrio"));

        attrezzo = new Attrezzo("chiave", 1);
        partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
    }

    @Test
    void testPosaAttrezzoPresente() {
        comandoPosa.setParametro("chiave");
        comandoPosa.esegui(partita);

        assertNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"), 
            "L'attrezzo dovrebbe essere stato rimosso dalla borsa.");
        assertNotNull(partita.getStanzaCorrente().getAttrezzo("chiave"), 
            "L'attrezzo dovrebbe essere stato posato nella stanza.");
    }

    @Test
    void testPosaAttrezzoNonPresente() {
        comandoPosa.setParametro("lanterna"); // non è presente nella borsa
        comandoPosa.esegui(partita);

        assertNull(partita.getStanzaCorrente().getAttrezzo("lanterna"),
            "L'attrezzo non dovrebbe essere stato posato, perché non era nella borsa.");
    }

    @Test
    void testPosaSenzaParametro() {
        comandoPosa.setParametro(null); // nessun parametro
        comandoPosa.esegui(partita);

        assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"),
            "L'attrezzo non dovrebbe essere stato rimosso senza parametro.");
    }

 
}
