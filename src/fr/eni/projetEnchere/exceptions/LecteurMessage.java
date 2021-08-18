package fr.eni.projetEnchere.exceptions;

import java.util.ResourceBundle;

/**
 * Classe permet de lire le contenu du fichier messages_erreur.properties
 * @author Groupe G (LORENT Maxime, TANGUY Cyril, COIGNARD Manuel
 *
 */
public abstract class LecteurMessage {
	private static ResourceBundle rb;
	
	/**
	 * Méthode statique se connectant au fichier messages_erreur.properties
	 */
	static
	{
		try
		{
			rb = ResourceBundle.getBundle("fr.eni.projetEnchere.exceptions.messages_erreur");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param code
	 * @return
	 */
	public static  String getMessageErreur(int code)
	{
		String message="";
		try
		{
			if(rb!=null)
			{
				message = rb.getString(String.valueOf(code));
			}
			else
			{
				message="Problème à la lecture du fichier contenant les messages";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message="Impossible de lire le message correspondant au code " + String.valueOf(code);
		}
		return message;
	}
}