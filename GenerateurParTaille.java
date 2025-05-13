
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurParTaille implements GenerateurDeCondidat {
    private static int marge = 2;

    @Override
    public List<CoupleDeNom> genererCondidat(Nom nomref, List<Nom> listeNoms) {
        int tailleRef = nomref.getNom().length();
        List<CoupleDeNom> candidats = new ArrayList<>();
        Map<Double, List<CoupleDeNom>> map = new HashMap<>();

        for (Nom nom : listeNoms) {

            if ((nom.getNom().length() == tailleRef || nom.getNom().length() <= tailleRef + marge)) {
                candidats.add(new CoupleDeNom(nomref, nom));
                map.put((double) nom.getNom().length(), List.of());

            }
        }

        return candidats;
    }

    public static void setMargeDeGeneration(int mrg) {
        GenerateurParTaille.marge = mrg;
    }

}
