package fr.eni.projetEnchere;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Groupe G (LORENT Maxime, TANGUY Cyril, COIGNARD Manuel
 *
 * Classe listant l'ensemble des erreurs (par leur code) pouvant survenir lors d'un traitement
 * quel que soit la couche à l'origine.
 */

public class BusinessException extends Exception {
	//------------------------- CONSTANTES --------------------------//
	private static final long serialVersionUID = 1L;
	//---------------------------------------------------------------//
	
	//------------------------- ATTRIBUTS D'INSTANCE -----------------//
	private List<Integer> listeCodesErreur;
	//----------------------------------------------------------------//
	
	
	//----------------------- CONSTRUCTEURS --------------------------//
	/**
	 * Constructeur de la classe créant une nouvelle liste de codes erreurs
	 */
	public BusinessException() {
		super();
		this.listeCodesErreur=new ArrayList<>();
	}
	
	/**
	 * 
	 * @param code Code de l'erreur. 
	 * Doit avoir un message associé dans un fichier properties.
	 */
	public void ajouterErreur(int code)
	{
		if(!this.listeCodesErreur.contains(code))
		{
			this.listeCodesErreur.add(code);
		}
	}
	
	public boolean hasErreurs()
	{
		return this.listeCodesErreur.size()>0;
	}
	
	public List<Integer> getListeCodesErreur()
	{
		return this.listeCodesErreur;
	}

	public void viderListeErreurs() {
		this.listeCodesErreur = new ArrayList<>();
	}
	
}