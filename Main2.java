import java.util.ArrayList;
import java.util.List;

//main class 

public class Main2 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();
        Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.0);
        GenerateurDeCondidat generateur = new GenerateurParTaille();
        Comparateur comparateurExact = new ComparateurExact();
        CompositionneurDeNom compositionneur = new CompositionneurStandard();
        ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurExact);
        ComparateurNoms comparateurComposition = new ComparteurNomsParComposition(compositionneur, comparateurExact);

         MoteurDeMatchingMouheb moteur = new MoteurDeMatchingMouheb(pretraiteur, generateur, comparateur);

        Recuperateur recuperateur = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_100.csv");

        List<Nom> listeNoms1 = recuperateur.importData();
        for (Nom nom : listeNoms1) {
            System.out.println(nom.getNom());
        }
        System.out.println("-------------------------------------------------");
        List<Nom> listeNoms3 = moteur.dedupliquer(listeNoms1, selectionneur);
        for (Nom nom : listeNoms3) {
            System.out.println(nom.getNom());
        }

    }
}