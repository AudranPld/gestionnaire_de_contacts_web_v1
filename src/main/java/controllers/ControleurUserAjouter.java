package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionnaire_de_contacts_web_v1.GestionnaireUser;

/**
 * Servlet implementation class ControleurUserAjouter
 */
@WebServlet("/PageUserAjouter")
public class ControleurUserAjouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurUserAjouter() {
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
	    this.getServletContext().getRequestDispatcher("/PageUserAjouter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
