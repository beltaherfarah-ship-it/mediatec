package mediatheque;

/** Le document existe mais ne peut pas être emprunté (déjà emprunté, consultation sur place...). */
public class DocumentIndisponibleException extends MediathequeException {

    private final String titre;

    public DocumentIndisponibleException(String titre) {
        this(titre, "déjà emprunté");
    }

    public DocumentIndisponibleException(String titre, String raison) {
        super(message(titre, raison));
        this.titre = titre;
    }

    public DocumentIndisponibleException(String titre, String raison, Throwable cause) {
        super(message(titre, raison), cause);
        this.titre = titre;
    }

    public String getTitre() { return titre; }

    private static String message(String titre, String raison) {
        return "Document indisponible : « " + titre + " » (" + raison + ")";
    }
}
