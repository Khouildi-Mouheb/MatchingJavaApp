
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatchingM {
    private Pretraiteur pretraiteur;
    private GenerateurDeCondidat generateur;
    private ComparateurNoms comparateur;
    private Selectionneur selectionneur;

    // Constructeur
    public MoteurDeMatchingM(Pretraiteur pretraiteur, GenerateurDeCondidat generateur,
            ComparateurNoms comparateur, Selectionneur selectionneur) {
        this.pretraiteur = pretraiteur;
        this.generateur = generateur;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
    }

    // le methode de recherche

    public List<CoupleDenomAvecScore> rechercher(Nom nomARechercher, List<Nom> leNoms) {
        // praitreittement de la nom a rechercher
        List<Nom> l = new ArrayList<>();
        l.add(nomARechercher);
        List<Nom> ln = pretraiteur.nettoyer(l);
        Nom nomNettoyer = ln.get(0);
        // pretraittement de la liste des noms
        List<Nom> listDesNomsAvecId = pretraiteur.nettoyer(leNoms);
        // affichage des noms apres pretraittement
        System.out.println("--------------------nom a rechercher nettoyer-------------------");
        System.out.println(nomNettoyer.getNom());
        System.out.println("--------------------liste nettoyer-------------------");

        // generation des condidats
        System.out.println("--------------------les condidats-------------------");
        List<CoupleDeNom> condidats = generateur.genererCondidat(ln.get(0), listDesNomsAvecId);

        // comparaison
        List<CoupleDenomAvecScore> resultatDeComparaison = new ArrayList<>();
        for (CoupleDeNom cond : condidats) {
            resultatDeComparaison
                    .add(new CoupleDenomAvecScore(cond, comparateur.comparer(cond.getNom1(), cond.getNom2())));
        }
        System.out.println("--------------------resultat de comparaison-------------------");
        for (CoupleDenomAvecScore cps : resultatDeComparaison) {
            System.out.println(cps.getCouple().getNom1() + "-" + cps.getCouple().getNom2() + "-" + cps.getScore());
        }

        // selection par le seuil > 0.9
        List<CoupleDenomAvecScore> resultat = selectionneur.selectionner(resultatDeComparaison);

        return resultat;

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

                temp = generateur.genererCondidat(nom, listNoms);
            }
            score = 0.0;
            for (CoupleDeNom couple : temp) {
                if (comparateur.comparer(couple.getNom1(), couple.getNom2()) > score) {
                    score = comparateur.comparer(couple.getNom1(), couple.getNom2());
                    temp2.add(new CoupleDenomAvecScore(new CoupleDeNom(couple.getNom1(), couple.getNom2()), score));

                }

            }

        }
        return temp2;
    }

}
