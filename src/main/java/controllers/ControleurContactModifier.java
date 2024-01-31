
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gestionnaire_de_contacts_web_v1.Contact;
import gestionnaire_de_contacts_web_v1.GestionnaireContact;

/**
 * Servlet implementation class controleurModifier
 */
@WebServlet("/PageContactModifier")
public class ControleurContactModifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurContactModifier() {
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
        String  appareilNom = (String) request.getSession().getAttribute("appareilNom");        
        if (appareilNom == null) {
            this.getServletContext().getRequestDispatcher("/PageAppareilPrincipale").forward(request, response);
            return;
        }
        
        GestionnaireContact gestionnaireContact = (GestionnaireContact) request.getSession().getAttribute("gestionnaireContact");

        request.getSession().setAttribute("nom", gestionnaireContact.getById(Integer.parseInt(request.getParameter("id"))).getNom());
        request.getSession().setAttribute("numero", gestionnaireContact.getById(Integer.parseInt(request.getParameter("id"))).getNumero());
        request.getSession().setAttribute("id", request.getParameter("id"));
		
        this.getServletContext().getRequestDispatcher("/PageContactModifier.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
