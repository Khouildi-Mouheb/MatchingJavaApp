import java.util.List;

public class MoteurDeMatching {
    // Composition : comparateur est créé directement ici
    private ComparateurNoms comparateurDeNom;
    private GenerateurDeCondidat generateur;

    // Agrégation : prétraiteur et selectionneur sont passés en argument du
    // constructeur
    private Pretraiteur pretraiteur;

    private List<Nom> listeNom = new java.util.ArrayList<>();

    // Constructeur
    public MoteurDeMatching(Pretraiteur pretraiteur, ComparateurNoms comparateurDeNom,
            GenerateurDeCondidat generateur) {

        this.generateur = generateur;
        this.pretraiteur = pretraiteur;
        this.comparateurDeNom = comparateurDeNom;
        // ou une autre implémentation selon ton choix
    }

    // Méthode pour effectuer le matching
    public List<CoupleDenomAvecScore> rechercher(Nom nomCible, List<Nom> listeNoms) {

        List<CoupleDenomAvecScore> m = new java.util.ArrayList<>();
        // Parcourir la liste des noms pour trouver le meilleur match

        for (Nom nom : listeNoms) {

            double score = this.comparateurDeNom.comparer(nomCible, nom);
            m.add(new CoupleDenomAvecScore(new CoupleDeNom(nomCible, nom), score));

        }
        return m;
    }

    public List<Nom> Dedupliquer(List<Nom> listNoms, Selectionneur selectionneur) {
        for (Nom nom : listNoms) {
            List<CoupleDenomAvecScore> listedesnomtrouves = this.rechercher(nom, listNoms);
            listedesnomtrouves = selectionneur.selectionner(listedesnomtrouves);
            if (listedesnomtrouves.size() > 0) {
                for (CoupleDenomAvecScore couple : listedesnomtrouves) {
                    listNoms.remove(couple.getCouple().getNom1());
                }
            }

        }
        return listeNom;
    }

    // Méthode pour tester le Comparateur
    public List<CoupleDenomAvecScore> comparer(List<Nom> listNoms2, List<Nom> listNoms) {
        listNoms2 = this.pretraiteur.nettoyer(listNoms2);
        listNoms = this.pretraiteur.nettoyer(listNoms);
        List<CoupleDeNom> temp = new java.util.ArrayList<>();
        List<CoupleDenomAvecScore> temp2 = new java.util.ArrayList<>();
        // Map<Nom, Double> resultat;
        // double somme = 0.0;
        double score = 0.0;
        for (Nom nom : listNoms2) {

            if (generateur != null) {
                List<Nom> nomAgenerer = new java.util.ArrayList<>();
                nomAgenerer.add(nom);
                temp = this.generateur.genererCondidat(nomAgenerer, listNoms);
            }
            score = 0.0;
            for (CoupleDeNom couple : temp) {
                if (this.comparateurDeNom.comparer(couple.getNom1(), couple.getNom2()) > score) {
                    score = this.comparateurDeNom.comparer(couple.getNom1(), couple.getNom2());
                    temp2.add(new CoupleDenomAvecScore(new CoupleDeNom(couple.getNom1(), couple.getNom2()), score));

                }

            }

        }
        return temp2;
    }
}