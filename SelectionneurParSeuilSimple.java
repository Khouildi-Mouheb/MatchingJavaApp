import java.util.List; // Import List for handling the list of 'Nom'

public class SelectionneurParSeuilSimple extends SelectionneurParSeuil {

    public SelectionneurParSeuilSimple(double seuil) {
        super(seuil);
    }

    @Override
    public Nom selectionner(List<Nom> candidats) {
        // Implémentation simple : on sélectionne le premier nom si le seuil est respecté
        if (seuil > 0.5 && !candidats.isEmpty()) {
            return candidats.get(0); // Sélectionne le premier nom (exemple simple)
        }
        return null; // Retourne null si aucune condition n'est remplie
    }
}
