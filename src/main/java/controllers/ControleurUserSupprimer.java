package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionnaire_de_contacts_web_v1.GestionnaireUser;

/**
 * Servlet implementation class ControleurUserSupprimer
 */
@WebServlet("/PageUserSupprimer")
public class ControleurUserSupprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurUserSupprimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GestionnaireUser gestionnaireUser = (GestionnaireUser) request.getSession().getAttribute("gestionnaireUser");
        if (gestionnaireUser == null) {
            this.getServletContext().getRequestDispatcher("/PageUserPrincipale.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("id") != null) {
	        String id = request.getParameter("id").toString();
        	try {
        		gestionnaireUser.supprimerUnUser(Integer.parseInt(id));
			} 
        	catch (NumberFormatException e) { System.out.println("54545"); e.printStackTrace();}
			catch (SQLException e) { System.out.println("88888"); e.printStackTrace();}
        }
		
        this.getServletContext().getRequestDispatcher("/PageUserPrincipale").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
