package mediatheque;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogueTest {

    private Catalogue<Document> catalogue;
    private Livre livre;
    private Dvd dvd;
    private Revue revue;

    @BeforeEach
    void setUp() {
        catalogue = new Catalogue<>();
        livre = new Livre("Dune", 1965, "Frank Herbert");
        dvd = new Dvd("Inception", 2010, 148);
        revue = new Revue("Science & Vie", 2024, 1285);
        catalogue.ajouter(livre);
        catalogue.ajouter(dvd);
        catalogue.ajouter(revue);
    }

    // --- Emprunt nominal ---
    @Test
    void empruntNominalLivre() {
        livre.emprunter();
        assertTrue(livre.estEmprunte());
        livre.rendre();
        assertFalse(livre.estEmprunte());
    }

    @Test
    void empruntNominalDvd() {
        dvd.emprunter();
        assertTrue(dvd.estEmprunte());
    }

    // --- Double emprunt ---
    @Test
    void doubleEmpruntLivreLeveException() {
        livre.emprunter();
        assertThrows(IllegalStateException.class, livre::emprunter);
    }

    @Test
    void doubleEmpruntDvdLeveException() {
        dvd.emprunter();
        assertThrows(IllegalStateException.class, dvd::emprunter);
    }

    @Test
    void rendreSansEmprunterLeveException() {
        assertThrows(IllegalStateException.class, livre::rendre);
    }

    @Test
    void revueNEstPasEmpruntable() {
        assertFalse(((Object) revue) instanceof Empruntable);
    }

    // --- Recherche ---
    @Test
    void rechercheTrouvee() {
        assertEquals(Optional.of(dvd), catalogue.rechercherParTitre("inception"));
    }

    @Test
    void rechercheInfructueuse() {
        assertTrue(catalogue.rechercherParTitre("Inconnu").isEmpty());
    }

    // --- Divers ---
    @Test
    void titreVideInterdit() {
        assertThrows(IllegalArgumentException.class, () -> new Livre(" ", 2000, "X"));
    }

    @Test
    void descriptionsPolymorphes() {
        assertTrue(livre.descriptionCourte().startsWith("Livre"));
        assertTrue(dvd.descriptionCourte().startsWith("DVD"));
        assertTrue(revue.descriptionCourte().startsWith("Revue"));
    }

    // --- Bonus ---
    @Test
    void maxRenvoieLeDernierAlphabetiquement() {
        assertSame(revue, Catalogue.max(catalogue.getElements()));
    }

    @Test
    void maxCollectionVideLeveException() {
        assertThrows(java.util.NoSuchElementException.class, () -> Catalogue.max(List.<Document>of()));
    }

    @Test
    void maxFonctionneAvecDesEntiers() {
        assertEquals(9, Catalogue.max(List.of(3, 9, 4)));
    }
}
