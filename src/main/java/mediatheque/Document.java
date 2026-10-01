package mediatheque;

import java.util.Objects;

/** Classe abstraite : tout ce qui se trouve dans la médiathèque. */
public abstract class Document implements Comparable<Document> {

    private final String titre;
    private final int annee;

    protected Document(String titre, int annee) {
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("Le titre est obligatoire");
        }
        this.titre = titre;
        this.annee = annee;
    }

    public String getTitre() { return titre; }
    public int getAnnee() { return annee; }

    /** Chaque sous-classe décrit son propre document (polymorphisme). */
    public abstract String descriptionCourte();

    /** Bonus : ordre naturel = ordre alphabétique des titres. */
    @Override
    public int compareTo(Document autre) {
        return titre.compareToIgnoreCase(autre.titre);
    }

    @Override
    public String toString() { return descriptionCourte(); }

    @Override
    public boolean equals(Object o) {
        return o instanceof Document d && titre.equals(d.titre) && annee == d.annee
                && getClass() == d.getClass();
    }

    @Override
    public int hashCode() { return Objects.hash(titre, annee, getClass()); }
}
