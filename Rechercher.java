
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe Recherche
public class Rechercher {
    private Pretraiteur pretraiteur;
    private ComparateurNoms comparateur;

    // Constructeur de la classe Recherche
    public Rechercher(Pretraiteur pretraiteur, ComparateurNoms comparateur) {
        this.pretraiteur = pretraiteur;
        this.comparateur = comparateur;
    }

    // Méthode de recherche dans la liste des noms
    public Map<Nom, Double> rechercher(Nom nomCible, List<Nom> listeNoms) {
        // Nettoyer le nom cible avant la comparaison
        Nom nomNettoye = pretraiteur.nettoyer(nomCible);
        Map<Nom, Double> m = new HashMap<>();
        // Parcourir la liste des noms pour trouver le meilleur match
        for (Nom nom : listeNoms) {
            Nom nomNettoyeListe = pretraiteur.nettoyer(nom);
            double score = comparateur.comparer(nomNettoye, nomNettoyeListe);
            m.put(nom, score);

            // Si la similarité est parfaite (score = 1.0), retourner le nom
            /*
             * if (score == 1.0) {
             * return nom;
             * }
             * }
             * // Retourner null si aucun nom ne correspond
             * return null;
             */
        }
        return m;
    }
}