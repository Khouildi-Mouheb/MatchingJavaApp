import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();
        Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.0);
        GenerateurDeCondidat generateur = new GenerateurParTaille();
        Comparateur comparateurExact = new ComparateurExact();
        Comparateur comparateurLv = new ComparateurLevenshtein();
        CompositionneurDeNom compositionneur = new CompositionneurStandard();
        ComparateurNoms comparateur = new ComparateurNomsSimple(comparateurLv);
        ComparateurNoms comparateurComposition = new ComparteurNomsParComposition(compositionneur, comparateurExact);

        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur, generateur);

        Recuperateur recuperateur = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_1k.csv");
        Recuperateur recuperateur2 = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_1k.csv");

        List<Nom> listeNoms1 = recuperateur.importData();
        List<Nom> listeNoms2 = recuperateur2.importData();

        System.out.println("-------------------------------------------------");
        List<CoupleDenomAvecScore> listeNoms3 = moteur.rechercher(listeNoms2.get(0), listeNoms1);
        int i = 0;
        for (CoupleDenomAvecScore couple : listeNoms3) {

            System.out.println(
                    couple.getCouple().getNom1() + " : " + couple.getCouple().getNom2() + " " + couple.getScore() + i);
            i++;
        }

    }

}
