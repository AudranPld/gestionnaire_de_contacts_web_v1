package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionnaire_de_contacts_web_v1.Contact;
import gestionnaire_de_contacts_web_v1.GestionnaireContact;

/**
 * Servlet implementation class ControleurPrincipal
 */
@WebServlet("/PageContactPrincipale")
public class ControleurContactPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurContactPrincipal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer  userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            this.getServletContext().getRequestDispatcher("/PageUserPrincipale").forward(request, response);
        }
        
        String  appareilNomParam = (String) request.getParameter("appareilNomParam");
        String  appareilNom = (String) request.getSession().getAttribute("appareilNom");   
        String  appareilResolutionParam = (String) request.getParameter("appareilResolutionParam");     
        if (appareilNomParam == null && appareilNom == null) {
            this.getServletContext().getRequestDispatcher("/PageAppareilPrincipale").forward(request, response);
        }
        if (appareilNom == null || appareilNomParam != null) {
        	request.getSession().setAttribute("appareilNom", appareilNomParam);

            String[] tabAppareilResolutionParam = appareilResolutionParam.split("x");
        	request.getSession().setAttribute("appareilResolutionHauteur", tabAppareilResolutionParam[0]);
        	request.getSession().setAttribute("appareilResolutionLargeur", tabAppareilResolutionParam[1]);
        }

        
        GestionnaireContact gestionnaireContact = (GestionnaireContact) request.getSession().getAttribute("gestionnaireContact");
        if (gestionnaireContact == null) {
        	gestionnaireContact = new GestionnaireContact(userId);
        	request.getSession().setAttribute("gestionnaireContact", gestionnaireContact);
        }

        if (request.getParameter("nom") != null) {
	        String nom = request.getParameter("nom").toString();
	        String numero = request.getParameter("numero").toString();
	        String id = request.getParameter("id").toString();
	        
	        if (id.equals("-1")) {
	        	if (nom.equals("")) {
		            this.getServletContext().getRequestDispatcher("/PageContactAjouter").forward(request, response);
		            return;
		        }
	    		gestionnaireContact.exporterUnContact(nom, numero);
	        }else {
	        	if (nom.equals("")) {
		            this.getServletContext().getRequestDispatcher("/PageContactModifier").forward(request, response);
		            return;
		        }
		        try {
		        	gestionnaireContact.modifierUnContact(	Integer.parseInt(id), nom, numero);
				} catch (NumberFormatException | SQLException e) {
					System.out.println("esception 44444");
					e.printStackTrace();
				}
	        }
		}
        
        this.getServletContext().getRequestDispatcher("/PageContactPrincipale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
