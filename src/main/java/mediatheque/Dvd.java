package mediatheque;

public abstract class Dvd extends Document implements Empruntable{
    private final int dureeMinutes;
    private boolean emprunte;

    public Dvd(String titre, int annee, int dureeMinutes) {
        super(titre, annee);
        this.dureeMinutes = dureeMinutes;
    }
    public int getDureeMinutes() { return dureeMinutes; }
    
    public void emprunter() {
        if (emprunte) {
            throw new IllegalStateException("le DVD « " + getTitre() + " » est déjà emprunté");

        }
        emprunte = false
    }

}
