package mediatheque;

public class Livre extends Document implements Empruntable {

    private final String auteur;
    private boolean emprunte;

    public Livre(String titre, int annee, String auteur) {
        super(titre, annee);
        this.auteur = auteur;
    }

    public String getAuteur() { return auteur; }

    @Override
    public void emprunter() {
        if (emprunte) {
            throw new IllegalStateException("Le livre « " + getTitre() + " » est déjà emprunté");
        }
        emprunte = true;
    }

    @Override
    public void rendre() {
        if (!emprunte) {
            throw new IllegalStateException("Le livre « " + getTitre() + " » n'est pas emprunté");
        }
        emprunte = false;
    }

    @Override
    public boolean estEmprunte() { return emprunte; }

    @Override
    public String descriptionCourte() {
        return "Livre : " + getTitre() + " de " + auteur + " (" + getAnnee() + ")"
                + (emprunte ? " [emprunté]" : "");
    }
}
