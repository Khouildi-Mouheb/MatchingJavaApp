
import java.util.List;

public interface Selectionneur {
    // make the returnType of the select couple nom score
    public List<CoupleDenomAvecScore> selectionner(List<CoupleDenomAvecScore> resultat);
}
