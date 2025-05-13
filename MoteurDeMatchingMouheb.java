
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatchingMouheb {
    private Pretraiteur pretraiteur;
    private GenerateurDeCondidat generateur;
    private ComparateurNoms comparateur;
    // private SelectionneurParSeuil selectionneur;

    // Constructeur
    public MoteurDeMatchingMouheb(Pretraiteur pretraiteur2, GenerateurDeCondidat generateur2,
            ComparateurNoms comparateurExact) {
        this.pretraiteur = pretraiteur2;
        this.generateur = generateur2;
        this.comparateur = comparateurExact;
        // this.selectionneur=selectionneur;
    }

    // le methode de recherche

    public List<CoupleDenomAvecScore> rechercherUnNomDansUneListe(Nom nomARechercher, List<Nom> leNoms) {
        // praitreittement
        List<Nom> nomL = new ArrayList<>();
        nomL.add(nomARechercher);
        nomL = pretraiteur.nettoyer(nomL);
        Nom nom = nomL.get(0);

        List<Nom> listDesNomsAvecId = pretraiteur.nettoyer(leNoms);
        // affichage des noms apres pretraittement
        System.out.println("--------------------nom a rechercher nettoyer-------------------");
        System.out.println(nom.getNom());
        System.out.println("--------------------liste nettoyer-------------------");
        for (Nom n : listDesNomsAvecId) {
            System.out.println(n.getNom());
        }
        // generation des condidats
        System.out.println("--------------------les condidats-------------------");

        List<CoupleDeNom> condidats = generateur.genererCondidat(nomL, listDesNomsAvecId);
        for (CoupleDeNom cp : condidats) {
            System.out.println(cp.getNom1() + "-" + cp.getNom2());
        }
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

        // selection par le score > 0.9
        List<CoupleDenomAvecScore> resultat = new ArrayList<>();
        for (CoupleDenomAvecScore cpas : resultatDeComparaison) {
            if (cpas.getScore() >= 0.9) {
                resultat.add(cpas);
            }
        }

        return resultat;

    }

}
