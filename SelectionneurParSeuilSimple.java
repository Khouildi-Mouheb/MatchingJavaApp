
// Import List for handling the list of 'Nom'
import java.util.ArrayList;
import java.util.List;

public class SelectionneurParSeuilSimple extends SelectionneurParSeuil {

    public SelectionneurParSeuilSimple(double seuil) {
        super(seuil);
    }

    @Override
    public List<CoupleDenomAvecScore> selectionner(List<CoupleDenomAvecScore> resultat) {
        // Implémentation simple : on sélectionne le premier nom si le seuil est
        // respecté
        List<CoupleDenomAvecScore> resultatSelectionne = new ArrayList<>();

        for (CoupleDenomAvecScore couple : resultat) {
            if ((couple.getScore()) >= seuil) {
                resultatSelectionne.add(couple);
            }
        }
        return resultatSelectionne;

    }

}
