package fr.eni.projetEnchere.dal;

public class CodesErreursDAL {
	
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_USER_NULL = 10000;
	
	/**
	 * Erreur quand tentative création utilisateur avec Email déjà en base
	 */	
	public static final int INSERT_EMAIL_ERREUR = 10001;
	
	/**
	 * Erreur quand tentative création utilisateur avec Pseudo déjà en base
	 */	
	public static final int INSERT_PSEUDO_ERREUR = 10002;
	
	
	
	/**
	 * Erreur majeure dans la recherche de l'utilisateur
	 */
	public static final int SELECT_USER_ERROR = 11000;
	
	/**
	 * Aucun utilisateur retourné lors de la recherche
	 */
	public static final int NO_USER_FOUND = 11001;
	
	/**
	 * Plusieurs utilisateurs retournés lors de la recherche
	 */
	public static final int MULTIPLE_USERS_FOUND = 11002;
	
	/**
	 * Erreur quand tentative connection avec profil desactivé(supprimé)
	 */
	public static final int SELECT_PROFIL_DESACTIVE = 11003;
	
	/**
	 * Erreur lors de l'update du profil
	 */
	public static final int UPDATE_PROFIL_ERROR = 11004;
	
	/**
	 * Erreur majeure dans la recherche des catégories
	 */
	public static final int SELECT_CATEGORIE_ERROR = 12000;
	
	
	
	public static final int ARTICLE_INSERT_ERREUR = 10100;

	public static final int ARTICLE_SELECTALL_ERREUR = 10101;
	
	public static final int SELECT_ENCHERES_OUVERTES_ERROR = 13000;
	
	public static final int SELECT_MES_ENCHERES_ERROR = 13001;
	
	public static final int SELECT_ENCHERES_EMPORTEES_ERROR = 13002;
	
	public static final int SELECT_VENTES_EC_ERROR = 13003;
	
	public static final int SELECT_VENTES_NON_DEBUTEES_ERROR = 13004;
	
	public static final int SELECT_VENTES_TERMINEES_ERROR = 13005;
	
	public static final int MODIF_PRIX_VENTE_ERROR = 14000;
	public static final int MODIF_PRIX_VENTE_ECHEC = 14001;
}
