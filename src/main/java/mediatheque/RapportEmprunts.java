package mediatheque;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * Bonus : rapport d'activité à utiliser avec try-with-resources.
 * close() écrit le bilan puis libère le flux ; il est idempotent.
 */
public class RapportEmprunts implements AutoCloseable {

    private final PrintWriter out;
    private int succes;
    private int echecs;
    private boolean ferme;

    public RapportEmprunts(Writer writer) {
        this.out = new PrintWriter(writer);
    }

    public void enregistrerSucces(String message) {
        verifierOuvert();
        succes++;
        out.println("[OK]    " + message);
    }

    public void enregistrerEchec(String message) {
        verifierOuvert();
        echecs++;
        out.println("[ECHEC] " + message);
    }

    public int getSucces() { return succes; }
    public int getEchecs() { return echecs; }

    /** Pas de « throws Exception » : on rétrécit la signature d'AutoCloseable. */
    @Override
    public void close() {
        if (ferme) {
            return;
        }
        ferme = true;
        out.println("--- Bilan : " + succes + " réussi(s), " + echecs + " échec(s) ---");
        out.close(); // flush + fermeture
    }

    private void verifierOuvert() {
        if (ferme) {
            throw new IllegalStateException("Rapport déjà fermé");
        }
    }
}
