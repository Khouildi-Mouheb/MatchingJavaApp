import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        RecuperateurCSV recuperateur = new RecuperateurCSV("C:\\Users\\khoui\\OneDrive\\Desktop\\peps_names_100.csv");
        
        // Création des composants du moteur
        PretraiteurNormalisation pretraiteur = new PretraiteurNormalisation();
        GenerateurParTaille generateur = new GenerateurParTaille();
        ComparateurExact comparateur = new ComparateurExact();
        //SelectionneurParSeuil selectionneur = new SelectionneurParSeuil();

        // Initialisation du moteur de matching
        MoteurDeMatchingMouheb moteur = new MoteurDeMatchingMouheb(pretraiteur, generateur, comparateur);

        // Création d'un nom à rechercher
        Nom nomARechercher = new Nom("demidovichvasilij");

        // Création de la liste des noms candidats
        List<Nom> listeNoms = recuperateur.importData();

        // Exécution de la recherche
        List<CoupleDenomAvecScore> resultats = moteur.rechercherUnNomDansUneListe(nomARechercher, listeNoms);

        // Affichage des résultats
       // Affichage des résultats
        System.out.println("---------------------Résultats de la recherche (score > 0.9) ----------------------");
        if (resultats.size() > 0) {  // Utilisation de size() au lieu de length()
            for (CoupleDenomAvecScore resultat : resultats) {
                System.out.println(resultat.toString());
            }
        } else {
            System.out.println("Name not found");
        }
    }
}

    

