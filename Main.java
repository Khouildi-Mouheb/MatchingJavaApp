import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();
        ComparateurNoms comparateur = new ComparateurNomsSimple();
        Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.5); // Exemple d'utilisation d'un seuil
        // içi j'ajoute un exemple de recuperateur qui fonctionne avec une liste static
        // qui j'ai genèré
        Recuperateur recuperateur = new RecuperateurStatique();
        // on doit ajouter une autre atribus pour le moteur qui est la liste vièrge
        List<Nom> listeNoms = recuperateur.recuperer();
        List<Nom> listeNoms2 = recuperateur.recuperer();
        // Créer une instance du moteur de matching
        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur);
        System.out.println("-------------------Matching_App------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-La Comparaison entre les noms est en cours-");
        // Appeler la méthode de test pour ComparateurExact
        moteur.comparer(listeNoms2, listeNoms);
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-La recherche entre les noms est en cours-");

        // Appeler la méthode de test pour effectuer le matching
        Map<Nom, Double> resultat = moteur.rechercher(listeNoms);
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-Affichage de la resultat du recherche apres la selection: -");

        // Afficher les résultats du matching
        selectionneur.selectionner(resultat);
    }
}
