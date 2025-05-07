import java.util.List;

public interface CompositionneurDeNom {
    public List<Nom> genererComposition(Nom nom, List<Nom> nomsExistants);
}
