package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

    private String attrezzoLucente;  // Nome dell'attrezzo che illumina la stanza

    public StanzaBuia(String nome, String attrezzoLucente) {
        super(nome);
        this.attrezzoLucente = attrezzoLucente;
    }

    @Override
    public String getDescrizione() {
        // Se l'attrezzo lucente non è presente nella stanza, restituisci la descrizione "buia"
        if (!this.hasAttrezzo(attrezzoLucente)) {
            return "Qui c'è un buio pesto. Non vedi nulla.";
        }
        // Altrimenti, restituisci la descrizione normale della stanza
        return super.getDescrizione();
    }
}
