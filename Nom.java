
public class Nom {
    private String nom;
    private String id;

    public Nom(String id, String nom) {
        this.nom = nom;
        this.id = id;
    }

    public Nom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
