package mediatheque;

public abstract class Dvd extends Document implements Empruntable{
    private final int dureeMinutes;

    public Dvd(String titre, int annee, int dureeMinutes) {
        super(titre, annee);
        this.dureeMinutes = dureeMinutes;
    }
}
