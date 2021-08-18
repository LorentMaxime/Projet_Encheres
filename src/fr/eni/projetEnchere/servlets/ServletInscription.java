package fr.eni.projetEnchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.exceptions.LecteurMessage;

@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// je dispatch vers la servlet inscription
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp");

		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> listeMsgError = new ArrayList<>();
		
		request.setCharacterEncoding("UTF-8");// permet de gerer les accents
		
		// quand on rentre ds le formulaire reviens sur la page d'accueil

		// 1. je crée mes variables implémentées par les infos de l'utilisateur
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("password");
		String mdpBis = request.getParameter("passwordbis");

		// test de savoir si le mot de passe bis est le meme que le premier
		if (mdp.equals(mdpBis)) {

			// 2. on envoie le tout à la BLL
			try {

				UtilisateurManager.getInstance().ajoutNouvelUtilisateur(pseudo, nom, prenom, email, telephone, rue,
						codePostal, ville, mdp);

				// 3. Si L'inscription s'est bien passée je pars vers l'accueil
				response.sendRedirect("/WEB-INF/jsp/index.jsp");
				

			} catch (BusinessException be) {				
				for (int code : be.getListeCodesErreur()) {
					listeMsgError.add(LecteurMessage.getMessageErreur(code));
				}
			
			}

		} else {
			// si non, envoi erreur ds la liste
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreursServlets.PASSWORD_CONFIRMATION_ERREUR);			
			for (int code : be.getListeCodesErreur()) {
				listeMsgError.add(LecteurMessage.getMessageErreur(code));
			}

		}
		// Envoi de la liste d'erreur
		request.setAttribute("ListeMessageErreur", listeMsgError);

		// 4. Si pb lors de l'insertion réaffiche le formulaire
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp");
		rd.forward(request, response);

	}
}