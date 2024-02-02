package gestionnaire_de_contacts_web_v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionnaireAppareil {

    private ArrayList<Appareil> appareils;
    private Connection maConnection = null;
	private int userId;

    public GestionnaireAppareil(Connection maConnection,int userId) {
    	this.maConnection = maConnection;
        this.userId = userId;
        this.importerLesAppareils(); 
    }
    public void ajouterUnAppareil(int id, String nom, int largeur, int hauteur) {
        appareils.add(new Appareil(id, nom, largeur, hauteur));
    }
    public void supprimerUnAppareil(int id) throws SQLException {
        try (PreparedStatement preparedStatement = maConnection.prepareStatement("DELETE FROM appareils WHERE id = ?")) {
        	preparedStatement.setInt(1, id);
        	preparedStatement.executeUpdate();
	        importerLesAppareils();
        } catch (SQLException e) {
            System.out.println("exception 31");
            e.printStackTrace();
        }
    }
    public void modifierUnAppareil(int id, String nouveauNom, int nouvelleHauteur, int nouvelleLargeur) throws SQLException {
        try (PreparedStatement preparedStatement = this.maConnection.prepareStatement("UPDATE appareils SET nom = ?, hauteur = ?, largeur = ? WHERE id = ?")) {
            preparedStatement.setString(1, nouveauNom);
            preparedStatement.setInt(2, nouvelleHauteur);
            preparedStatement.setInt(3, nouvelleLargeur);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("exception 51");
            e.printStackTrace();
        }
        importerLesAppareils();
    }
    public void importerLesAppareils() {
        this.appareils = new ArrayList<Appareil>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "135246AETZRY");
			this.importerLesAppareilsSuite();
		}
		catch(Exception e){
            System.out.println("exception 4");
			e.printStackTrace();
		}
	}  
    private void importerLesAppareilsSuite() throws SQLException {
		PreparedStatement preparedStatement = this.maConnection.prepareStatement("SELECT * FROM appareils WHERE user_id = ?");
		preparedStatement.setInt(1, this.userId);
		ResultSet monResultSet = preparedStatement.executeQuery();
	    while (monResultSet.next()) {
	    	this.ajouterUnAppareil(monResultSet.getInt("id"), monResultSet.getString("nom"), monResultSet.getInt("largeur"), monResultSet.getInt("hauteur"));
	    }
	    monResultSet.close();
	}
    public void exporterUnAppareil(String nom, int hauteur, int largeur) {
    	Appareil nouveauAppareil = new Appareil(this.appareils.size()+1, nom, largeur, hauteur);
        try {
        	exporterUnAppareilsuite(nouveauAppareil);
        } catch (Exception e) {
            System.out.println("exception 49");
            e.printStackTrace();
        }
    }
    private void exporterUnAppareilsuite(Appareil nouveauAppareil) throws SQLException {
    	try (PreparedStatement preparedStatement = this.maConnection.prepareStatement("INSERT INTO appareils (nom, largeur, hauteur, user_id) VALUES (?, ?, ?, ?)")) {
    	    preparedStatement.setString(1, nouveauAppareil.getNom());
    	    preparedStatement.setInt(2, nouveauAppareil.getLargeur());
    	    preparedStatement.setInt(3, nouveauAppareil.getHauteur());
        	preparedStatement.setInt(4, userId);
    	    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
            System.out.println("exception 41");
            e.printStackTrace();
        }
        importerLesAppareils();
    }
    public Appareil getById(int id) {
        for (Appareil appareil : appareils) {
            if (appareil.getId() == id) {
                return appareil;
            }
        }
        return null; 
    }
	public ArrayList<Appareil> getAppareils() {
		return appareils;
	}
	public void setAppareils(ArrayList<Appareil> appareils) {
		this.appareils = appareils;
	}
}
