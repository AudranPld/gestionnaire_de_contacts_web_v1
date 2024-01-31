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

import gestionnaire_de_contacts_web_v1.GestionnaireUser;

/**
 * Servlet implementation class ControleurUserPrincipal
 */
@WebServlet("/PageUserPrincipale")
public class ControleurUserPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurUserPrincipal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		GestionnaireUser gestionnaireUser = (GestionnaireUser) request.getSession().getAttribute("gestionnaireUser");
        if (gestionnaireUser == null) {
        	Connection maConnection = null;
		    try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "135246AETZRY");
				request.getSession().setAttribute("maConnection", maConnection);
			} catch (SQLException | ClassNotFoundException e) {System.out.println("EXEEEPTION");e.printStackTrace();}
			
		    gestionnaireUser = new GestionnaireUser(maConnection);
        	request.getSession().setAttribute("gestionnaireUser", gestionnaireUser);
        }

        if (request.getParameter("email") != null) {
	        if (request.getParameter("id") != null) {
	        	request.getSession().setAttribute("login","");
		        String email = request.getParameter("email").toString();
		        String password = request.getParameter("password").toString();
		        String id = request.getParameter("id").toString();
		        if (id.equals("-1")) {
		        	if (email.equals("") || password.equals("")) {
			            this.getServletContext().getRequestDispatcher("/PageUserAjouter").forward(request, response);
			            return;
			        } 
		    		gestionnaireUser.exporterUnUser(email, password);
		        }else {
		        	if (email.equals("") || password.equals("")) {
			            this.getServletContext().getRequestDispatcher("/PageUserModifier").forward(request, response);
			            return;
			        }
			        try {
			        	gestionnaireUser.modifierUnUser(	Integer.parseInt(id), email, password);
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
		        }
	        }else {
	        	request.getSession().setAttribute("login","Authentification incorrecte");
	        }
		}
        
        this.getServletContext().getRequestDispatcher("/PageUserPrincipale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
