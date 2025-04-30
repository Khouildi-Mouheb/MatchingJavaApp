import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();
        Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.5); // Exemple d'utilisation d'un seuil
        // içi j'ajoute un exemple de recuperateur qui fonctionne avec une liste static
        // qui j'ai genèré
        Recuperateur recuperateur = new Recuperateur__statique();
        // on doit ajouter une autre atribus pour le moteur qui est la liste vièrge
        List<Nom> listeNoms = recuperateur.recuperer();
        // Créer une instance du moteur de matching
        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, selectionneur);

        // Appeler la méthode de test pour ComparateurExact
        moteur.testerComparateurExact();

        // Appeler la méthode de test pour effectuer le matching
        moteur.effectuerMatching(listeNoms);
    }
}
