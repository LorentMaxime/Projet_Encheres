package fr.eni.projetEnchere.bo;

public class boCategorie {

	private int id;
	private String libelle;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public boCategorie() {
	}
	
	public boCategorie(String libelle) {
		this();
		this.libelle = libelle;
	}
	public boCategorie(int id, String libelle) {
		this(libelle);
		this.id = id;
	}
	
	
	
}
