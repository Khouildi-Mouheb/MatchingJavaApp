
import org.apache.commons.text.similarity.JaroWinklerSimilarity;

public class ComparateurJaroWinkler implements Comparateur {

    @Override
    public double comparer(String nom1, String nom2) {
        if (nom1 != null && nom2 != null) {
            JaroWinklerSimilarity jw = new JaroWinklerSimilarity();
            return jw.apply(nom1, nom2); // Compare names
        } else
            return 0.0; // Handle null values
    }
}
