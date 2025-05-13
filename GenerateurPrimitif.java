
import java.util.List;

public class GenerateurPrimitif implements GenerateurDeCondidat {
    @Override
    public List<CoupleDeNom> genererCondidat(List<Nom> nomref, List<Nom> listeNoms) {
        List<CoupleDeNom> candidats = new java.util.ArrayList<>();
        for (Nom nom1 : nomref) {
            for (Nom nom2 : listeNoms) {
                candidats.add(new CoupleDeNom(nom1, nom2));
            }
        }
        // TODO Auto-generated method stub
        return candidats;
    }

}
