package gestionnaire_de_contacts_web_v1;

public class Appareil {
	private int id;
    private String nom;
    private int hauteur;
    private int largeur;

    public Appareil() {
    }

    public Appareil(int id, String nom, int largeur, int hauteur) {
    	this.id = id;
        this.nom = nom;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    
    public String toString() {
        return "(" + this.id + ") " + this.nom + ", " + this.largeur + "x" + this.hauteur;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
}
