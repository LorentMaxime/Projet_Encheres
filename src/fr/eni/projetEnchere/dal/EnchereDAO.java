package fr.eni.projetEnchere.dal;

import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boEnchere;

public interface EnchereDAO {
	void insert(boEnchere nvlEnchere)throws BusinessException;
		
		
	boEnchere selectDateArticleId (LocalDate dateEnchere, boArticleVendu articleId) throws SQLException;

}
