package mediatheque;

/** Aucun document de ce titre dans le catalogue. */
public class DocumentIntrouvableException extends MediathequeException {

    private final String titre;

    public DocumentIntrouvableException(String titre) {
        super("Document introuvable : « " + titre + " »");
        this.titre = titre;
    }

    public DocumentIntrouvableException(String titre, Throwable cause) {
        super("Document introuvable : « " + titre + " »", cause);
        this.titre = titre;
    }

    public String getTitre() { return titre; }
}
