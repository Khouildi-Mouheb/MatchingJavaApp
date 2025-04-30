public class PretraiteurNormalisation implements Pretraiteur {
    @Override
    public Nom nettoyer(Nom nom) {
        String valeur = nom.getNom(); // pas "String nom"
        String nettoye = valeur.trim().toLowerCase().replaceAll("\\s+", "");
        return new Nom(nettoye);
    }
}
