
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatchingMouheb {
    private PretraiteurNormalisation pretraiteur;
    private GenerateurPrimitif generateur;
    private ComparateurExact comparateur;
    private SelectionneurParSeuil selectionneur;

    // Constructeur
    public MoteurDeMatchingMouheb(PretraiteurNormalisation pretraiteur, GenerateurPrimitif generateur,
            ComparateurExact comparateur, SelectionneurParSeuil selectionneur) {
        this.pretraiteur = pretraiteur;
        this.generateur = generateur;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
    }

    // le methode de recherche

    public List<CoupleDenomAvecScore> rechercherUnNomDansUneListe(Nom nomARechercher, List<Nom> leNoms) {
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
        /*
         * for (Nom n : listDesNomsAvecId){
         * System.out.println(n.getNom());
         * }
         */
        // generation des condidats
        System.out.println("--------------------les condidats-------------------");
        List<CoupleDeNom> condidats = generateur.genererCondidat(ln.get(0), listDesNomsAvecId);
        for (CoupleDeNom cp : condidats) {
            System.out.println(cp.getNom1() + "-" + cp.getNom2());
        }

        // comparaison
        List<CoupleDenomAvecScore> resultatDeComparaison = new ArrayList<>();
        for (CoupleDeNom cond : condidats) {
            resultatDeComparaison.add(comparateur.comparer(cond));
        }
        System.out.println("--------------------resultat de comparaison-------------------");
        for (CoupleDenomAvecScore cps : resultatDeComparaison) {
            System.out.println(cps.getCouple().getNom1() + "-" + cps.getCouple().getNom2() + "-" + cps.getScore());
        }

        // selection par le seuil > 0.9
        List<CoupleDenomAvecScore> resultat = selectionneur.selectionner(resultatDeComparaison);

        return resultat;

    }

}
