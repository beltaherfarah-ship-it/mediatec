package mediatheque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BibliothecaireTest {

    @Test
    void unBibliothecaireNEstPasUnCatalogue() {
        assertFalse(Catalogue.class.isAssignableFrom(Bibliothecaire.class));
    }

    @Test
    void salutationContientLeNom() {
        assertEquals("Bonjour, je suis Awa", new Bibliothecaire("Awa").salutation());
    }

    @Test
    void enregistrerEtChercher() {
        Bibliothecaire b = new Bibliothecaire("Awa");
        b.enregistrer(new Livre("Dune", 1965, "Frank Herbert"));
        assertTrue(b.chercher("dune").isPresent());
        assertTrue(b.chercher("Inconnu").isEmpty());
    }

    @Test
    void preterPuisReprendre() throws MediathequeException {
        Bibliothecaire b = new Bibliothecaire("Awa");
        b.enregistrer(new Livre("Dune", 1965, "Frank Herbert"));
        b.preter("Dune");
        DocumentIndisponibleException e =
                assertThrows(DocumentIndisponibleException.class, () -> b.preter("Dune"));
        assertTrue(e.getMessage().contains("Dune"));
        b.reprendre("Dune");
        assertDoesNotThrow(() -> b.preter("Dune"));
    }

    @Test
    void deuxBibliothecairesPartagentLeMemeCatalogue() throws MediathequeException {
        Catalogue<Document> partage = new Catalogue<>();
        Bibliothecaire a = new Bibliothecaire("Awa", partage);
        Bibliothecaire b = new Bibliothecaire("Ben", partage);
        a.enregistrer(new Dvd("Inception", 2010, 148));
        b.preter("Inception"); // visible par b : même catalogue
        assertThrows(DocumentIndisponibleException.class, () -> a.preter("Inception"));
    }

    @Test
    void introuvablePropagueParLeBibliothecaire() {
        assertThrows(DocumentIntrouvableException.class, () -> new Bibliothecaire("Awa").preter("Rien"));
    }
}
