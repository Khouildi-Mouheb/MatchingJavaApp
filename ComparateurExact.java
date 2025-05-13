
public class ComparateurExact implements Comparateur {
    @Override
    public double comparer(String nom1, String nom2) {
        if (nom1 != null && nom2 != null) {
            return nom1.equals(nom2) ? 1.0 : 0.0;
        } else {
            return 0.0;
        }
    }

    public CoupleDenomAvecScore comparer(CoupleDeNom coupleDeNomAComparer) {
    Nom nom1 = coupleDeNomAComparer.getNom1();
     Nom nom2 = coupleDeNomAComparer.getNom2();
    if (nom1 == null || nom2 == null) {
        CoupleDeNom couple = new CoupleDeNom(null, null);
        return new CoupleDenomAvecScore(couple, 0.0); 
    }
    double score = nom1.getNom().equals(nom2.getNom()) ? 1.0 : 0.0;
    CoupleDeNom couple = new CoupleDeNom(nom1, nom2);
    return new CoupleDenomAvecScore(couple, score);
}


}
