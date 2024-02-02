package gestionnaire_de_contacts_web_v1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionnaireContact {
    private ArrayList<Contact> contacts;
    private Connection maConnection = null;
	private int userId;

    public GestionnaireContact(Connection maConnection,int userId) {
    	this.maConnection = maConnection;
        this.userId = userId;
        this.importerLesContacts(); 
    }
    public void ajouterUnContact(int id, String nom, String numero) {
        contacts.add(new Contact(id, nom,numero));
    }
    public void supprimerUnContact(int id) throws SQLException {
        try (PreparedStatement preparedStatement = maConnection.prepareStatement("DELETE FROM contacts WHERE id = ?")) {
            preparedStatement.setInt(1, id);
        	preparedStatement.executeUpdate();
	        importerLesContacts();
        } catch (SQLException e) {
            System.out.println("exception 31");
            e.printStackTrace();
        }
    }
    public void modifierUnContact(int id, String nouveauNom, String nouveauNumero) throws SQLException {
        try (PreparedStatement preparedStatement = this.maConnection.prepareStatement("UPDATE contacts SET nom = ?, numero = ? WHERE id = ?")) {
            preparedStatement.setString(1, nouveauNom);
            preparedStatement.setString(2, nouveauNumero);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("exception 51");
            e.printStackTrace();
        }
        importerLesContacts();
    }
    public int[] rechercherUnContact(int index, String mot) {
        int[] resultats = new int[this.contacts.size()];
        int j = 0;
        
        for (int i = 0; i < this.contacts.size(); i++) {
            if ((index==1 && this.contacts.get(i).getNom().toLowerCase().contains(mot.toLowerCase())) ||
        		(index==2 && this.contacts.get(i).getNumero().toLowerCase().contains(mot.toLowerCase()))) {
                resultats[j] = i;
                j++;
            }
        }
        int[] resultats2 = new int[j];
        for(int k=0;k<j;k++) {
        	resultats2[k] = resultats[k];
        }
        return resultats2;
    }   
    public void importerLesContacts() {
        this.contacts = new ArrayList<Contact>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "135246AETZRY");
			this.importerLesContactsSuite();
		}
		catch(Exception e){
            System.out.println("exception 4");
			e.printStackTrace();
		}
	}  
    private void importerLesContactsSuite() throws SQLException {
		PreparedStatement preparedStatement = this.maConnection.prepareStatement("SELECT * FROM contacts WHERE user_id = ?");
		preparedStatement.setInt(1, this.userId);
		ResultSet monResultSet = preparedStatement.executeQuery();
	    while (monResultSet.next()) {
	    	this.ajouterUnContact(monResultSet.getInt("id"), monResultSet.getString("nom"), monResultSet.getString("numero"));
	    }
	    monResultSet.close();
	}
    public void exporterUnContact(String nom, String numero) {
    	Contact nouveauContact = new Contact(this.contacts.size()+1, nom, numero);
        try {
        	exporterUnContactsuite(nouveauContact);
        } catch (Exception e) {
            System.out.println("exception 49");
            e.printStackTrace();
        }
    }
    private void exporterUnContactsuite(Contact nouveauContact) throws SQLException {
    	try (PreparedStatement preparedStatement = this.maConnection.prepareStatement("INSERT INTO contacts (nom, numero, user_id) VALUES (?, ?, ?)")) {
    	    preparedStatement.setString(1, nouveauContact.getNom());
    	    preparedStatement.setString(2, nouveauContact.getNumero());
        	preparedStatement.setInt(3, userId);
    	    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
            System.out.println("exception 41");
            e.printStackTrace();
        }
        importerLesContacts();
    }
    public Contact getById(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null; 
    }
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
	
	
	
}
