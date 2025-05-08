
// Import List for handling the list of 'Nom'
import java.util.Map;
import java.util.HashMap;

public class SelectionneurParSeuilSimple extends SelectionneurParSeuil {

    public SelectionneurParSeuilSimple(double seuil) {
        super(seuil);
    }

    @Override
    public Map<Nom, Double> selectionner(Map<Nom, Double> resultat) {
        // Implémentation simple : on sélectionne le premier nom si le seuil est
        // respecté
        Map<Nom, Double> resultatSelectionne = new HashMap<>();

        if (resultat != null && !resultat.isEmpty()) {
            for (Map.Entry<Nom, Double> entry : resultat.entrySet()) {
                Nom nom = entry.getKey();
                double score = entry.getValue();
                if (score > seuil) {
                    // Sélectionne le premier nom (exemple simple)
                    resultatSelectionne.put(nom, score);
                }

            }

        }
        return resultatSelectionne;

    }

}
