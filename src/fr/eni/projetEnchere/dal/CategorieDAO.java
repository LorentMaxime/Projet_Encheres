package fr.eni.projetEnchere.dal;

import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boCategorie;

public interface CategorieDAO {

	List<boCategorie> selectAll() throws BusinessException;
	
}
