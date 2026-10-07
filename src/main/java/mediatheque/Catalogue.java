package mediatheque;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Catalogue générique : un Catalogue<Livre>, un Catalogue<Dvd>, un Catalogue<Document>... */
public class Catalogue<T extends Document> {

    private final List<T> elements = new ArrayList<>();

    public void ajouter(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Élément null");
        }
        elements.add(element);
    }

    public Optional<T> rechercherParTitre(String titre) {
        return elements.stream()
                .filter(e -> e.getTitre().equalsIgnoreCase(titre))
                .findFirst();
    }

    /** Aucun instanceof : le polymorphisme de descriptionCourte() fait le travail. */
    public void afficherTout() {
        elements.forEach(e -> System.out.println(e.descriptionCourte()));
    }

    public int taille() { return elements.size(); }

      List<T> getElements() { return List.copyOf(elements); }

}
