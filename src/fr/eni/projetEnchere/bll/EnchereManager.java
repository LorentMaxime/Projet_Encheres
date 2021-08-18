package fr.eni.projetEnchere.bll;

import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boEnchere;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.EnchereDAO;
import fr.eni.projetEnchere.dal.UtilisateurDAO;
import fr.eni.projetEnchere.dal.jdbcTools.DAOFactory;

public class EnchereManager {

	//////////////////////////////////////// SINGLETON
	//////////////////////////////////////// ////////////////////////////////////////
	/**
	 * Mise en place d'un singleton pour l'utilisation du Manager
	 */
	private static EnchereManager instance;

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

	// attribut d'instance

	private EnchereDAO enchereDAO = DAOFactory.getEnchere();

	public void ajoutEnchere(int montantEnchere, boUtilisateur utilisateurId, boArticleVendu articleId)
			throws BusinessException {
		BusinessException be = new BusinessException();

		// Création de l'enchère
		boEnchere nvlEnchere = new boEnchere(montantEnchere, utilisateurId, articleId);

		gestionErreurEnchere(nvlEnchere, be);
		if (be.hasErreurs()) {
			throw be;
		}
		// Si pas erreur : Ajout dans la BDD
		enchereDAO.insert(nvlEnchere);
	}

	public boEnchere selectDateArticleId(LocalDate dateEnchere, boArticleVendu articleid) throws SQLException {
		return enchereDAO.selectDateArticleId(dateEnchere, articleid);
	}

	/*******************************************************************************
	 *
	 * ENSEMBLE DES MÉTHODES INDÉPENDANTES DE VERIFICATION DE CHAMPS
	 *
	 *******************************************************************************/

	private void gestionErreurEnchere(boEnchere enchere, BusinessException businessException) {
		creditInsuffisant(enchere, businessException);
	}

	private void creditInsuffisant(boEnchere enchere, BusinessException businessException) {
		if (enchere.getUtilisateurId().getCredit() < enchere.getMontantEnchere()) {
			businessException.ajouterErreur(CodesErreursBLL.ENCHERE_CREDIT_INSUFFISANT);
		}
	}
}