import java.util.List;

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
    public Nom rechercher(Nom nomCible, List<Nom> listeNoms) {
        // Nettoyer le nom cible avant la comparaison
        Nom nomNettoye = pretraiteur.nettoyer(nomCible);

        // Parcourir la liste des noms pour trouver le meilleur match
        for (Nom nom : listeNoms) {
            Nom nomNettoyeListe = pretraiteur.nettoyer(nom);
            double score = comparateur.comparer(nomNettoye, nomNettoyeListe);

            // Si la similarité est parfaite (score = 1.0), retourner le nom
            if (score == 1.0) {
                return nom;
            }
        }
        // Retourner null si aucun nom ne correspond
        return null;
    }
}
