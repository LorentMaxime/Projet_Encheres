package fr.eni.projetEnchere.dal.jdbcTools;

import fr.eni.projetEnchere.dal.ArticleVenduDAO;
import fr.eni.projetEnchere.dal.ArticleVenduDAOJdbcImpl;
import fr.eni.projetEnchere.dal.CategorieDAO;
import fr.eni.projetEnchere.dal.CategorieDAOJdbcImpl;
import fr.eni.projetEnchere.dal.EnchereDAO;
import fr.eni.projetEnchere.dal.EnchereDAOJdbcImpl;
import fr.eni.projetEnchere.dal.UtilisateurDAO;
import fr.eni.projetEnchere.dal.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateur() {
		return new UtilisateurDAOJdbcImpl();
	}
	public static ArticleVenduDAO getArticleVendu() {
		return new ArticleVenduDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorie() {
		return new CategorieDAOJdbcImpl();	
	}
	
	public static EnchereDAO getEnchere() {
		return new EnchereDAOJdbcImpl();
	}
}
