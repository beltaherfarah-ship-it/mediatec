package mediatheque;

/** Consultable sur place uniquement : n'implémente PAS Empruntable. */
public class Revue extends Document {

    private final int numero;

    public Revue(String titre, int annee, int numero) {
        super(titre, annee);
        this.numero = numero;
    }

    public int getNumero() { return numero; }

    @Override
    public String descriptionCourte() {
        return "Revue : " + getTitre() + " n°" + numero + " (" + getAnnee() + ") [sur place]";
    }
}
