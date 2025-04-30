
import java.util.List;

public class MoteurDeMatching {
    // Composition : comparateur est créé directement ici
    private final ComparateurNoms comparateur;

    // Agrégation : prétraiteur et selectionneur sont passés en argument du
    // constructeur
    private Pretraiteur pretraiteur;
    private Selectionneur selectionneur;

    // Constructeur
    public MoteurDeMatching(Pretraiteur pretraiteur, Selectionneur selectionneur) {
        this.pretraiteur = pretraiteur;
        this.selectionneur = selectionneur;
        // Composition : création directe du comparateur
        this.comparateur = new ComparateurExact(); // ou une autre implémentation selon ton choix
    }

    // Méthode pour effectuer le matching
    public void effectuerMatching(List<Nom> listNoms) {
        // Créer une instance de Recherche avec prétraiteur et comparateur
        Rechercher recherche = new Rechercher(pretraiteur, comparateur);

        // Créer des objets Nom avec des chaînes "brutes"

        // Nettoyer les noms en utilisant le prétraiteur
        Nom nom1 = listNoms.getFirst();
        Nom nom2 = listNoms.getFirst();
        Nom nom3 = listNoms.getFirst();

        nom1 = pretraiteur.nettoyer(nom1);
        nom2 = pretraiteur.nettoyer(nom2);
        nom3 = pretraiteur.nettoyer(nom3);

        System.out.println("nomBrut1 apres nettoyage :" + nom1);
        System.out.println("nomBrut2 apres nettoyage :" + nom2);
        System.out.println("nomBrut3 apres nettoyage :" + nom3);

        // Nom cible à rechercher
        Nom nomCible = new Nom(" kodskhouildi ");

        // Effectuer la recherche
        Nom resultat = recherche.rechercher(nomCible, listNoms);

        // Afficher le résultat
        if (resultat != null) {
            System.out.println("Nom trouvé : " + resultat);
        } else {
            System.out.println("Nom non trouvé.");
        }
    }

    // Méthode pour tester le ComparateurExact
    public void testerComparateurExact() {
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
