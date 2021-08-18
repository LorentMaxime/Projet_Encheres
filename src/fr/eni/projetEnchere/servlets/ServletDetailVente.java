package fr.eni.projetEnchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.ArticleVenduManager;
import fr.eni.projetEnchere.bll.EnchereManager;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boEnchere;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.exceptions.LecteurMessage;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/ServletDetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("listeErreur") != null) {
			request.setAttribute("listeErreur",request.getAttribute("listeErreur"));
		}
		int articleId = Integer.parseInt(request.getParameter("id"));
		//int utilisateurId = Integer.parseInt(request.getParameter("idUtilisateur"));
		boArticleVendu toto;
		try {
			toto = ArticleVenduManager.getInstance().selectById(articleId);
			request.setAttribute("article", toto);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
		rd.forward(request, response);
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		boUtilisateur profilConnecte = (boUtilisateur) session.getAttribute("connectedUser");
		
		try {
			//ID de l'utilisateur et de l'article
			
			
			int articleId = Integer.parseInt(request.getParameter("id"));
			boArticleVendu selectById =ArticleVenduManager.getInstance().selectById(articleId);
		
			//crédit de l'utilisateur + prix initial de l'article
			int montantEnchere = Integer.parseInt(request.getParameter("enchere"));
			//request.setAttribute("tata", montantEnchere);
			EnchereManager.getInstance().ajoutEnchere(montantEnchere,profilConnecte, selectById );
		
			System.out.println("enchere" + montantEnchere);
			
		}catch (BusinessException be) {
			List<String> listeMsgError = new ArrayList<>();
			for (int code : be.getListeCodesErreur()) {
				listeMsgError.add(LecteurMessage.getMessageErreur(code));
			}
			request.setAttribute("listeErreur", listeMsgError);
			
		} 
		doGet(request, response);
	
	}
}
