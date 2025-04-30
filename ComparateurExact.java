public class ComparateurExact implements ComparateurNoms {
    @Override
    public double comparer(Nom nom1, Nom nom2) {
        return nom1.getNom().equals(nom2.getNom()) ? 1.0 : 0.0;
    }
}
