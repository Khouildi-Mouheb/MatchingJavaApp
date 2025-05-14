
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurParTaille implements GenerateurDeCondidat {
    private static int marge = 2;

    @Override
    public List<CoupleDeNom> genererCondidat(Nom nomref, List<Nom> listeNoms) {
        int tailleRef = nomref.getNom().length();

        Map<Nom, List<CoupleDeNom>> map = new HashMap<>();

        for (Nom nom : listeNoms) {
            List<CoupleDeNom> candidats = new ArrayList<>();
            String[] words = nom.getNom().split("\\S+");

            if ((nomref.getNom().split("\\S+").length == words.length)
                    && (nom.getNom().length() >= tailleRef - marge && nom.getNom().length() <= tailleRef + marge)) {
                candidats.add(new CoupleDeNom(nomref, nom));

            }
            map.putIfAbsent(nomref, candidats);

        }
        getListeDesCondidats(map);

        return getListeDesCondidats(map);

    }

    public List<CoupleDeNom> getListeDesCondidats(Map<Nom, List<CoupleDeNom>> candidatsParSyllabe) {
        List<CoupleDeNom> listeCondidats = new ArrayList<>();
        for (List<CoupleDeNom> candidats : candidatsParSyllabe.values()) {
            listeCondidats.addAll(candidats);
        }
        return listeCondidats;
    }

    public static void setMargeDeGeneration(int mrg) {
        GenerateurParTaille.marge = mrg;
    }

}
