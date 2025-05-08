
import java.util.List;
import java.util.Map;

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
    public Map<Nom, Double> rechercher(List<Nom> listNoms) {

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
        Nom nomCible = new Nom("yacine boujelbane");

        // Effectuer la recherche
        Map<Nom, Double> resultat = recherche.rechercher(nomCible, listeNom);
        for (Map.Entry<Nom, Double> entry : resultat.entrySet()) {
            Nom nom = entry.getKey();
            double score = entry.getValue();
            System.out.println("Nom : " + nom.getNom() + ", Score : " + score);

        }
        return resultat;

    }

    // Méthode pour tester le Comparateur
    public void comparer(List<Nom> listNoms2, List<Nom> listNoms) {
        listNoms2 = pretraiteur.nettoyer(listNoms2);
        listNoms = pretraiteur.nettoyer(listNoms);
        List<Nom> temp = new java.util.ArrayList<>();
        // Map<Nom, Double> resultat;
        double somme = 0.0;
        double score = 0.0;
        for (Nom nom : listNoms2) {

            if (generateur != null) {
                temp = generateur.genererCondidat(nom, listNoms);
            } else {
                temp = listNoms;
            }
            score = 0.0;
            for (Nom nom2 : temp) {
                if (comparateurDeNom.comparer(nom, nom2) > score) {
                    score = comparateurDeNom.comparer(nom, nom2);
                }
                score = comparateurDeNom.comparer(nom, nom2);
                System.out.println("Comparaison entre " + nom.getNom() + " et " + nom2.getNom() + ": "
                        + comparateurDeNom.comparer(nom, nom2));
                // resultat.put(nom, score);

            }
            somme += score;

        }
        System.out.println("La somme des scores est : " + somme / listNoms2.size());
        // prochainnement on doit intégre le selectionneur
    }
}
