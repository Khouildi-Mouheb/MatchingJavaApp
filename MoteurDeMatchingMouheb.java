import java.util.ArrayList;
import java.util.List;


public class MoteurDeMatchingMouheb {
    // recuperation de donnees

    // instance d'un recuperateur csv
    private RecuperateurCSV recuperateur;
    // les noms sont dans la liste
    List<Nom> listeNoms;

    public MoteurDeMatchingMouheb() {
        recuperateur = new RecuperateurCSV("C:\\Users\\khoui\\OneDrive\\Desktop\\peps_names_1k.csv");
        listeNoms = recuperateur.importData();
    }

    public void affichage(List<Nom> listeNoms) {
        for (Nom nom : listeNoms) {
            System.out.println("Nom: " + nom.getNom() + ", id: " + nom.getId());
        }
    }

}
