package gestionnaire_de_contacts_web_v1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionnaireUser {
    private ArrayList<User> users;
    private Connection maConnection = null;
    
    public GestionnaireUser(Connection maConnection) {
    	this.maConnection = maConnection;
        this.importerLesUsers(); 
    }
    public void ajouterUnUser(int id, String email, String password) {
        users.add(new User(id, email,password));
    }
    public void supprimerUnUser(int id) throws SQLException {
        try (PreparedStatement preparedStatement = maConnection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, id);
        	preparedStatement.executeUpdate();
	        importerLesUsers();
        } catch (SQLException e) {
            System.out.println("exception 31");
            e.printStackTrace();
        }
    }
    public void modifierUnUser(int id, String nouveauNom, String nouveauNumero) throws SQLException {
        try (PreparedStatement preparedStatement = this.maConnection.prepareStatement("UPDATE users SET email = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, nouveauNom);
            preparedStatement.setString(2, nouveauNumero);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("exception 51");
            e.printStackTrace();
        }
        importerLesUsers();
    } 
	public void importerLesUsers() {
        this.users = new ArrayList<User>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "135246AETZRY");
			this.importerLesUsersSuite();
		}
		catch(Exception e){
            System.out.println("exception 4");
			e.printStackTrace();
		}
	}  
    private void importerLesUsersSuite() throws SQLException {
    	Statement monCS = this.maConnection.createStatement();
	    ResultSet monResultSet = monCS.executeQuery("SELECT * FROM users");
	    while (monResultSet.next()) {
	    	this.ajouterUnUser(monResultSet.getInt("id"), monResultSet.getString("email"), monResultSet.getString("password"));
	    }
	    monResultSet.close();
	    monCS.close();
	}
    public void exporterUnUser(String email, String password) {
    	User nouveauUser = new User(this.users.size()+1, email, password);
        try {
        	exporterUnUsersuite(nouveauUser);
        } catch (Exception e) {
            System.out.println("exception 49");
            e.printStackTrace();
        }
    }
    private void exporterUnUsersuite(User nouveauUser) throws SQLException {
    	System.out.println(nouveauUser.getEmail());
    	System.out.println(nouveauUser.getPassword());
    	try (PreparedStatement preparedStatement = this.maConnection.prepareStatement("INSERT INTO users (email, password) VALUES (?, ?)")) {
    	    preparedStatement.setString(1, nouveauUser.getEmail());
    	    preparedStatement.setString(2, nouveauUser.getPassword());
    	    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
            System.out.println("exception 41");
            e.printStackTrace();
        }
        importerLesUsers();
    }
    public User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; 
    }
    public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}
