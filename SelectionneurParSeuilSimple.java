
// Import List for handling the list of 'Nom'
import java.util.Map;

public class SelectionneurParSeuilSimple extends SelectionneurParSeuil {

    public SelectionneurParSeuilSimple(double seuil) {
        super(seuil);
    }

    @Override
    public void selectionner(Map<Nom, Double> resultat) {
        // Implémentation simple : on sélectionne le premier nom si le seuil est
        // respecté
        if (resultat != null && !resultat.isEmpty()) {
            for (Map.Entry<Nom, Double> entry : resultat.entrySet()) {
                Nom nom = entry.getKey();
                double score = entry.getValue();
                if (score > seuil) {
                    // Sélectionne le premier nom (exemple simple)
                    System.out.println("Nom sélectionné : " + nom.getNom());
                }

            }

        }

        // Sélectionne le premier nom (exemple simple)
    }

}
