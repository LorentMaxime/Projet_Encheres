package fr.eni.projetEnchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boCategorie;
import fr.eni.projetEnchere.dal.CategorieDAO;
import fr.eni.projetEnchere.dal.jdbcTools.DAOFactory;

public class CategorieManager {

	//////////////////////////////////////// SINGLETON ////////////////////////////////////////
	/**
	 * Mise en place d'un singleton pour l'utilisation du Manager
	 */
	private static CategorieManager instance;

	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}

	///////////////////////////////////////// ATTRIBUTS D'INSTANCE ////////////////////////////
	private CategorieDAO categorieDAO = DAOFactory.getCategorie();

	///////////////////////////////////////// METHODES PUBLICS/////////////////////////////////
	
	/**
	 * Méthode retournant la liste des catégories existantes
	 * @return une liste de catégorie (boCategorie)
	 * @throws BusinessException
	 */
	public List<boCategorie> selectAllCategories() throws BusinessException{
		List<boCategorie> lstCategorie= new ArrayList<>();
		
		lstCategorie = categorieDAO.selectAll();
		
		return lstCategorie;
	}	
}
