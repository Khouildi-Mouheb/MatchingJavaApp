
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatchingMouheb {
<<<<<<< HEAD
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
=======
    private PretraiteurNormalisation pretraiteur ;
    private GenerateurPrimitif generateur;
    private ComparateurExact comparateur;
    private SelectionneurParSeuil selectionneur;


    //Constructeur
    public MoteurDeMatchingMouheb(
    PretraiteurNormalisation pretraiteur,
    GenerateurPrimitif generateur,
    ComparateurExact comparateur,
    SelectionneurParSeuil selectionneur
    ){
        this.pretraiteur=pretraiteur;
        this.generateur=generateur;
        this.comparateur=comparateur;
        this.selectionneur=selectionneur;
>>>>>>> 18a1f65 (.......)
    }

    // le methode de recherche

<<<<<<< HEAD
    public List<CoupleDenomAvecScore> rechercherUnNomDansUneListe(Nom nomARechercher, List<Nom> leNoms) {
        // praitreittement
        List<Nom> nomL = new ArrayList<>();
        nomL.add(nomARechercher);
        nomL = pretraiteur.nettoyer(nomL);
        Nom nom = nomL.get(0);

        List<Nom> listDesNomsAvecId = pretraiteur.nettoyer(leNoms);
        // affichage des noms apres pretraittement
=======
    public List<CoupleDenomAvecScore> rechercherUnNomDansUneListe (Nom nomARechercher , List<Nom> leNoms){
        //praitreittement de la nom a rechercher
        List<Nom> l=new ArrayList<>();
        l.add(nomARechercher);
        List<Nom> ln=pretraiteur.nettoyer(l);
        Nom nomNettoyer=ln.get(0);
        //pretraittement de la liste des noms
        List <Nom> listDesNomsAvecId = pretraiteur.nettoyer(leNoms);
        //affichage des noms apres pretraittement
>>>>>>> 18a1f65 (.......)
        System.out.println("--------------------nom a rechercher nettoyer-------------------");
        System.out.println(nomNettoyer.getNom());
        System.out.println("--------------------liste nettoyer-------------------");
<<<<<<< HEAD
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
=======
        /* 
        for (Nom n : listDesNomsAvecId){
            System.out.println(n.getNom());
        }
            */
        //generation des condidats
        System.out.println("--------------------les condidats-------------------");
        List <CoupleDeNom> condidats = generateur.genererCondidat(ln, listDesNomsAvecId);
        for (CoupleDeNom cp : condidats){
            System.out.println(cp.getNom1()+"-"+cp.getNom2());
        }
            
        //comparaison
        List <CoupleDenomAvecScore> resultatDeComparaison = new ArrayList<>();
        for(CoupleDeNom cond : condidats){
            resultatDeComparaison.add(comparateur.comparer(cond));
>>>>>>> 18a1f65 (.......)
        }
        System.out.println("--------------------resultat de comparaison-------------------");
        for (CoupleDenomAvecScore cps : resultatDeComparaison) {
            System.out.println(cps.getCouple().getNom1() + "-" + cps.getCouple().getNom2() + "-" + cps.getScore());
        }

<<<<<<< HEAD
        // selection par le score > 0.9
        List<CoupleDenomAvecScore> resultat = new ArrayList<>();
        for (CoupleDenomAvecScore cpas : resultatDeComparaison) {
            if (cpas.getScore() >= 0.9) {
                resultat.add(cpas);
            }
        }
=======
        //selection par le seuil > 0.9
        List <CoupleDenomAvecScore> resultat = selectionneur.selectionner(resultatDeComparaison);
>>>>>>> 18a1f65 (.......)

        return resultat;

    }

}
