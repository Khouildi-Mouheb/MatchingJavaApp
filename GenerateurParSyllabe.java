import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
public class GenerateurParSyllabe implements GenerateurDeCondidat{
    private List<String> extraireSyllabes(String nom){
        return Arrays.asList(nom.toLowerCase().split(" "));
    }

    public Map<String, List<Nom>> genererCondidatAvecSyllabe(Nom nomRef, List<Nom> listeNoms) {
        Map<String, List<Nom>> condidats = new HashMap<>();
        List<String> syllabesRef = extraireSyllabes(nomRef.getNom());
        for (String syllabe : syllabesRef) {
            condidats.put(syllabe, new ArrayList<>());
        }
        for (Nom nom : listeNoms) {
            for (String syllabe : syllabesRef) {
                if (nom.getNom().contains(syllabe)) {
                    condidats.get(syllabe).add(nom);
                }
            }
        }
        return condidats;
    }


    public List<Nom> getListeDesCondidats (Map<String, List<Nom>> candidatsParSyllabe){
        List<Nom> listeCondidats = new ArrayList<>();
        for (List<Nom> candidats : candidatsParSyllabe.values()) {
            listeCondidats.addAll(candidats);
        }
        return listeCondidats;
    }


    @Override
    public List<CoupleDeNom> genererCondidat(Nom nomRef, List<Nom> listeNoms) {
        // Générer un HashMap contenant les syllabes du nomRef et les noms associés
        Map<String, List<Nom>> candidatsParSyllabe = genererCondidatAvecSyllabe(nomRef, listeNoms);

        // Convertir le HashMap en liste de CoupleDeNom
        List<CoupleDeNom> couples = new ArrayList<>();
        for (List<Nom> nomsAssocies : candidatsParSyllabe.values()) {
            for (Nom nom : nomsAssocies) {
                couples.add(new CoupleDeNom(nomRef, nom));
            }
        }

        return couples;
    }




}
