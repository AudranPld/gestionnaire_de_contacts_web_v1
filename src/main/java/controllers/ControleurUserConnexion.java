package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionnaire_de_contacts_web_v1.GestionnaireContact;
import gestionnaire_de_contacts_web_v1.GestionnaireUser;
import gestionnaire_de_contacts_web_v1.User;

/**
 * Servlet implementation class ControleurUserConnexion
 */
@WebServlet("/ControleurUserConnexion")
public class ControleurUserConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurUserConnexion() {
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
    	
        String email = request.getParameter("email").toString();
        String password = request.getParameter("password").toString();
    	for(int i=0;i<gestionnaireUser.getUsers().size();i++) {
        	//System.out.println(gestionnaireUser.getUsers().get(i).getEmail() + " = " + email + " _ " + gestionnaireUser.getUsers().get(i).getPassword() + " = " + password);
        	if(gestionnaireUser.getUsers().get(i).getEmail().equals(email) && gestionnaireUser.getUsers().get(i).getPassword().equals(password)) {
	        	request.getSession().setAttribute("email",email);
	        	request.getSession().setAttribute("userId",gestionnaireUser.getUsers().get(i).getId());
	        	this.getServletContext().getRequestDispatcher("/PageAppareilPrincipale").forward(request, response);
	        	return;
	        }
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
