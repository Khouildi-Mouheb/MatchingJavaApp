
import java.util.ArrayList;
import java.util.List;

public class PretraiteurNormalisation implements Pretraiteur {
    List<Nom> nomPretraiter = new ArrayList<>();

    @Override
    public List<Nom> nettoyer(List<Nom> nom) {
        for (int i = 0; i < nom.size(); i++) {
            Nom n = nom.get(i);
            String valeur = n.getNom(); // pas "String nom"
            if (valeur ==null){
                continue;
            }
            String nettoye = valeur.trim().toLowerCase().replaceAll("\\s+", "");
            nomPretraiter.add(new Nom(n.getId(), nettoye));

        }
        return nomPretraiter;
    }

    public Nom nettoyer(Nom nom) {
    if (nom == null || nom.getNom() == null) {
        return null; 
    }
    String nettoye = nom.getNom().trim().toLowerCase().replaceAll("\\s+", "");
    return new Nom(nom.getId(), nettoye);
    }


}
