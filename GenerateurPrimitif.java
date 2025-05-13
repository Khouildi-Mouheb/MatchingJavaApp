
import java.util.ArrayList;
import java.util.List;

public class GenerateurPrimitif implements GenerateurDeCondidat {
    @Override
    public List<CoupleDeNom> genererCondidat(List<Nom> nomref, List<Nom> listeNoms) {
    List<CoupleDeNom> candidats = new ArrayList<>();
    if (!nomref.isEmpty()) {
        Nom nom1 = nomref.get(0); // Assuming it's a single search name
        for (Nom nom2 : listeNoms) {
            if (!nom1.equals(nom2)) { // Avoid adding self-matching pairs
                candidats.add(new CoupleDeNom(nom1, nom2));
            }
        }
    }
    return candidats;
}


}
