
//bsh njarab n9asamha l les methodes as8ar bsh tet9ra 5yr ama naarash te5osh w9t akthar wlla A9AL
import java.util.ArrayList;
import java.util.List;

public class PretraiteurDeNormalisationV2 implements Pretraiteur {

    private List<Nom> nomPretraited = new ArrayList<>();

    @Override
    public List<Nom> nettoyer(List<Nom> noms) {
        for (Nom nom : noms) {
            String valeur = extraireNom(nom);
            String nettoye = nettoyerTexte(valeur);
            nom.setNom(nettoye);
            nomPretraited.add(nom);
        }
        return nomPretraited;
    }

    // 🔹 Extract the name value from the object
    private String extraireNom(Nom nom) {
        return nom.getNom();
    }

    // 🔹 Normalize the text: trim, lowercase, remove spaces
    private String nettoyerTexte(String texte) {
        String sansEspaces = supprimerEspaces(texte);
        return convertirEnMinuscule(sansEspaces);
    }

    // 🔹 Convert to lowercase
    private String convertirEnMinuscule(String texte) {
        return texte.toLowerCase();
    }

    // 🔹 Remove leading/trailing spaces and compress internal spaces
    private String supprimerEspaces(String texte) {
        return texte.trim().replaceAll("\\s+", "");
    }
}
