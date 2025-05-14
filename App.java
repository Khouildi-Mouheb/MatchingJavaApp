import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        RecuperateurCSV recuperateur = new RecuperateurCSV(
                "C:\\\\Users\\\\win10\\\\OneDrive\\\\Bureau\\\\work\\\\ENIT\\\\JAVA\\\\peps_names_1k.csv");
        RecuperateurCSV recuperateur1 = new RecuperateurCSV(
                "C:\\\\Users\\\\win10\\\\OneDrive\\\\Bureau\\\\work\\\\ENIT\\\\JAVA\\\\peps_names_4k.csv");

        // Création des composants du moteur
        PretraiteurNormalisation pretraiteur = new PretraiteurNormalisation();
        GenerateurPrimitif generateur = new GenerateurPrimitif();
        Comparateur comparateur = new ComparateurLevenshtein();
        ComparateurNoms comparateurNoms = new ComparateurNomsSimple(comparateur);
        SelectionneurParSeuil selectionneur = new SelectionneurParSeuilSimple(0.5);

        // Initialisation du moteur de matching
        MoteurDeMatchingMouheb moteur = new MoteurDeMatchingMouheb(pretraiteur, generateur, comparateurNoms,
                selectionneur);

        // Création d'un nom à rechercher
        Nom nomARechercher = new Nom("khouildi mouheb");

        // Création de la liste des noms candidats
        List<Nom> listeNoms = recuperateur.importData();
        List<Nom> listeNoms1 = recuperateur1.importData();

        // Exécution de la recherche
        List<CoupleDenomAvecScore> resultats = moteur.rechercherUnNomDansUneListe(nomARechercher, listeNoms1);
        // Affichage des résultats
        // Affichage des résultats
        System.out.println("---------------------Résultats de la recherche (score > 0.9) ----------------------");
        if (resultats.size() > 0) { // Utilisation de size() au lieu de length()
            for (CoupleDenomAvecScore resultat : resultats) {
                System.out.println(resultat.toString());
            }
        } else {
            System.out.println("Name not found");
        }
        resultats = moteur.comparer(listeNoms.subList(0, 100), listeNoms1);
        resultats = selectionneur.selectionner(resultats);
        System.out.println("---------------------Résultats de la recherche (score > 0.9) ----------------------");
        if (resultats.size() > 0) { // Utilisation de size() au lieu de length()
            for (CoupleDenomAvecScore resultat : resultats) {
                System.out.println(resultat.toString());
            }
        } else {
            System.out.println("Name not found");
        }
    }
}
