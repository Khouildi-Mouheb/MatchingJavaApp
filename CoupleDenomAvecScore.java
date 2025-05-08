public class CoupleDenomAvecScore {
    private CoupleDeNom couplenom;
    private double score;

    public CoupleDenomAvecScore(CoupleDeNom couplenom, double score) {
        this.couplenom = couplenom;
        this.score = score;
    }

    public CoupleDeNom getCouple() {
        return couplenom;
    }

    public double getScore() {
        return score;
    }

}
