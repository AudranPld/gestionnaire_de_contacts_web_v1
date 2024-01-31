package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionnaire_de_contacts_web_v1.GestionnaireContact;

/**
 * Servlet implementation class controleurSupprimer
 */
@WebServlet("/PageContactSupprimer")
public class ControleurContactSupprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurContactSupprimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer  userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            this.getServletContext().getRequestDispatcher("/PageUserPrincipale.jsp").forward(request, response);
            return;
        }
        
        GestionnaireContact gestionnaireContact = (GestionnaireContact) request.getSession().getAttribute("gestionnaireContact");
        if (request.getParameter("id") != null) {
	        String id = request.getParameter("id").toString();
        	try {
        		gestionnaireContact.supprimerUnContact(Integer.parseInt(id));
			} 
        	catch (NumberFormatException e) { System.out.println("54545"); e.printStackTrace();}
			catch (SQLException e) { System.out.println("88888"); e.printStackTrace();}
        }
		
        this.getServletContext().getRequestDispatcher("/PageContactPrincipale").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
