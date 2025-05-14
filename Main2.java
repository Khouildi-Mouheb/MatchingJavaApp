import java.util.ArrayList;
import java.util.List;

//main class 

public class Main2 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        PretraiteurNormalisation pretraiteur = new PretraiteurNormalisation();
        GenerateurPrimitif generateur = new GenerateurPrimitif();
        SelectionneurParSeuil selectionneur = new SelectionneurParSeuilSimple(0.9);
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Comparateur comparateurExact = new ComparateurExact();
        Comparateur comparateurLv = new ComparateurLevenshtein();
        Comparateur comparateurJw = new ComparateurJaroWinkler();
        CompositionneurDeNom compositionneur = new CompositionneurStandard();
        ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurJw);

        ComparateurNoms comparateurComposition = new ComparteurNomsParComposition(compositionneur, comparateurLv);

        MoteurDeMatchingMouheb moteur1 = new MoteurDeMatchingMouheb(pretraiteur, generateur, comparateur,
                selectionneur);

        Recuperateur recuperateur = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_658k.csv");

        List<Nom> listeNoms1 = recuperateur.importData();

        System.out.println("-------------------------------------------------");
        List<CoupleDenomAvecScore> listeNoms3 = moteur1.rechercherUnNomDansUneListe(listeNoms1.get(0), listeNoms1);

        System.out.println("-------------------------------------------------");
        listeNoms3 = selectionneur.selectionner(listeNoms3);
        for (CoupleDenomAvecScore nom : listeNoms3) {
            System.out.println(nom.getCouple().getNom1() + " " + nom.getCouple().getNom2() + " " + nom.getScore());
        }
        long endTime = System.nanoTime();
        System.out.println("Temps d'exécution : " + (endTime - startTime) / 1_000_000 + " ms");

    }
}