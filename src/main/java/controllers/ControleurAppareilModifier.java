package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestionnaire_de_contacts_web_v1.GestionnaireAppareil;

/**
 * Servlet implementation class ControleurAppareilModifier
 */
@WebServlet("/PageAppareilModifier")
public class ControleurAppareilModifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAppareilModifier() {
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
		
		GestionnaireAppareil gestionnaireAppareil = (GestionnaireAppareil) request.getSession().getAttribute("gestionnaireAppareil");
       
        request.getSession().setAttribute("nom", gestionnaireAppareil.getById(Integer.parseInt(request.getParameter("id"))).getNom());
        request.getSession().setAttribute("hauteur", gestionnaireAppareil.getById(Integer.parseInt(request.getParameter("id"))).getHauteur());
        request.getSession().setAttribute("largeur", gestionnaireAppareil.getById(Integer.parseInt(request.getParameter("id"))).getLargeur());
        request.getSession().setAttribute("id", request.getParameter("id"));
		
        this.getServletContext().getRequestDispatcher("/PageAppareilModifier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
