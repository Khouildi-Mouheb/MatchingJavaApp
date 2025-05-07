import java.util.ArrayList;
import java.util.List;

public class GenerateurParTaille implements GenerateurDeCondidat {
    public static int marge = 0;

    @Override
    public List<Nom> genererCondidat(Nom nomref, List<Nom> listeNoms) {
        int tailleRef = nomref.getNom().length();
        List<Nom> candidats = new ArrayList<>();

        for (Nom nom : listeNoms) {
            if (nom.getNom().length() == tailleRef || nom.getNom().length() <= tailleRef + marge) {
                candidats.add(nom);
            }
        }

        return candidats;
    }

    public static void setMargeDeGeneration(int mrg) {
        GenerateurParTaille.marge = mrg;
    }

}
