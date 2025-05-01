public class ComparateurNoms_simple implements ComparateurNoms {
    Comparateur comparateur = new ComparateurExact();

    @Override
    public double comparer(Nom nom1, Nom nom2) {
        // Comparaison simple basée sur l'égalité des noms

        if (nom1 != null && nom2 != null) {
            String nom1Str = nom1.getNom();
            String nom2Str = nom2.getNom();
            return comparateur.comparer(nom1Str, nom2Str);

        } else {
            return 0.0; // Si l'un des noms est null, retourner 0.0
        }
    }

}
