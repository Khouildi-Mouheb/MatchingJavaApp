import java.util.List ;
public abstract class SelectionneurParSeuil implements Selectionneur {
    protected double seuil;

    public SelectionneurParSeuil(double seuil) {
        this.seuil = seuil;
    }

    // Abstract method, must be implemented by subclasses
    @Override
    public abstract Nom selectionner(List<Nom> candidats);
}
