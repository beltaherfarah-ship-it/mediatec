package mediatheque;

/** Contrat des documents qui s'empruntent (Livre et Dvd, pas Revue). */
public interface Empruntable {

    /** @throws IllegalStateException si déjà emprunté */
    void emprunter();

    /** @throws IllegalStateException si non emprunté */
    void rendre();

    boolean estEmprunte();
}
