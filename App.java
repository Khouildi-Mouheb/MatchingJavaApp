import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Initialisation du récupérateur CSV avec le fichier source
        RecuperateurCSV recuperateur = new RecuperateurCSV("C:\\Users\\khoui\\OneDrive\\Desktop\\peps_names_658k.csv");
        // Création des composants du moteur
        PretraiteurNormalisation pretraiteur = new PretraiteurNormalisation();
        GenerateurParSyllabe generateur = new GenerateurParSyllabe();
        ComparateurJaroWinkler comparateur = new ComparateurJaroWinkler();
        SelectionneurParSeuil selectionneur = new SelectionneurParSeuilSimple(0.0);
        // Initialisation du moteur de matching
        MoteurDeMatchingMouheb moteur = new MoteurDeMatchingMouheb(pretraiteur, generateur, comparateur, selectionneur);
        // Création d'un nom à rechercher
        Nom nomARechercher = new Nom("Kim Kum-chol");
        // Importation des noms candidats depuis le fichier CSV
        List<Nom> listeNoms = recuperateur.importData();
        // Exécution de la recherche
        List<CoupleDenomAvecScore> resultats = moteur.rechercherUnNomDansUneListe(nomARechercher, listeNoms);

        /*
        System.out.println("-----------------------------------deduplication---------------------------------------------");
        System.out.println("taille de liste avant deduplication" +listeNoms.size());
        System.out.println("----------------------------avant dedup-----------------------------------");
        for(Nom n:listeNoms){
            System.out.println(n.getNom());
        }
        List<Nom> listededupliquer =moteur.deduplication(listeNoms);
        System.out.println("----------------------------apres dedup-----------------------------------");
        System.out.println("taille de liste APRES deduplication" +listeNoms.size());
        for(Nom n:listededupliquer){
            System.out.println(n.getNom());
        }
        */

    }
}




