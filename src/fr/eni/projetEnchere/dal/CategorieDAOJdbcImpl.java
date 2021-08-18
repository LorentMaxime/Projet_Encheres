package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boCategorie;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES;";

	@Override
	public List<boCategorie> selectAll() throws BusinessException {
		
		List<boCategorie> lstCategorie = new ArrayList<>();
		
		try(Connection cnx = JdbcTools.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
			
				int noCategorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");
				
				lstCategorie.add(new boCategorie(noCategorie,libelle));
				
			}

		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreursDAL.SELECT_CATEGORIE_ERROR);
			throw businessException;
		}
		
		return lstCategorie;
	}
	
	

}
