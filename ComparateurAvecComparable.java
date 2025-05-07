public class ComparateurAvecComparable implements Comparable<Nom>, Comparateur {

    @Override
    public int compareTo(Nom nomAComparer) {
        if (nomAComparer != null) {
            return this.compareTo(nomAComparer);
        } else {
            return 0; // Si l'un des noms est null, retourner 0.0
        }
    }

    // how can ignore the implementation ?
    @Override
    public double comparer(String nom1, String nom2) {
        return nom1.compareTo(nom2); // Si l'un des noms est null, retourner 0.0
    }

}