import java.util.ArrayList;
import java.util.List;

//main class 

public class Main1 {
        public static void main(String[] args) {
                long startTime = System.nanoTime();
                // Créer les dépendances pour le prétraiteur et le sélectionneur
                Pretraiteur pretraiteur = new PretraiteurNormalisation();
                Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.5);
                GenerateurDeCondidat generateur = new GenerateurParTaille();
                GenerateurDeCondidat generateur1 = new GenerateurParSyllabe();
                Comparateur comparateurExact = new ComparateurExact();
                Comparateur comparateurLv = new ComparateurLevenshtein();
                Comparateur comparateurJw = new ComparateurJaroWinkler();
                CompositionneurDeNom compositionneur = new CompositionneurStandard();
                ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurJw);
                ComparateurNoms comparateurComposition = new ComparteurNomsParComposition(compositionneur,
                                comparateurExact);

                MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur, generateur1);
                MoteurDeMatchingM moteur1 = new MoteurDeMatchingM(pretraiteur, generateur, comparateur, selectionneur);

                Recuperateur recuperateur = new RecuperateurCSV(
                                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_1k.csv");
                Recuperateur recuperateur2 = new RecuperateurCSV(
                                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_1k.csv");

                List<Nom> listeNoms1 = recuperateur.importData();
                List<Nom> listeNoms2 = recuperateur2.importData();

                System.out.println("-------------------------------------------------");
                List<CoupleDenomAvecScore> listeNoms5 = new ArrayList<>();
                List<Nom> listeNoms4 = new ArrayList<>();
                int i = 0;
                int k = listeNoms1.size() / 10;
                while ((i + k) <= listeNoms1.size()) {
                        listeNoms4 = listeNoms1.subList(i, i + k);

                        if ((i + k) > listeNoms1.size()) {
                                listeNoms4 = listeNoms1.subList(i, listeNoms1.size());
                        }
                        i += k;
                        List<CoupleDenomAvecScore> listeNoms3 = moteur1.comparer(listeNoms4, listeNoms2);
                        listeNoms5.addAll(listeNoms3);
                        System.out.println(listeNoms5.size());
                        listeNoms3.clear();
                        long endTime = System.nanoTime();
                        System.out.println("Temps d'exécution : " + (endTime - startTime) / 1_000_000 + " ms");

                }
                listeNoms5 = selectionneur.selectionner(listeNoms5);
                for (CoupleDenomAvecScore cs : listeNoms5) {
                        System.out.println(cs.getCouple().getNom1() + " : " + cs.getCouple().getNom2() + " : "
                                        + cs.getScore());
                }

                long endTime = System.nanoTime();
                System.out.println("Temps d'exécution : " + (endTime - startTime) / 1_000_000 + " ms");
        }
}