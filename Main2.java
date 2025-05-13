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
        CompositionneurDeNom compositionneur = new CompositionneurStandard();
        ComparateurNoms comparateurComposition = new ComparteurNomsParComposition(compositionneur, comparateurExact);

        MoteurDeMatchingMouheb moteur1 = new MoteurDeMatchingMouheb(pretraiteur, generateur, new ComparateurExact(),
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

    }
}