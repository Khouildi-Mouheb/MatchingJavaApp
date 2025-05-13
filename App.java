
public class App {
    public static void main(String[] args) {

        RecuperateurCSV recuperateur = new RecuperateurCSV("C:\\Users\\khoui\\OneDrive\\Desktop\\peps_names_100.csv");
        Pretraiteur pretraiteur = new PretraiteurDeNormalisationV2();
        GenerateurDeCondidat generateur = new GenerateurParTaille();
        Comparateur comparateur = new ComparateurExact();

        MoteurDeMatchingMouheb moteur = new MoteurDeMatchingMouheb(recuperateur, pretraiteur, generateur, comparateur);

        moteur.executerMatching();
        moteur.afficherNomsSansId();
    }
}
