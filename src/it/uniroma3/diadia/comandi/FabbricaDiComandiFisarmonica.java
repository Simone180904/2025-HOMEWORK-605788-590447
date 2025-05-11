package it.uniroma3.diadia.comandi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica {

    private IO io;
    private Map<String, Class<? extends Comando>> comandi;

    public FabbricaDiComandiFisarmonica(IO io) {
        this.io = io;
        this.comandi = new HashMap<>();
        
        // Popolamento della mappa con il comando e la relativa classe
        comandi.put("vai", ComandoVai.class);
        comandi.put("prendi", ComandoPrendi.class);
        comandi.put("posa", ComandoPosa.class);
        comandi.put("aiuto", ComandoAiuto.class);
        comandi.put("fine", ComandoFine.class);
        comandi.put("guarda", ComandoGuarda.class);
    }

    public Comando costruisciComando(String istruzione) {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Comando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next(); // Prima parola: nome del comando
        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next(); // Seconda parola: parametro (se presente)

        // Controllo se il comando esiste nella mappa
        if (nomeComando != null && comandi.containsKey(nomeComando)) {
            try {
                // Istanzia il comando usando la reflection
                comando = comandi.get(nomeComando).newInstance();
                comando.setParametro(parametro);
            } catch (Exception e) {
                comando = new ComandoNonValido();
            }
        } else {
            comando = new ComandoNonValido();
        }

        comando.setIo(this.io);
        return comando;
    }
}
