
import java.util.List;

public class MoteurDeMatching {
    // Composition : comparateur est créé directement ici
    private ComparateurNoms comparateurDeNom;
    private GenerateurDeCondidat generateur;

    // Agrégation : prétraiteur et selectionneur sont passés en argument du
    // constructeur
    private Pretraiteur pretraiteur;
    private List<Nom> listeNom = new java.util.ArrayList<>();

    // Constructeur
    public MoteurDeMatching(Pretraiteur pretraiteur, ComparateurNoms comparateurDeNom,
            GenerateurDeCondidat generateur) {

        this.generateur = generateur;
        this.pretraiteur = pretraiteur;
        this.comparateurDeNom = comparateurDeNom;
        // ou une autre implémentation selon ton choix
    }

    // Méthode pour effectuer le matching
    public List<CoupleDenomAvecScore> rechercher(List<Nom> listNoms) {

        // Créer une instance de Recherche avec prétraiteur et comparateur
        Rechercher recherche = new Rechercher(pretraiteur, comparateurDeNom);

        // Créer des objets Nom avec des chaînes "brutes"

        // Nettoyer les noms en utilisant le prétraiteur

        listeNom = pretraiteur.nettoyer(listNoms);
        /*
         * Nom nom1 = listNoms.getFirst();
         * listeNom.add(nom1);
         * listNoms.removeFirst();
         * 
         * Nom nom2 = listNoms.getFirst();
         * listeNom.add(nom2);
         * listNoms.removeFirst();
         * Nom nom3 = listNoms.getFirst();
         * listeNom.add(nom3);
         */

        // Nom cible à rechercher
        Nom nomCible = new Nom("Nk-1", "yacine boujelbane");

        // Effectuer la recherche
        List<Nom> nomCibleL = new java.util.ArrayList<>();
        nomCibleL.add(nomCible);

        List<CoupleDenomAvecScore> resultat = recherche.rechercher(nomCibleL, listeNom);

        return resultat;

    }

    // Méthode pour tester le Comparateur
    public List<CoupleDenomAvecScore> comparer(List<Nom> listNoms2, List<Nom> listNoms) {
        listNoms2 = pretraiteur.nettoyer(listNoms2);
        listNoms = pretraiteur.nettoyer(listNoms);
        List<CoupleDeNom> temp = new java.util.ArrayList<>();
        List<CoupleDenomAvecScore> temp2 = new java.util.ArrayList<>();
        // Map<Nom, Double> resultat;
        double somme = 0.0;
        double score = 0.0;
        for (Nom nom : listNoms2) {

            if (generateur != null) {
                List<Nom> nomAgenerer = new java.util.ArrayList<>();
                nomAgenerer.add(nom);
                temp = generateur.genererCondidat(nomAgenerer, listNoms);
            }
            score = 0.0;
            for (CoupleDeNom couple : temp) {
                if (comparateurDeNom.comparer(couple.getNom(), couple.getNom2()) > score) {
                    score = comparateurDeNom.comparer(couple.getNom(), couple.getNom2());
                    temp2.add(new CoupleDenomAvecScore(new CoupleDeNom(couple.getNom(), couple.getNom2()), score));

                }

                // resultat.put(nom, score);

            }
            somme += score;

        }
        return temp2;
        // prochainnement on doit intégre le selectionneur
    }
}
