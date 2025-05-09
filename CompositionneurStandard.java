import java.util.ArrayList;
import java.util.List;

public class CompositionneurStandard implements CompositionneurDeNom {

    @Override
    public List<Nom> genererComposition(Nom nom, List<Nom> nomsExistants) {
        List<Nom> result = new ArrayList<>();
        String[] words = nom.getNom().split(" ");
        permute(words, 0, result);

        return result;
    }

    // deux méthodes statiques importées pour générer les permutations

    private static void permute(String[] words, int start, List<Nom> result) {
        Nom nom = new Nom("", "");
        if (start == words.length) {
            nom.setNom(String.join(" ", words));
            result.add(nom);
            return;
        }

        for (int i = start; i < words.length; i++) {
            swap(words, start, i);
            permute(words, start + 1, result);
            swap(words, start, i);
        }
    }

    private static void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

}
