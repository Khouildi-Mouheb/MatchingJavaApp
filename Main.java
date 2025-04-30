public class Main {
    public static void main(String[] args) {
        // Créer les dépendances pour le prétraiteur et le sélectionneur
        Pretraiteur pretraiteur = new PretraiteurNormalisation();
        Selectionneur selectionneur = new SelectionneurParSeuilSimple(0.5); // Exemple d'utilisation d'un seuil

        // Créer une instance du moteur de matching
        MoteurDeMatching moteur = new MoteurDeMatching(pretraiteur, selectionneur);

        // Appeler la méthode de test pour ComparateurExact
        moteur.testerComparateurExact();

        // Appeler la méthode de test pour effectuer le matching
        moteur.effectuerMatching();
    }
}
