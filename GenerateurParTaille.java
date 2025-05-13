
import java.util.ArrayList;
import java.util.List;

public class GenerateurParTaille implements GenerateurDeCondidat {
    private static int marge = 0;

    @Override
    public List<CoupleDeNom> genererCondidat(List<Nom> nomref, List<Nom> listeNoms) {
        int tailleRef = nomref.get(0).getNom().length();
        List<CoupleDeNom> candidats = new ArrayList<>();

        for (Nom nom : listeNoms) {

            if ((nom.getNom().length() == tailleRef || nom.getNom().length() <= tailleRef + marge)) {
                candidats.add(new CoupleDeNom(nomref.get(0), nom));
            }
        }

        return candidats;
    }

    public static void setMargeDeGeneration(int mrg) {
        GenerateurParTaille.marge = mrg;
    }

}
