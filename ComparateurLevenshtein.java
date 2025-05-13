//Compte le nombre de modifications minimales (insertion, suppression, substitution) nécessaires pour transformer une chaîne en une autre.
//Utilisé pour la correction orthographique et la détection de variations.

//using AI
public class ComparateurLevenshtein implements Comparateur {

    @Override
    public double comparer(String nom1Nettoye, String nom2Nettoye) {
        int len1 = nom1Nettoye.length();
        int len2 = nom2Nettoye.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Initialisation de la matrice
        for (int i = 0; i <= len1; i++)
            dp[i][0] = i;
        for (int j = 0; j <= len2; j++)
            dp[0][j] = j;

        // Calcul de la distance de Levenshtein
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (nom1Nettoye.charAt(i - 1) == nom2Nettoye.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(
                        dp[i - 1][j] + 1, // Suppression
                        dp[i][j - 1] + 1), // Insertion
                        dp[i - 1][j - 1] + cost); // Substitution
            }
        }

        // Retourne la distance de Levenshtein normalisée
        return 1 - ((double) dp[len1][len2] / Math.max(len1, len2));
    }

}
