import java.util.ArrayList;
import java.util.List;

public class GenerateurParTaille implements GenerateurDeCandidat {

    @Override
    public List<Nom> genererCandidats(Nom nomref, List<Nom> listeNoms) {
        int tailleRef = nomref.getNom().length();
        List<Nom> candidats = new ArrayList<>();

        for (Nom nom : listeNoms) {
            if (nom.getNom().length() == tailleRef) {
                candidats.add(nom);
            }
        }

        return candidats;
    }

}
