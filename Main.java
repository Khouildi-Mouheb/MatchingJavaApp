import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();

        Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.0);

        // içi j'ajoute un exemple de recuperateur qui fonctionne avec une liste static
        Recuperateur recuperateur = new RecuperateurStatique();
        Recuperateur recuperateur2 = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_658k.csv");

        List<Nom> listeNoms = recuperateur.importData();
        List<Nom> listeNoms2 = recuperateur.importData();
        List<Nom> listeNoms3 = recuperateur2.importData();

        /*
         * for (Nom nom : listeNoms3) {
         * System.out.println("Nom: " + nom.getNom() + ", id: " + nom.getId());
         * }
         */

        if ((listeNoms3.size() > 2000)) {
            int j = 0;

            while (j + 1000 < listeNoms3.size() - 1001) {
                List<Nom> listeNoms5 = listeNoms3.subList(j, j + 1000);
                j = j + 1000;
                if (j + 1000 > listeNoms3.size() - 1001) {
                    listeNoms5 = listeNoms3.subList(j, listeNoms3.size() - 1);
                }
                // Créer une instance de GenerateurDeCondidat
                GenerateurDeCondidat generateur = new GenerateurParTaille();

                // Créer un CompositionneurDeNom
                CompositionneurDeNom compositionneur = new CompositionneurStandard();

                // Créer des instances de ComparateurNoms et ComparateurDeChaine
                Comparateur comparateurExact = new ComparateurExact();
                ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurExact);

                // Créer une instance du moteur de matching
                // on doit ajouter une autre atribus pour le moteur qui est la liste vièrge
                MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur, generateur);

                System.out.println("-------------------Matching_App------------------");
                System.out.println("-------------------------------------------------");
                System.out.println("-La Comparaison entre les noms est en cours-");
                // Appeler la méthode de test pour ComparateurExact
                List<CoupleDenomAvecScore> listeNoms4 = moteur.comparer(listeNoms5, listeNoms5);

                System.out.println("-------------------------------------------------");
                System.out.println("-------------------------------------------------");
                System.out.println("-La recherche entre les noms est en cours-");

                // Appeler la méthode de test pour effectuer le matching
                // List<CoupleDenomAvecScore> resultat = moteur.rechercher(listeNoms3);
                System.out.println("-------------------------------------------------");
                System.out.println("-------------------------------------------------");
                System.out.println("-Affichage de la resultat du recherche apres la selection: -");

                // Afficher les résultats du matching avec le Selectionneur
                // resultat = selectionneur.selectionner(resultat);
                listeNoms4 = selectionneur.selectionner(listeNoms4);
                for (CoupleDenomAvecScore couple : listeNoms4) {
                    System.out.println("Nom1: " + couple.getCouple().getNom() + " Nom2: " + couple.getCouple().getNom2()
                            + ", Score: " + couple.getScore());
                }

            }
        }
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
        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur, generateur);

        System.out.println("-------------------Matching_App------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-La Comparaison entre les noms est en cours-");
        // Appeler la méthode de test pour ComparateurExact
        List<CoupleDenomAvecScore> listeNoms4 = moteur.comparer(listeNoms3, listeNoms3);

        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-La recherche entre les noms est en cours-");

        // Appeler la méthode de test pour effectuer le matching
        // List<CoupleDenomAvecScore> resultat = moteur.rechercher(listeNoms3);
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-Affichage de la resultat du recherche apres la selection: -");

        // Afficher les résultats du matching avec le Selectionneur
        // resultat = selectionneur.selectionner(resultat);
        listeNoms4 = selectionneur.selectionner(listeNoms4);
        for (CoupleDenomAvecScore couple : listeNoms4) {
            System.out.println("Nom1: " + couple.getCouple().getNom() + " Nom2: " + couple.getCouple().getNom2()
                    + ", Score: " + couple.getScore());
        }

        // affichage:
        // for (CoupleDenomAvecScore couple : resultat) {
        // System.out.println("Nom: " + couple.getCouple().getNom() + ", Score: " +
        // couple.getScore());
        // }
    }
}
