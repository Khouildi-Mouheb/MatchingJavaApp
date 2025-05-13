
import java.util.ArrayList;
import java.util.List;

public class ComparteurNomsParComposition implements ComparateurNoms {

    CompositionneurDeNom compositionneur;
    Comparateur comparateurDeChaine;

    ComparteurNomsParComposition(CompositionneurDeNom compositionneur, Comparateur comparateurDeChaine) {
        this.compositionneur = compositionneur;
        this.comparateurDeChaine = comparateurDeChaine;
    }

    List<Nom> candidats1 = new ArrayList<>();
    List<Nom> candidats2 = new ArrayList<>();

    @Override
    public double comparer(Nom nom1, Nom nom2) {
        // Comparaison simple basée sur l'égalité des noms

        if (nom1 != null && nom2 != null) {
            candidats1 = compositionneur.genererComposition(nom1, null);
            candidats2 = compositionneur.genererComposition(nom2, null);
            double maxScore = 0.0;
            for (Nom candidat1 : candidats1) {
                for (Nom candidat2 : candidats2) {
                    double score = comparateurDeChaine.comparer(candidat1.getNom(), candidat2.getNom());
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
