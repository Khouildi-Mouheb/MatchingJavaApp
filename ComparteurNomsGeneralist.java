import java.util.ArrayList;
import java.util.List;

public class ComparteurNomsGeneralist implements ComparateurNoms {

    Comparateur comparateur = new ComparateurExact();
    GenerateurDeCandidat generateur = new GenerateurStandard();
    List<Nom> candidats1 = new ArrayList<>();
    List<Nom> candidats2 = new ArrayList<>();

    @Override
    public double comparer(Nom nom1, Nom nom2) {
        // Comparaison simple basée sur l'égalité des noms

        if (nom1 != null && nom2 != null) {
            candidats1 = generateur.genererCandidats(nom1);
            candidats2 = generateur.genererCandidats(nom2);
            double maxScore = 0.0;
            for (Nom candidat1 : candidats1) {
                for (Nom candidat2 : candidats2) {
                    double score = comparer(candidat1, candidat2);
                    if (score > maxScore) {
                        maxScore = score;
                    }
                }
            }
            return maxScore;

        } else {
            return 0.0; // Si l'un des noms est null, retourner 0.0
        }
    }

}
