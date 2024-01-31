package gestionnaire_de_contacts_web_v1;

public class Contact {
	private int id;
    private String nom;
    private String numero;

    public Contact() {
    }

    public Contact(int id, String nom, String numero) {
    	this.id = id;
        this.nom = nom;
        this.numero = numero;
    }
    
    public String toString() {
        return "(" + this.id + ") " + this.nom + ", Numéro: " + this.numero;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
