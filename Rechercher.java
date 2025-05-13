import java.util.ArrayList;
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
    public List<CoupleDenomAvecScore> rechercher(List<Nom> nomCible, List<Nom> listeNoms) {
        // Nettoyer le nom cible avant la comparaison
        listeNoms = pretraiteur.nettoyer(listeNoms);
        nomCible = pretraiteur.nettoyer(nomCible);
        Nom nomNettoye = nomCible.get(0); // On suppose qu'il n'y a qu'un seul nom cible
        List<CoupleDenomAvecScore> m = new java.util.ArrayList<>();
        // Parcourir la liste des noms pour trouver le meilleur match
        for (Nom nom : listeNoms) {

            double score = comparateur.comparer(nomNettoye, nom);
            m.add(new CoupleDenomAvecScore(new CoupleDeNom(nomNettoye, nom), score));

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
    
    public List<CoupleDenomAvecScore> rechercher(List<CoupleDeNom> condidats){
    List<CoupleDenomAvecScore> result = new ArrayList<>();
    for (CoupleDeNom couple : condidats) {
        Nom nom1 = couple.getNom1();
        Nom nom2 = couple.getNom2();
        double score = comparateur.comparer(nom1, nom2);
        result.add(new CoupleDenomAvecScore(couple, score));
    }
    return result;
}
}
