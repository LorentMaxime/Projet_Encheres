package fr.eni.projetEnchere.dal;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boUtilisateur;

public interface UtilisateurDAO {
	
	void insert(boUtilisateur nouvelUtilisateur) throws BusinessException;
	
	boUtilisateur connectionEmail(String email) throws BusinessException;
	
	boUtilisateur connectionPseudo(String pseudo) throws BusinessException;

	void desactivationUtilisateur(int noUtillisateur) throws BusinessException;

	void update(boUtilisateur nvlUtilisateur) throws BusinessException;
}
