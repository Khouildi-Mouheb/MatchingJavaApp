public class ComparateurExact implements Comparateur {
    @Override
    public double comparer(String nom1, String nom2) {
        if (nom1 != null && nom2 != null) {
            return nom1.equals(nom2) ? 1.0 : 0.0;
        } else {
            return 0.0;
        }
    }

}
