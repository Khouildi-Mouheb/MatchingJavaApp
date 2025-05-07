import java.util.List;

public interface GenerateurDeCandidat {
    public List<Nom> genererCandidats(Nom nom, List<Nom> nomsExistants);
}
