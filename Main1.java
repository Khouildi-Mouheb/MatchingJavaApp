import java.util.ArrayList;
import java.util.List;

//main class 

public class Main1 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();
        Selectionneur selectionneur = new SelectionneurParSeuilSimple(90.0);
        GenerateurDeCondidat generateur = new GenerateurParSyllabe();
        Comparateur comparateurExact = new ComparateurExact();
        Comparateur comparateurLv = new ComparateurLevenshtein();
        Comparateur comparateurJw = new ComparateurJaroWinkler();
        CompositionneurDeNom compositionneur = new CompositionneurStandard();
        ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurLv);
        ComparateurNoms comparateurComposition = new ComparteurNomsParComposition(compositionneur, comparateurExact);

        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur, generateur);

        Recuperateur recuperateur = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_2k.csv");
        Recuperateur recuperateur2 = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_128k.csv");

        List<Nom> listeNoms1 = recuperateur.importData();
        List<Nom> listeNoms2 = recuperateur2.importData();

        System.out.println("-------------------------------------------------");
        List<CoupleDenomAvecScore> listeNoms3 = moteur.comparer(listeNoms1, listeNoms2);
        listeNoms3 = selectionneur.selectionner(listeNoms3);

        for (CoupleDenomAvecScore couple : listeNoms3) {
            System.out.println(couple.getCouple().getNom1() + " " + couple.getCouple().getNom2() + " "
                    + (couple.getScore() - (double) (couple.getCouple().getNom1().getNom().hashCode()
                            / 10000000)));
        }
        long endTime = System.nanoTime();
        System.out.println("Temps d'exécution : " + (endTime - startTime) / 1_000_000 + " ms");
    }
}