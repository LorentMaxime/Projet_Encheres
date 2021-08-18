package fr.eni.projetEnchere.dal;

import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boUtilisateur;

public interface ArticleVenduDAO {
	
	/**
	 * Méthode permettant l'ajout en bdd d'un nouvel article
	 * @param nvlArticle objet de type {@link boUtilisateur}
	 * @throws BusinessException
	 */
	void insert(boArticleVendu nvlArticle) throws BusinessException;
	
	/**
	 * Méthode permettant de récupérer la liste des articles dont les enchères sous toujours en cours
	 * à la date du jour. Les enchères closes ne sont pas récupérées !
	 * @return une liste d'articles de la classe {@link ArticleVenduDAO}
	 * @throws BusinessException
	 */
	List<boArticleVendu> selectAll() throws BusinessException;
	
	/**
	 * Méthode affichant un article présent en bdd en fonction de son numéro
	 * @param noArticle 
	 * @return un objet de type {@link boArticleVendu}
	 */
	boArticleVendu selectById(int noArticle) throws BusinessException;

	/**
	 * Méthode affichant la liste des articles dont les enchères sont en cours
	 * @param zoneRecherche texte recherché par utilisateur
	 * @param noCategorie numéro de la catégorie sélectionné par l'utilisateur
	 * @param lst liste des articles pouvant contenir les résultats d'une autre méthode
	 * @param be objet {@link BusinessException} contenant la liste des code erreur en cas de levée d'exception
	 * @return renvoie la liste des articles mis à jour
	 * @throws BusinessException
	 */
	List<boArticleVendu> selectEncheresOuvertes(String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	
	/**
	 * Méthode affichant la liste des articles sur lesquels l'utilisateur connecté a enchéri
	 * @param zoneRecherche texte recherché par utilisateur
	 * @param noCategorie numéro de la catégorie sélectionné par l'utilisateur
	 * @param lst liste des articles pouvant contenir les résultats d'une autre méthode
	 * @param be objet {@link BusinessException} contenant la liste des code erreur en cas de levée d'exception
	 * @return renvoie la liste des articles mis à jour
	 * @throws BusinessException
	 */
	List<boArticleVendu> selectMesEncheres(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	
	/**
	 * Méthode affichant la liste des articles pour lesquels l'utilisateur connecté à remporter les enchères
	 * @param zoneRecherche texte recherché par utilisateur
	 * @param noCategorie numéro de la catégorie sélectionné par l'utilisateur
	 * @param lst liste des articles pouvant contenir les résultats d'une autre méthode
	 * @param be objet {@link BusinessException} contenant la liste des code erreur en cas de levée d'exception
	 * @return renvoie la liste des articles mis à jour
	 * @throws BusinessException
	 */
	List<boArticleVendu> selectEncheresEmportees(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	
	/**
	 * Méthode affichant la liste des articles que l'utilisateur connecté à mis en vente et dont la vente est en cours
	 * @param zoneRecherche texte recherché par utilisateur
	 * @param noCategorie numéro de la catégorie sélectionné par l'utilisateur
	 * @param lst liste des articles pouvant contenir les résultats d'une autre méthode
	 * @param be objet {@link BusinessException} contenant la liste des code erreur en cas de levée d'exception
	 * @return renvoie la liste des articles mis à jour
	 * @throws BusinessException
	 */
	List<boArticleVendu> selectMesVentesEnCours(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	
	/**
	 * Méthode affichant la liste des articles que l'utilisateur connecté à mis en vente et dont la vente n'a pas encore débutées
	 * @param zoneRecherche texte recherché par utilisateur
	 * @param noCategorie numéro de la catégorie sélectionné par l'utilisateur
	 * @param lst liste des articles pouvant contenir les résultats d'une autre méthode
	 * @param be objet {@link BusinessException} contenant la liste des code erreur en cas de levée d'exception
	 * @return renvoie la liste des articles mis à jour
	 * @throws BusinessException
	 */
	List<boArticleVendu> selectMesVentesNonDebutees(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	
	/**
	 * Méthode affichant la liste des articles que l'utilisateur connecté à mis en vente et dont la vente est terminée
	 * @param zoneRecherche texte recherché par utilisateur
	 * @param noCategorie numéro de la catégorie sélectionné par l'utilisateur
	 * @param lst liste des articles pouvant contenir les résultats d'une autre méthode
	 * @param be objet {@link BusinessException} contenant la liste des code erreur en cas de levée d'exception
	 * @return renvoie la liste des articles mis à jour
	 * @throws BusinessException
	 */
	List<boArticleVendu> selectMesVentesTerminees(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
}
