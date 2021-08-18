package fr.eni.projetEnchere.bll;

public abstract class CodesErreursBLL {

	public static final int EMAIL_REGEX_ERREUR = 20000;
	
	public static final int PSEUDO_TAILLE_ERREUR= 20001;
	
	public static final int PSEUDO_ALPHANUMERIC_ERREUR= 20002;
	
	public static final int NOM_UTILISATEUR_ERREUR= 20003;
	
	public static final int PRENOM_UTILISATEUR_ERREUR= 20004;
	
	public static final int TELEPHONE_REGEX_ERREUR= 20005;
	
	public static final int CODEPOSTAL_REGEX_ERREUR= 20006;
	
	public static final int PASSWORD_REGEX_ERREUR= 20007;
	
	
	public static final int ARTICLE_NOM_ERREUR = 20100;

	public static final int ARTICLE_DESCRIPTON_ERREUR = 20101;
	
	public static final int ARTICLE_DATE_ERREUR = 20102;
	
	public static final int ARTICLE_PRIX_ERREUR = 20103;
	
	public static final int ARTICLE_INSERT_ERREUR = 20104;
	
	
	public static final int ENCHERE_CREDIT_INSUFFISANT = 20500;
	/**
	 * Mauvais mot de passe renseigné
	 */
	public static final int CONNECT_WRONG_PASSWORD = 21000;
	
	/**
	 * Catégorie inconnue
	 */
	public static final int CATEGORIE_INCONNUE = 22000;
	
	public static final int CHK_ENCHERES_OUVERTES_ERROR = 23000;
	public static final int CHK_ENCHERES_MES_ENCHERES_ERROR = 23001;
	public static final int CHK_ENCHERES_EMPORTEES_ERROR = 23002;
	public static final int CHK_VENTES_EN_COURS_ERROR = 23003;
	public static final int CHK_VENTES_NON_DEBUTEES_ERROR = 23004;
	public static final int CHK_VENTES_TERMINEES_ERROR = 23005;
	public static final int LST_CATEGORIE_ERROR = 23006;
	public static final int CHK_ACHAT_VENTE_ERROR = 23007;
	
}
