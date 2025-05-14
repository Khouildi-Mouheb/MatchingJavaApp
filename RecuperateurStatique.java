
import java.util.ArrayList;
import java.util.List;

public class RecuperateurStatique implements Recuperateur {
    Nom nomBrut1 = new Nom("Nk--sdsdfjj", " khouildi mouheb");
    Nom nomBrut2 = new Nom("Nk-sdfjfgjjs", "boujelbane khouildi");
    Nom nomBrut3 = new Nom("Nksjfsjfzikf", "yacine mouheb");

    @Override
    public List<Nom> importData() {
        // Créer une liste de noms
        List<Nom> listeNoms = new ArrayList<>();
        listeNoms.add(nomBrut1);
        listeNoms.add(nomBrut2);
        listeNoms.add(nomBrut3);

        return listeNoms;

    }

}
