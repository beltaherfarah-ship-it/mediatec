package mediatheque;

public interface Empruntable {
    /** @throws IllegalStateException si déjà emprunté */
    void emprunter();

    /** @throws IllegalStateException si non emprunté */
    void rendre();

    boolean estEmprunte();
}
