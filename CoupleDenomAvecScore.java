
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

    public String toString() {
        return "CoupleDenomAvecScore { couple = " + couplenom.getNom1()+"-"+couplenom.getNom1() + ", score = " + score + " }";
    }

    public char[] length() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'length'");
    }

	public String size() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'size'");
	}

}
