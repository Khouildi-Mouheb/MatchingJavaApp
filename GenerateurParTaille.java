
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurParTaille implements GenerateurDeCondidat {
    private static int marge = 4;

    @Override
    public List<CoupleDeNom> genererCondidat(List<Nom> nomref, List<Nom> listeNoms) {
        int tailleRef = nomref.get(0).getNom().length();
        List<CoupleDeNom> candidats = new ArrayList<>();

        for (Nom nom : listeNoms) {

            String[] nom1 = nom.getNom().split(" ");
            for (String n : nom1) {

            }

            if ((nom.getNom().length() == tailleRef || nom.getNom().length() <= tailleRef + marge)) {
                candidats.add(new CoupleDeNom(nomref.get(0), nom));

            }
        }

        return candidats;
    }

    public static void setMargeDeGeneration(int mrg) {
        GenerateurParTaille.marge = mrg;
    }

    public List<CoupleDeNom> genererCondidat(Nom nomref, List<Nom> listeNoms) {
        int tailleRef = nomref.getNom().length();
        List<CoupleDeNom> candidats = new ArrayList<>();
        Map<Double, List<CoupleDeNom>> map = new HashMap<>();

        for (Nom nom : listeNoms) {
            if ((nom.getNom().length() == tailleRef || nom.getNom().length() <= tailleRef + marge)) {
                CoupleDeNom couple = new CoupleDeNom(nomref, nom);
                candidats.add(couple);

                // Stockage dans la Map pour organisation par taille
                map.computeIfAbsent((double) nom.getNom().length(), k -> new ArrayList<>()).add(couple);
            }
        }

        return candidats;
    }

}
