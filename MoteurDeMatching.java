
import java.util.List;
import java.util.Map;

public class MoteurDeMatching {
    // Composition : comparateur est créé directement ici
    private ComparateurNoms comparateur;

    // Agrégation : prétraiteur et selectionneur sont passés en argument du
    // constructeur
    private Pretraiteur pretraiteur;
    private List<Nom> listeNom = new java.util.ArrayList<>();

    // Constructeur
    public MoteurDeMatching(Pretraiteur pretraiteur, ComparateurNoms comparateur) {
        this.pretraiteur = pretraiteur;
        this.comparateur = comparateur;
        // Composition : création directe du comparateur
        this.comparateur = new ComparateurNoms_simple(); // ou une autre implémentation selon ton choix
    }

    // Méthode pour effectuer le matching
    public Map<Nom, Double> rechercher(List<Nom> listNoms) {

        // Créer une instance de Recherche avec prétraiteur et comparateur
        Rechercher recherche = new Rechercher(pretraiteur, comparateur);

        // Créer des objets Nom avec des chaînes "brutes"

        // Nettoyer les noms en utilisant le prétraiteur
        Nom nom1 = listNoms.getFirst();
        listeNom.add(nom1);
        listNoms.removeFirst();

        Nom nom2 = listNoms.getFirst();
        listeNom.add(nom2);
        listNoms.removeFirst();
        Nom nom3 = listNoms.getFirst();
        listeNom.add(nom3);

        nom1 = pretraiteur.nettoyer(nom1);
        nom2 = pretraiteur.nettoyer(nom2);
        nom3 = pretraiteur.nettoyer(nom3);

        System.out.println("nomBrut1 apres nettoyage :" + nom1);
        System.out.println("nomBrut2 apres nettoyage :" + nom2);
        System.out.println("nomBrut3 apres nettoyage :" + nom3);

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

    // Méthode pour tester le ComparateurExact
    public void comparer(List<Nom> listNoms2, List<Nom> listNoms) {
        // Map<Nom, Double> resultat;
        double somme = 0.0;
        double score = 0.0;
        for (Nom nom : listNoms2) {

            score = 0.0;
            for (Nom nom2 : listNoms) {
                if (comparateur.comparer(nom, nom2) > score) {
                    score = comparateur.comparer(nom, nom2);
                }
                score = comparateur.comparer(nom, nom2);
                System.out.println("Comparaison entre " + nom.getNom() + " et " + nom2.getNom() + ": "
                        + comparateur.comparer(nom, nom2));
                // resultat.put(nom, score);

            }
            somme += score;

        }
        System.out.println("La somme des scores est : " + somme / listNoms2.size());
        // return resultat;

    }
}
