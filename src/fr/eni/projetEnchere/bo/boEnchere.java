package fr.eni.projetEnchere.bo;

import java.time.LocalDate;

public class boEnchere {
	private int noEnchere;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private boUtilisateur utilisateurId;
	private boArticleVendu articleId;
	
	
	//////GETTERS ET SETTERS//////
	
	
	public int getNoEnchere() {
		return noEnchere;
	}
	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public boUtilisateur getUtilisateurId() {
		return utilisateurId;
	}
	public void setUtilisateurId(boUtilisateur utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	public boArticleVendu getArticleId() {
		return articleId;
	}
	public void setArticleId(boArticleVendu articleId) {
		this.articleId = articleId;
	}

	//////CONSTRUCTEURS///////
	
	public boEnchere() {
		
	}
	public boEnchere(int noEnchere, LocalDate dateEnchere, int montantEnchere, boUtilisateur utilisateurId,
			boArticleVendu articleId) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateurId = utilisateurId;
		this.articleId = articleId;
	}
	public boEnchere(int montantEnchere, boUtilisateur utilisateurId, boArticleVendu articleId) {
		super();
		this.montantEnchere = montantEnchere;
		this.utilisateurId = utilisateurId;
		this.articleId = articleId;
	}
	//constructeur pour les encheres
	public boEnchere(LocalDate dateEnchere, boArticleVendu articleId) {
		super();
		this.dateEnchere = dateEnchere;
		this.articleId = articleId;
	}
	public boEnchere(int noEnchere, int montantEnchere, boUtilisateur utilisateurId) {
		super();
		this.noEnchere = noEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateurId = utilisateurId;
	}

	
	
}
