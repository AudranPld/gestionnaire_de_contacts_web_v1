
package gestionnaire_de_contacts_web_v1;

public class User {
	private int id;
    private String email;
    private String password;

    public User() {
    }

    public User(int id, String email, String password) {
    	this.id = id;
        this.email = email;
        this.password = password;
    }
    
    public String toString() {
        return "(" + this.id + ") " + this.email + ", mdp: " + this.password;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
