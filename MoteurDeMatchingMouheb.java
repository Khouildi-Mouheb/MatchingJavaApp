
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatchingMouheb {
    private Pretraiteur pretraiteur;
    private GenerateurDeCondidat generateur;
    private Comparateur comparateur;
    private ComparateurNoms comparateurNoms;
    private Selectionneur selectionneur;

    // Constructeur
    public MoteurDeMatchingMouheb(Pretraiteur pretraiteur,GenerateurDeCondidat generateur,
                                  Comparateur comparateur, Selectionneur selectionneur /*ComparateurNoms comparateurNoms*/) {
        //this.comparateurNoms = comparateurNoms;
        this.pretraiteur = pretraiteur;
        this.generateur = generateur;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
    }

    // le methode de recherche
    /*---------------------------------RECHERCHE D'UN NOM DANS UNE LISTE--------------------------------------*/
    public List<CoupleDenomAvecScore> rechercherUnNomDansUneListe(Nom nomARechercher, List<Nom> leNoms) {
        List<CoupleDeNom> condidats = generateur.genererCondidat(nomARechercher, leNoms);
        condidats.remove(0);
        if (condidats.size() == 0) {
            System.out.println("le nom n'a pas des condidats donc il n'est pas dans la liste .");
            return null;
        } else {
            System.out.println(" *******   on a "+ condidats.size() + " condidats pour le nom " + nomARechercher.getNom() +"*******");
            for (CoupleDeNom cp : condidats) {
                System.out.println(cp.getNom1().getNom() + " candidat avec " + cp.getNom2().getNom());
            }
        }
        System.out.println("----------------------------------------------------------------");
        // Pretraitement du nom à rechercher
        List<Nom> l = new ArrayList<>();
        l.add(nomARechercher);
        List<Nom> ln = pretraiteur.nettoyer(l);
        Nom nomNettoyer = ln.get(0);
        List<Nom> listeDesCondidats = new ArrayList<>();
        for (CoupleDeNom couple : condidats) {
            listeDesCondidats.add(couple.getNom2());
        }
        List<Nom> listDesNomsAcomparer = pretraiteur.nettoyer(listeDesCondidats);
        List<CoupleDenomAvecScore> resultat = new ArrayList<>();
        for (Nom nom : listDesNomsAcomparer) {
            double score = comparateur.comparer(nomNettoyer.getNom(), nom.getNom());
            CoupleDenomAvecScore coupleAvecScore = new CoupleDenomAvecScore(new CoupleDeNom(nomNettoyer, nom), score);
            resultat.add(coupleAvecScore);
        }
        listDesNomsAcomparer.remove(0);
        for(Nom nom:listDesNomsAcomparer){
            System.out.println("le score entre"+nomNettoyer.getNom()+" et "+nom.getNom()+" est : "+comparateur.comparer(nomNettoyer.getNom(), nom.getNom()) );
        }
        //selectionnement
        List<CoupleDenomAvecScore> resultatSelectionne = selectionneur.selectionner(resultat);
        resultatSelectionne.remove(0);

        if(resultatSelectionne.size()==0){
            System.out.println("aucun condidat selectionne");
        }else{
            System.out.println("----------------------------------------------------------------------");
            System.out.println("le nombre de resultat selectionnes est de : "+resultatSelectionne.size());
            for (CoupleDenomAvecScore couple : resultatSelectionne) {
                System.out.println("le score entre"+couple.getCouple().getNom1().getNom()+" et "+couple.getCouple().getNom2().getNom()+" est : "+couple.getScore() );
            }
            System.out.println("le selection donner :"+ resultatSelectionne.size());
        }
        return resultatSelectionne;
    }


    /*------------------------DEDUPMLICATION D'UNE LISTE---------------------------------------------*/
    public List<Nom> deduplication(List<Nom> listeNoms) {
        //nettoyage de la liste
        List<Nom> listeNettoyer=pretraiteur.nettoyer(listeNoms);
        //gestion des condidats
        for(Nom nom : listeNettoyer){
            List<CoupleDeNom> condidats=generateur.genererCondidat(nom,listeNettoyer);
            for(CoupleDeNom couple : condidats){
                double resultatDeComparaison=comparateur.comparer(couple.getNom1().getNom(),couple.getNom2().getNom());
                if(resultatDeComparaison>0.98){
                    listeNoms.remove(couple.getNom1());
                }
            }
        }
        return listeNoms;
    }




    /*------------------------------COMPARAISON DE DEUX LISTES-----------------------------------------------*/
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
                if (comparateur.comparer(couple.getNom1().getNom(), couple.getNom2().getNom()) > score) {
                    score = comparateur.comparer(couple.getNom1().getNom(), couple.getNom2().getNom());
                    temp2.add(new CoupleDenomAvecScore(new CoupleDeNom(couple.getNom1(), couple.getNom2()), score));

                }

            }

        }
        return temp2;
    }

}
