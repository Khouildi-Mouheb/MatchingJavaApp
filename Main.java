import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();

        Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.5);

        // içi j'ajoute un exemple de recuperateur qui fonctionne avec une liste static
        Recuperateur recuperateur = new RecuperateurStatique();

        List<Nom> listeNoms = recuperateur.recuperer();
        List<Nom> listeNoms2 = recuperateur.recuperer();

        // Créer une instance de GenerateurDeCondidat
        GenerateurDeCondidat generateur = new GenerateurParTaille();

        // Créer un CompositionneurDeNom
        CompositionneurDeNom compositionneur = new CompositionneurStandard();

        // Créer des instances de ComparateurNoms et ComparateurDeChaine
        Comparateur comparateurExact = new ComparateurExact();
        ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurExact);

        Comparateur comparateurComparable = new ComparateurAvecComparable();

        ComparateurNoms comparateurDeNom2 = new ComparteurNomsParComposition(compositionneur, comparateurExact);

        // Créer une instance du moteur de matching
        // on doit ajouter une autre atribus pour le moteur qui est la liste vièrge
        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateurDeNom2, generateur);

        System.out.println("-------------------Matching_App------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-La Comparaison entre les noms est en cours-");
        // Appeler la méthode de test pour ComparateurExact
        moteur.comparer(listeNoms2, listeNoms);

        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-La recherche entre les noms est en cours-");

        // Appeler la méthode de test pour effectuer le matching
        List<CoupleDenomAvecScore> resultat = moteur.rechercher(listeNoms);
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-Affichage de la resultat du recherche apres la selection: -");

        // Afficher les résultats du matching avec le Selectionneur
        resultat = selectionneur.selectionner(resultat);

        // affichage:
        for (CoupleDenomAvecScore couple : resultat) {
            System.out.println("Nom: " + couple.getCouple().getNom() + ", Score: " + couple.getScore());
        }
    }
}
