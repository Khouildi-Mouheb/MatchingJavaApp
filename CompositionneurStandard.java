
import java.util.ArrayList;
import java.util.List;

public class CompositionneurStandard implements CompositionneurDeNom {

    @Override
    public List<Nom> genererComposition(Nom nom, List<Nom> nomsExistants) {
        List<Nom> result = new ArrayList<>();
        String[] words = nom.getNom().split(" ");
        compositionner(words, result);

        return result;
    }

    private static List<Nom> compositionner(String[] mots, List<Nom> result) {
        String[] words = mots.clone();
        words = swap(words, 0, mots.length - 1);
        result.add(new Nom("", String.join(" ", words)));

        while (words != mots) {
            for (int i = mots.length - 1; i >= 0; i--) {
                words = swap(words, 0, i);
                result.add(new Nom("", String.join(" ", words)));

            }

        }
        return result;

    }

    // deux méthodes statiques importées pour générer les permutations
    /*
     * private static void permute(String[] words, int start, List<Nom> result) {
     * Nom nom = new Nom("", "");
     * if (start == words.length) {
     * nom.setNom(String.join(" ", words));
     * result.add(nom);
     * return;
     * }
     * 
     * for (int i = start; i < words.length; i++) {
     * swap(words, start, i);
     * permute(words, start + 1, result);
     * swap(words, start, i);
     * }
     * }
     */

    private static String[] swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
        return words;
    }

}
