package mediatheque;

public class Livre extends Document implements Empruntable{

    private final String auteur;
    private boolean emprunte;

    public Livre(String titre, int annee, String auteur) {
        super(titre, annee);
        this.auteur = auteur;
    }

    public String getAuteur() { return auteur; }

    @Override
    public String descriptionCourte() {
        return "";
    }

    @Override
    public void emprunter() {

    }

    @Override
    public void rendre() {

    }

    @Override
    public boolean estEmprunte() {
        return false;
    }
}
