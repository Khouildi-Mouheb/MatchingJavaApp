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
        Comparateur comparateurExact = new ComparateurExact();
        ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurExact);

        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur, generateur);

        Recuperateur recuperateur = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_1k.csv");

        List<Nom> listeNoms1 = recuperateur.importData();

    }
}