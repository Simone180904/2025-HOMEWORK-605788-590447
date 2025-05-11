package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOConsoleTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));  // Reindirizza output
    }

    @Test
    void testMostraMessaggio() {
        IOConsole io = new IOConsole();
        io.mostraMessaggio("Messaggio di prova");
        assertTrue(outputStream.toString().contains("Messaggio di prova"),
                "Il messaggio dovrebbe essere stampato su System.out");
    }

    @Test
    void testLeggiRiga() {
        String inputSimulato = "comandoInserito\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputSimulato.getBytes());
        System.setIn(inputStream);

        IOConsole io = new IOConsole();
        String rigaLetta = io.leggiRiga();
        assertEquals("comandoInserito", rigaLetta,
                "La riga letta dovrebbe essere uguale all'input simulato");
    }
}
