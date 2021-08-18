package fr.eni.projetEnchere.bo;

import java.time.LocalDate;

public class boArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate finDebutEncheres;
	private int prixInitial;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	private int noRetrait;
	private boUtilisateur vendeur;
	private boCategorie categorie;
	private boEnchere enchere;
	
	//////GETTERS ET SETTERS//////
	
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public LocalDate getFinDebutEncheres() {
		return finDebutEncheres;
	}
	public void setFinDebutEncheres(LocalDate finDebutEncheres) {
		this.finDebutEncheres = finDebutEncheres;
	}
	public int getPrixInitial() {
		return prixInitial;
	}
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public int getNoRetrait() {
		return noRetrait;
	}
	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}
	
	public boUtilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(boUtilisateur vendeur) {
		this.vendeur = vendeur;
	}
	public boCategorie getCategorie() {
		return categorie;
	}
	public void setCategorie(boCategorie categorie) {
		this.categorie = categorie;
	}
	public boEnchere getEnchere() {
		return enchere;
	}
	public void setEnchere(boEnchere enchere) {
		this.enchere = enchere;
	}
	//////CONSTRUCTEURS///////
	
	/**
	 * Constructeur vide
	 */
	public boArticleVendu() {
		
	}
	/**
	 * Constructeur ajout de l'article. Dans ce cas précis, le prix initial est également le prix de vente
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param finDebutEncheres
	 * @param prixInitial
	 * @param noUtilisateur
	 * @param noCategorie
	 */
	public boArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate finDebutEncheres, int prixInitial, int noUtilisateur, int noCategorie) {
		super();
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.finDebutEncheres = finDebutEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixInitial;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}
	public boArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate finDebutEncheres, int prixInitial, int prixVente, int noUtilisateur, int noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.finDebutEncheres = finDebutEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}

	
	/**
	 * Constructeur de l'article
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param finDebutEncheres
	 * @param prixInitial
	 * @param prixVente
	 */
	public boArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate finDebutEncheres, int prixInitial, int prixVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.finDebutEncheres = finDebutEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;	
	}
	
	/**
	 * Constructeur pour le selectAll
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param finDebutEncheres
	 * @param prixInitial
	 * @param noUtilisateur
	 * @param noCategorie
	 * @param vendeur
	 */	
	public boArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate finDebutEncheres, int prixInitial, int noUtilisateur, int noCategorie, boUtilisateur vendeur) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.finDebutEncheres = finDebutEncheres;
		this.prixInitial = prixInitial;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.vendeur = vendeur;
	}
	
	/**
	 * Constructeur pour selectAll avec affichage du pseudo du vendeur - Manuel 25/06/2021
	 * @param noArticle
	 * @param nomArticle
	 * @param finDebutEncheres
	 * @param prixVente
	 * @param vendeur
	 */
	public boArticleVendu(int noArticle, String nomArticle, LocalDate finDebutEncheres, int prixVente, boUtilisateur vendeur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.finDebutEncheres = finDebutEncheres;
		this.prixVente = prixVente;
		this.vendeur = vendeur;
	}
/*Constructeur pour le select by id
 */
	public boArticleVendu(int noArticle, String nomArticle, String description, LocalDate finDebutEncheres,
			 int prixInitial, boCategorie categorie, boUtilisateur vendeur, boEnchere enchere) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.finDebutEncheres = finDebutEncheres;
		this.prixInitial = prixInitial;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.enchere = enchere;
	}
	/**
	 * Méthode toString pour l'affichage des données de l'article
	 */
	@Override
	public String toString() {
		return "boArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", finDebutEncheres=" + finDebutEncheres
				+ ", prixInitial=" + prixInitial + ", prixVente=" + prixVente + ", noUtilisateur=" + noUtilisateur
				+ ", noCategorie=" + noCategorie + ", noRetrait=" + noRetrait + "]";
	}
	
	
	
}
