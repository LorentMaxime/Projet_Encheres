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
import fr.eni.projetEnchere.bll.ArticleVenduManager;
import fr.eni.projetEnchere.bll.CategorieManager;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boCategorie;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.exceptions.LecteurMessage;

/**
 * Servlet implementation class ServletPageAccueil
 */
@WebServlet("/ServletPageAccueil")
public class ServletPageAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> lstMsgError = new ArrayList<>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// Récupération de la liste de tous les articles disponibles
			if(request.getAttribute("listeArticle") == null){
				List<boArticleVendu> listeArticle = new ArrayList<>();
				listeArticle = ArticleVenduManager.getInstance().selectAll();
				request.setAttribute("listeArticle", listeArticle);
			}

			//Récupération de la liste des catégories
			List<boCategorie> lstCategories = new ArrayList<>();
			lstCategories = CategorieManager.getInstance().selectAllCategories();
			request.setAttribute("lstCategories", lstCategories);

		} catch (BusinessException be) {
			for (int code : be.getListeCodesErreur()) {
				lstMsgError.add(LecteurMessage.getMessageErreur(code));
			}
			
			request.setAttribute("listeMessagesErreur", lstMsgError);

		}
		
		//Affichage de la .jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*1.vérifier si utilisateur connecté;
		 *	si nok, direction accueil non connecté (doGet) 
		 *	si ok continue cette méthode
		 */
		if(request.getSession().getAttribute("connectedUser") == null) {
			doGet(request, response);
		}
		
		/*2.Récupère les paramètres de la requête;
		 *	si optBtnAchats et aucune chk sélectionnée, 'enchères en cours' par défaut
		 *	si optBtnVentes et aucune chk sélectionnée, 'mes ventes en cours par défaut
		 *	si zoneRecherche est vide, selectAll
		 *	si categorie renvoie 0, selectAll
		 */
		
		boUtilisateur Utilisateur = (boUtilisateur) request.getSession().getAttribute("connectedUser");
		
		String zoneRecherche = request.getParameter("zoneRecherche");
		String categorie = request.getParameter("categorie");
		String achatsVente = request.getParameter("achatsVente");
		
		String encheresOuvertes = request.getParameter("chkEncheresOuvertes");
		String mesEncheres = request.getParameter("chkMesEncheres");
		String encheresEmportees = request.getParameter("chkMesEncheresEmportees");
		
		String mesVentesEnCours = request.getParameter("chkMesVentesEnCours");
		String mesVentesNonDebutees = request.getParameter("chkVentesNonDebutees");
		String mesVentesTerminees = request.getParameter("chkVentesTerminees");
		
		try {
			List<boArticleVendu> listeArticle = new ArrayList<>();
			listeArticle = ArticleVenduManager.getInstance().selectWithFilters(Utilisateur, zoneRecherche,categorie,achatsVente,encheresOuvertes,mesEncheres,encheresEmportees,mesVentesEnCours,mesVentesNonDebutees,mesVentesTerminees);
			request.setAttribute("listeArticle", listeArticle);
			
			//Affichage de la .jsp
			doGet(request, response);
			
		} catch (BusinessException be) {
			for (int code : be.getListeCodesErreur()) {
				lstMsgError.add(LecteurMessage.getMessageErreur(code));
			
			}
			request.setAttribute("listeMessagesErreur", lstMsgError);
			//Affichage de la .jsp
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			rd.forward(request, response);
		}
	}

}
