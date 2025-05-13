import java.util.List;

public class MoteurDeMatching {

    private ComparateurNoms comparateurDeNom;
    private GenerateurDeCondidat generateur;
    private Pretraiteur pretraiteur;

    // Constructeur
    public MoteurDeMatching(Pretraiteur pretraiteur, ComparateurNoms comparateurDeNom,
            GenerateurDeCondidat generateur) {
        this.generateur = generateur;
        this.pretraiteur = pretraiteur;
        this.comparateurDeNom = comparateurDeNom;

    }

    public List<CoupleDenomAvecScore> rechercher(Nom nomCible, List<Nom> listeNoms) {
        List<Nom> lis = new java.util.ArrayList<>();
        lis.add(nomCible);

        List<CoupleDenomAvecScore> m = this.comparer(lis, listeNoms);

        return m;
    }

    public List<Nom> dedupliquer(List<Nom> listNoms1, Selectionneur selectionneur) {
        List<Nom> listNomsT = this.pretraiteur.nettoyer(listNoms1);
        List<Nom> listNoms = listNoms1;
        for (int i = 0; i < listNomsT.size(); i++) {
            Nom nom = listNomsT.get(i);
            System.out.println("Nom : " + nom.getNom());
            List<CoupleDenomAvecScore> listedesnomtrouves = rechercher(nom, listNomsT);
            // listedesnomtrouves = selectionneur.selectionner(listedesnomtrouves);
            if (listedesnomtrouves.size() > 0) {
                // for (CoupleDenomAvecScore couple : listedesnomtrouves) {
                // listNoms.remove(couple.getCouple().getNom1());

                // listeNoms.remove(listNoms.get(i)); // ou bien on supprime tous les possibles
                // doublons
            } //
        }
        return listNoms;

    }

    // Méthode pour tester le Comparateur
    public List<CoupleDenomAvecScore> comparer(List<Nom> listNoms0, List<Nom> listNoms) {
        listNoms0 = this.pretraiteur.nettoyer(listNoms0);
        listNoms = this.pretraiteur.nettoyer(listNoms);
        List<CoupleDeNom> temp = new java.util.ArrayList<>();
        List<CoupleDenomAvecScore> temp2 = new java.util.ArrayList<>();

        double score = 0.0;
        for (Nom nom : listNoms0) {

            if (generateur != null) {
                List<Nom> nomAgenerer = new java.util.ArrayList<>();
                nomAgenerer.add(nom);
                temp = generateur.genererCondidat(nomAgenerer, listNoms);
            }
            score = 0.0;
            for (CoupleDeNom couple : temp) {
                if (comparateurDeNom.comparer(couple.getNom1(), couple.getNom2()) > score) {
                    score = comparateurDeNom.comparer(couple.getNom1(), couple.getNom2());
                    temp2.add(new CoupleDenomAvecScore(new CoupleDeNom(couple.getNom1(), couple.getNom2()), score));

                }

            }

        }
        return temp2;
    }
}
