package mediatheque;

import java.util.Objects;
import java.util.Optional;

/**
 * Refactoring par COMPOSITION (« a un », pas « est un »).
 * Le catalogue est un détail interne : il n'est ni exposé ni hérité.
 * Voir docs/analyse-heritage.md pour ce qui n'allait pas avec « extends Catalogue ».
 */
public class Bibliothecaire {

    private final String nom;
    private final Catalogue<Document> catalogue;
    private final EmpruntManager manager;

    public Bibliothecaire(String nom, Catalogue<Document> catalogue) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        this.nom = nom;
        this.catalogue = Objects.requireNonNull(catalogue, "catalogue");
        this.manager = new EmpruntManager(catalogue);
    }

    public Bibliothecaire(String nom) {
        this(nom, new Catalogue<>());
    }

    public String salutation() { return "Bonjour, je suis " + nom; }

    public void accueillir() { System.out.println(salutation()); }

    // API métier du bibliothécaire : des verbes de son métier, pas ceux du catalogue.
    public void enregistrer(Document document) { catalogue.ajouter(document); }

    public Optional<Document> chercher(String titre) { return catalogue.rechercherParTitre(titre); }

    public void preter(String titre) throws DocumentIntrouvableException, DocumentIndisponibleException {
        manager.emprunter(titre);
    }

    public void reprendre(String titre) throws DocumentIntrouvableException, DocumentIndisponibleException {
        manager.rendre(titre);
    }
}
