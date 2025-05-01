
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
    public void comparer() {
        // Créer des objets Nom pour le test
        Nom nomBrut1 = new Nom(" mouheb   khouildi");
        Nom nomBrut2 = new Nom("mouhebkhouildi");
        Nom nomBrut3 = new Nom("yacine boujelbane");

        // Nettoyer les noms en utilisant le prétraiteur
        Nom nom1 = pretraiteur.nettoyer(nomBrut1);
        Nom nom2 = pretraiteur.nettoyer(nomBrut2);
        Nom nom3 = pretraiteur.nettoyer(nomBrut3);

        // Utiliser le comparateur pour comparer les noms
        double resultat1 = comparateur.comparer(nom1, nom2); // Devrait retourner 1.0 car les noms sont identiques
        double resultat2 = comparateur.comparer(nom1, nom3); // Devrait retourner 0.0 car les noms sont différents

        // Afficher les résultats du test
        System.out.println("Comparaison de " + nomBrut1.getNom() + " et " + nomBrut2.getNom() + " : " + resultat1); // 1.0
        System.out.println("Comparaison de " + nom1.getNom() + " et " + nom3.getNom() + " : " + resultat2); // 0.0

        // Vérification des résultats
        if (resultat1 == 1.0) {
            System.out.println("Test de comparaison réussi pour les noms identiques.");
        } else {
            System.out.println("Test de comparaison échoué pour les noms identiques.");
        }

        if (resultat2 == 0.0) {
            System.out.println("Test de comparaison réussi pour les noms différents.");
        } else {
            System.out.println("Test de comparaison échoué pour les noms différents.");
        }
    }

    // Méthode main pour exécuter le test

}
