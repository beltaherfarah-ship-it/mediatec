package mediatheque;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Composition : le manager POSSÈDE (référence) un catalogue et lui DÉLÈGUE la recherche.
 * Il n'hérite de rien : sa seule responsabilité est la logique d'emprunt.
 */
public class EmpruntManager {

    private final Catalogue<? extends Document> catalogue;
    private final RapportEmprunts rapport; // optionnel (peut être null)

    public EmpruntManager(Catalogue<? extends Document> catalogue) {
        this(catalogue, null);
    }

    public EmpruntManager(Catalogue<? extends Document> catalogue, RapportEmprunts rapport) {
        this.catalogue = Objects.requireNonNull(catalogue, "catalogue");
        this.rapport = rapport;
    }

    public void emprunter(String titre) throws DocumentIntrouvableException, DocumentIndisponibleException {
        try {
            Empruntable e = trouverEmpruntable(titre);
            e.emprunter();
            noterSucces("Emprunt de « " + titre + " »");
        } catch (MediathequeException ex) { // rethrow précis : même types qu'en signature
            noterEchec(ex.getMessage());
            throw ex;
        }
    }

    public void rendre(String titre) throws DocumentIntrouvableException, DocumentIndisponibleException {
        try {
            Empruntable e = trouverEmpruntable(titre);
            e.rendre();
            noterSucces("Retour de « " + titre + " »");
        } catch (MediathequeException ex) {
            noterEchec(ex.getMessage());
            throw ex;
        }
    }

    private Empruntable trouverEmpruntable(String titre)
            throws DocumentIntrouvableException, DocumentIndisponibleException {
        Document doc;
        try {
            doc = catalogue.rechercherParTitre(titre).orElseThrow();
        } catch (NoSuchElementException ex) {
            // Traduction d'une exception technique en exception métier, cause conservée.
            throw new DocumentIntrouvableException(titre, ex);
        }
        if (doc instanceof Empruntable e) {
            return e;
        }
        throw new DocumentIndisponibleException(titre, "consultable sur place uniquement");
    }

    private void noterSucces(String m) { if (rapport != null) rapport.enregistrerSucces(m); }
    private void noterEchec(String m)  { if (rapport != null) rapport.enregistrerEchec(m); }
}
