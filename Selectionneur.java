
import java.util.Map;

public interface Selectionneur {
    // make the returnType of the select couple nom score
    public Map<Nom, Double> selectionner(Map<Nom, Double> resultat);
}
