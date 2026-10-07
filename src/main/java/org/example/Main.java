package mediatheque;

public class Main {
    public static void main(String[] args) {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Livre("Le Petit Prince", 1943, "Saint-Exupéry"));
        catalogue.ajouter(new Dvd("Inception", 2010, 148));
        catalogue.ajouter(new Revue("Science & Vie", 2024, 1285));

        catalogue.afficherTout();

        catalogue.rechercherParTitre("Inception")
                .ifPresentOrElse(d -> System.out.println("Trouvé : " + d),
                        () -> System.out.println("Introuvable"));

        if (catalogue.rechercherParTitre("Le Petit Prince").orElseThrow() instanceof Empruntable e) {
            e.emprunter();
            try {
                e.emprunter(); // double emprunt
            } catch (IllegalStateException ex) {
                System.out.println("Erreur attendue : " + ex.getMessage());
            }
        }

        System.out.println("Max (ordre alphabétique) : " + Catalogue.max(catalogue.getElements()));
    }
}
