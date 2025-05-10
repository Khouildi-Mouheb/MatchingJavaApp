import java.util.ArrayList;
import java.util.List;

public class PretraiteurNormalisation implements Pretraiteur {
    List<Nom> nomPretraiter = new ArrayList<>();

    @Override
    public List<Nom> nettoyer(List<Nom> nom) {
        for (Nom n : nom) {
            String valeur = n.getNom(); // pas "String nom"
            String nettoye = valeur.trim().toLowerCase().replaceAll("\\s+", "");
            n.setNom(nettoye); // pas "nom.setNom(nettoye)"
            nomPretraiter.add(n);

        }
        return nomPretraiter;
    }

}
