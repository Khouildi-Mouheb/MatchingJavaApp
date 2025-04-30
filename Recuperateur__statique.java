import java.util.ArrayList;
import java.util.List;

public class Recuperateur__statique implements Recuperateur {
    Nom nomBrut1 = new Nom(" khouildi mouheb");
    Nom nomBrut2 = new Nom("mouhebkhouildi");
    Nom nomBrut3 = new Nom("yacine boujelbane");

    @Override
    public List<Nom> recuperer() {
        // Créer une liste de noms
        List<Nom> listeNoms = new ArrayList<>();
        listeNoms.add(nomBrut1);
        listeNoms.add(nomBrut2);
        listeNoms.add(nomBrut3);

        return listeNoms;

    }

}
