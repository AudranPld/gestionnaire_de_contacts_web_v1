package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionnaire_de_contacts_web_v1.GestionnaireAppareil;
import gestionnaire_de_contacts_web_v1.GestionnaireUser;

/**
 * Servlet implementation class ControleurAppareilPrincipal
 */
@WebServlet("/PageAppareilPrincipale")
public class ControleurAppareilPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAppareilPrincipal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    Connection maConnection = (Connection) request.getSession().getAttribute("maConnection");
        if (maConnection == null) {
    	    try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "135246AETZRY");
    			request.getSession().setAttribute("maConnection", maConnection);
    		} catch (SQLException | ClassNotFoundException e) {System.out.println("EXEEEPTION");e.printStackTrace();}
        }
        
		Integer  userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            this.getServletContext().getRequestDispatcher("/PageUserPrincipale.jsp").forward(request, response);
            return;
        }
		
		GestionnaireAppareil gestionnaireAppareil = (GestionnaireAppareil) request.getSession().getAttribute("gestionnaireAppareil");
        if (gestionnaireAppareil == null) {
        	gestionnaireAppareil = new GestionnaireAppareil(maConnection,userId);
        	request.getSession().setAttribute("gestionnaireAppareil", gestionnaireAppareil);
        }

        if (request.getParameter("nom") != null) {
	        String nom = request.getParameter("nom").toString();
	        int hauteur = Integer.parseInt(request.getParameter("hauteur"));
	        int largeur = Integer.parseInt(request.getParameter("largeur"));
	        String id = request.getParameter("id").toString();
	        if (id.equals("-1")) {
	        	if (nom.equals("")) {
		            this.getServletContext().getRequestDispatcher("/PageAppareilAjouter").forward(request, response);
		            return;
		        }
	    		gestionnaireAppareil.exporterUnAppareil(nom, hauteur, largeur);
	        }else {
	        	if (nom.equals("")) {
		            this.getServletContext().getRequestDispatcher("/PageAppareilModifier").forward(request, response);
		            return;
		        }
		        try {
		        	gestionnaireAppareil.modifierUnAppareil(Integer.parseInt(id), nom, hauteur, largeur);
				} catch (NumberFormatException | SQLException e) {
					System.out.println("ESXEPTION");
					e.printStackTrace();
				}
	        }
		}
        
        if(gestionnaireAppareil.getAppareils().size()==0) {
            this.getServletContext().getRequestDispatcher("/PageAppareilAjouter").forward(request, response);
            return;
        }
        
        this.getServletContext().getRequestDispatcher("/PageAppareilPrincipale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
