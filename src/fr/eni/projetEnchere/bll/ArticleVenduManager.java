package fr.eni.projetEnchere.bll;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.ArticleVenduDAO;
import fr.eni.projetEnchere.dal.jdbcTools.DAOFactory;

public class ArticleVenduManager {
	
	////////////////////////////////////////SINGLETON ////////////////////////////////////////
	/**
	 * Mise en place d'un singleton pour l'utilisation du Manager
	 */
	private static ArticleVenduManager instance;

	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}

	///////////////////////////////////////// ATTRIBUTS D'INSTANCE ////////////////////////////
	private ArticleVenduDAO articleVenduDAO= DAOFactory.getArticleVendu();;
	
	///////////////////////////////////////// METHODES PUBLICS/////////////////////////////////
	public List<boArticleVendu> selectAll() throws BusinessException{
		return articleVenduDAO.selectAll();
	}
	
	/**
	 * Méthode permettant la récupération d'une liste d'article en fonction des paramètres choisis
	 * @param zoneRecherche
	 * @param categorie
	 * @param achatsVente
	 * @param encheresOuvertes
	 * @param mesEncheres
	 * @param encheresEmportees
	 * @param mesVentesEnCours
	 * @param mesVentesNonDebutees
	 * @param mesVentesTerminees
	 * @return
	 */
	public List<boArticleVendu> selectWithFilters(boUtilisateur utilisateur, String zoneRecherche, String categorie, String achatsVente, String encheresOuvertes,
			String mesEncheres, String encheresEmportees, String mesVentesEnCours, String mesVentesNonDebutees,
			String mesVentesTerminees) throws BusinessException {
		
		BusinessException be = new BusinessException();
		List<boArticleVendu> lstArticles = new ArrayList<>();
		
		int valEncheresOuvertes = convertToInteger(encheresOuvertes, be, CodesErreursBLL.CHK_ENCHERES_OUVERTES_ERROR);
		int valMesEncheres = convertToInteger(mesEncheres, be, CodesErreursBLL.CHK_ENCHERES_MES_ENCHERES_ERROR);
		int valEncheresEmportees = convertToInteger(encheresEmportees, be, CodesErreursBLL.CHK_ENCHERES_EMPORTEES_ERROR);
		
		int valMesVentesEnCours = convertToInteger(mesVentesEnCours, be, CodesErreursBLL.CHK_VENTES_EN_COURS_ERROR);
		int valMesVentesNonDebutees = convertToInteger(mesVentesNonDebutees, be, CodesErreursBLL.CHK_VENTES_NON_DEBUTEES_ERROR);
		int valMesVentesTerminees = convertToInteger(mesVentesTerminees, be, CodesErreursBLL.CHK_VENTES_TERMINEES_ERROR);
		
		int valCategorie = convertToInteger(categorie, be, CodesErreursBLL.LST_CATEGORIE_ERROR);
		
		if(valCategorie < 0) {
			valCategorie = 0;
		}
		
		int sumAchat = valEncheresOuvertes + valMesEncheres + valEncheresEmportees;
		
		if(sumAchat < 0) {
			throw be;
		}
		
		int sumVente = valMesVentesEnCours + valMesVentesNonDebutees + valMesVentesTerminees;
		
		if(sumVente < 0) {
			throw be;
		}
		
		if((sumAchat > 0 && sumVente > 0)) {
			be.ajouterErreur(CodesErreursBLL.CHK_ACHAT_VENTE_ERROR);
			throw be;
		}
		
		/*
		 * Achat
		 * 1 - encheresOuvertes										: tous les articles dont les enchères sont encore ouvertes
		 * 2 - mesEncheres											: articles sur lesquels l'utilisateur à au moins 1 enchère
		 * 3 - encheresOuvertes + mesEncheres						: 1 + 2
		 * 4 - encheresEmportees									: tous les articles dont les enchères sont terminées et où l'utilisateur est le dernier enchérisseur
		 * 5 - encheresOuvertes + encheresEmportees					: 1 + 4
		 * 6 - mesEncheres + encheresEmportees						: 2 + 4
		 * 7 - encheresOuvertes + mesEncheres +  encheresEmportees	: 1 + 2 + 4
		 */
		
		/*
		 * Vente
		 * 1 - mesVentesEnCours													: tous les articles dont les enchères sont encore ouvertes et dont je suis le vendeur
		 * 2 - mesVentesNonDebutees												: tous les articles dont les enchères n'ont pas débutées et dont je suis le vendeur
		 * 3 - mesVentesEnCours + mesVentesNonDebutees							: 1 + 2
		 * 4 - mesVentesTerminees												: tous les articles dont les enchères sont terminées et dont je suis le vendeur
		 * 5 - mesVentesEnCours + mesVentesTerminees							: 1 + 4
		 * 6 - mesVentesNonDebutees + mesVentesTerminees						: 2 + 4
		 * 7 - mesVentesEnCours + mesVentesNonDebutees +  mesVentesTerminees	: 1 + 2 + 4
		 */
		if(achatsVente.equals("achat")) {
			switch (sumAchat) {
			case 1:
				lstArticles = articleVenduDAO.selectEncheresOuvertes(zoneRecherche, valCategorie, lstArticles, be);
				break;
		
			case 2:
				lstArticles = articleVenduDAO.selectMesEncheres(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			case 3:
				lstArticles = articleVenduDAO.selectEncheresOuvertes(zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectMesEncheres(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			case 4:
				lstArticles = articleVenduDAO.selectEncheresEmportees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			case 5:
				lstArticles = articleVenduDAO.selectEncheresOuvertes(zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectEncheresEmportees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			case 6:
				lstArticles = articleVenduDAO.selectMesEncheres(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectEncheresEmportees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			case 7:
				lstArticles = articleVenduDAO.selectEncheresOuvertes(zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectMesEncheres(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectEncheresEmportees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			default:
				break;
			}	
		}
		
		if(achatsVente.equals("vente")) {
			switch (sumVente) {
			case 1:
				lstArticles = articleVenduDAO.selectMesVentesEnCours(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
			case 2:
				lstArticles = articleVenduDAO.selectMesVentesNonDebutees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			case 3:
				lstArticles = articleVenduDAO.selectMesVentesEnCours(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectMesVentesNonDebutees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
				
			case 4:
				lstArticles = articleVenduDAO.selectMesVentesTerminees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
			case 5:
				lstArticles = articleVenduDAO.selectMesVentesEnCours(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectMesVentesTerminees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
			case 6:
				lstArticles = articleVenduDAO.selectMesVentesNonDebutees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectMesVentesTerminees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;
			case 7:
				lstArticles = articleVenduDAO.selectMesVentesEnCours(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectMesVentesNonDebutees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				lstArticles = articleVenduDAO.selectMesVentesTerminees(utilisateur, zoneRecherche, valCategorie, lstArticles, be);
				break;

			default:
				break;
			}	
		}
				
		return lstArticles;
		
	}
	
	// Méthode

	public void ajoutArticle (String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate finDebutEnchere,int prixInitial, int idUtilisateur, int categorie) throws BusinessException {

		// Vérification des données provenant du formulaire

		// Après vérification, création de l'article
		boArticleVendu nvlArticle = new boArticleVendu(nomArticle, description, dateDebutEnchere, finDebutEnchere,
				prixInitial, idUtilisateur, categorie);

		// Ajout à la BDD
		articleVenduDAO.insert(nvlArticle);
	}
	public boArticleVendu selectById(int articleId) throws BusinessException {
		return articleVenduDAO.selectById(articleId);
	}

	/*******************************************************************************
	 *
	 * ENSEMBLE DES MÉTHODES INDÉPENDANTES DE VERIFICATION DE CHAMPS
	 *
	 *******************************************************************************/

	private void validerArticleVendu(boArticleVendu articleVendu, BusinessException businessException) {
		validerNomArticle(articleVendu, businessException);
		validerDescription(articleVendu, businessException);
		validerDate(articleVendu.getDateDebutEncheres(), businessException);
		validerDate(articleVendu.getFinDebutEncheres(), businessException);
		validerPrix(articleVendu.getPrixInitial(), businessException);
		validerPrix(articleVendu.getPrixVente(), businessException);
	}
	
	private void validerNomArticle(boArticleVendu articleVendu, BusinessException businessException) {
		if(articleVendu.getNomArticle().length() > 5 && articleVendu.getNomArticle().length() < 30) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_NOM_ERREUR);
		}
	}
	private void validerDescription(boArticleVendu articleVendu, BusinessException businessException) {
		
		if(articleVendu.getDescription().length() > 10 && articleVendu.getDescription().length() < 300) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_DESCRIPTON_ERREUR);
		}
	}
	private void validerDate (LocalDate date, BusinessException businessException) {
		if (date == null || date.equals(LocalDate.now()) || date.isAfter(LocalDate.now())) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_DATE_ERREUR);
		}
	}
	private void validerPrix(int prix, BusinessException businessException) {
		if(prix < 0 ) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_PRIX_ERREUR);
		}
	}
	
	/**
	 * Méthode permettant de vérifier si un String peu être converti en int
	 * @param val valeur de type {@link String} à convertir
	 * @param be objet {@link BusinessException} permettant la remontée des erreurs
	 * @param codeErreur code provenant de {@link CodesErreursBLL} 
	 * @return valeur convertie ; par défaut -7
	 */
	private int convertToInteger(String val, BusinessException be, int codeErreur) {
		int valRetour = -7;
		
		try {
			if(val == null) {
				valRetour = 0;
			} else {
				valRetour = Integer.parseInt(val);
			}
			
		} catch (Exception e) {
			be.ajouterErreur(codeErreur);
			e.getMessage();
		}
		
		return valRetour;
	}
}