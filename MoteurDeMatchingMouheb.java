
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatchingMouheb {
    private Recuperateur recuperateur;
    private Pretraiteur pretraiteur;
    private GenerateurDeCondidat generateur;
    private Comparateur comparateur;

    public MoteurDeMatchingMouheb(Recuperateur recuperateur, Pretraiteur pretraiteur, GenerateurDeCondidat generateur,
            Comparateur comparateur) {
        this.recuperateur = recuperateur;
        this.pretraiteur = pretraiteur;
        this.generateur = generateur;
        this.comparateur = comparateur;
    }

    public void executerMatching() {

        List<Nom> tousLeNomsId = recuperateur.importData();

        List<Nom> tousLeNoms = new ArrayList<>();
        for (Nom nomOriginal : tousLeNomsId) {
            Nom nouveauNom = new Nom(nomOriginal.getNom());
            tousLeNoms.add(nouveauNom);
        }

        List<Nom> nomsPretraite = pretraiteur.nettoyer(tousLeNoms);
        System.out.println("--------------------------------------------------------");
        System.out.println("List of Names (Preprocessed):");
        for (Nom nom : nomsPretraite) {
            System.out.println(nom.getNom()); // ✅ Extract and print only the name
        }

        List<Nom> nomsRef = new ArrayList<>();
        nomsRef.add(new Nom("tankokyam"));

        List<CoupleDeNom> candidats = generateur.genererCondidat(nomsRef, nomsPretraite);
        System.out.println("--------------------------------------------------------");
        System.out.println("List of Candidates:");
        for (CoupleDeNom couple : candidats) {
            System.out.println(couple.getNom1().getNom() + " - " + couple.getNom2().getNom());
        }

        System.out.println("--------------------------------------------------------");
        int resultat = rechercherExact(candidats);
        System.out.println("Exact match found? " + (resultat == 1 ? "Yes" : "No"));
    }

    public int rechercherExact(List<CoupleDeNom> candidats) {
        for (CoupleDeNom couple : candidats) {
            if (comparateur.comparer(couple.getNom1().getNom(), couple.getNom2().getNom()) == 1.0) {
                return 1;
            }
        }
        return 0;
    }

    public void afficherNomsSansId() {
        List<Nom> tousLeNomsId = recuperateur.importData(); // Load names with IDs
        System.out.println("--------------------------------------------------------");
        System.out.println("🔹 List of Names (Without IDs):");
        for (Nom nom : tousLeNomsId) {
            System.out.println(nom.getNom()); // ✅ Extract and print only the name
        }
    }
}
