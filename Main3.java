import java.util.List;

public class Main3 {
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

        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, comparateur, generateur);

        Recuperateur recuperateur = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_100.csv");
        Recuperateur recuperateur2 = new RecuperateurCSV(
                "C:\\Users\\win10\\OneDrive\\Bureau\\work\\ENIT\\JAVA\\peps_names_100.csv");

        List<Nom> listeNoms1 = recuperateur.importData();
        List<Nom> listeNoms2 = recuperateur2.importData();

        System.out.println("-------------------------------------------------");
        List<Nom> listeNoms3 = moteur.dedupliquer(listeNoms2, selectionneur);
        int i = 0;
        for (Nom couple : listeNoms3) {

            System.out.println(couple.getNom() + " " + couple.getId() + i);
            i++;
        }

    }

}
