package fr.eni.projetEnchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.bo.boUtilisateur;


@WebServlet("/ServletProfil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// Je crée un utilisateur "profilUtilisateur" initialisé avec les données cherchées en bdd via méthode connectionPseudo()
		boUtilisateur profilUtilisateur = new boUtilisateur();

			String pseudo = request.getParameter("pseudoProfil");
			if(pseudo != null) {
				try {
					profilUtilisateur = UtilisateurManager.getInstance().profilParPseudo(pseudo);
				} catch (BusinessException e) {				
					e.printStackTrace();
				}
			}
			
			// J'envoi l'objet l'utilisateur "profilUtilisateur" vers la jsp
			request.setAttribute("profilConnecte", profilUtilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp");
			rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationProfil.jsp");
		rd.forward(request, response);
	}

}
