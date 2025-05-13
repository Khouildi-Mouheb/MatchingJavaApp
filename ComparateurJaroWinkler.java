//Mesure la similarité phonétique et orthographique, favorisant les préfixes communs.
//
//Très utilisé dans la recherche de noms, matching de bases de données, et reconnaissance vocale.

//using AI

public class ComparateurJaroWinkler implements Comparateur  {

    private static final double SCALING_FACTOR = 0.1;

    @Override
    public double comparer(String nom1Nettoye, String nom2Nettoye) {
        if (nom1Nettoye.equals(nom2Nettoye)) return 1.0;

        int[] matchInfo = matches(nom1Nettoye, nom2Nettoye);
        int matches = matchInfo[0];
        int transpositions = matchInfo[1];

        if (matches == 0) return 0.0;

        double jaro = ((matches / (double) nom1Nettoye.length()) +
                (matches / (double) nom2Nettoye.length()) +
                ((matches - transpositions / 2.0) / matches)) / 3.0;

        int prefixLength = commonPrefixLength(nom1Nettoye, nom2Nettoye);
        return jaro + (prefixLength * SCALING_FACTOR * (1 - jaro));
    }

    private int[] matches(String s1, String s2) {
        int maxDist = Math.max(s1.length(), s2.length()) / 2 - 1;
        boolean[] s1Matches = new boolean[s1.length()];
        boolean[] s2Matches = new boolean[s2.length()];

        int matches = 0;
        int transpositions = 0;

        for (int i = 0; i < s1.length(); i++) {
            int start = Math.max(0, i - maxDist);
            int end = Math.min(i + maxDist + 1, s2.length());

            for (int j = start; j < end; j++) {
                if (s2Matches[j]) continue;
                if (s1.charAt(i) == s2.charAt(j)) {
                    s1Matches[i] = true;
                    s2Matches[j] = true;
                    matches++;
                    break;
                }
            }
        }

        int k = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (!s1Matches[i]) continue;
            while (!s2Matches[k]) k++;
            if (s1.charAt(i) != s2.charAt(k)) transpositions++;
            k++;
        }

        return new int[]{matches, transpositions};
    }

    private int commonPrefixLength(String s1, String s2) {
        int maxPrefixLength = 4;
        int prefixLength = 0;
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                prefixLength++;
            } else {
                break;
            }
        }
        return prefixLength;
    }


}