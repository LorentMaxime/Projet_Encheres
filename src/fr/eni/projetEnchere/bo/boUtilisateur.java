package fr.eni.projetEnchere.bo;

public class boUtilisateur {

	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePpasse;
	private int credit;
	private boolean administrateur;
	
	//////////// GETTER SETTER //////////
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePpasse() {
		return motDePpasse;
	}

	public void setMotDePpasse(String motDePpasse) {
		this.motDePpasse = motDePpasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	//////////// Déclaration des contructeurs//////////

	// constructeur vide
	public boUtilisateur() {

	}

	// constructeur inscription et de connexion
	public boUtilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePpasse, int credit, boolean administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePpasse = motDePpasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	// constructeur sans id d'utilisateur
	public boUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePpasse, int credit, boolean administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePpasse = motDePpasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	// constructeur sans administrateur
		public boUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
				String codePostal, String ville, String motDePpasse) {
			super();
			this.pseudo = pseudo;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.telephone = telephone;
			this.rue = rue;
			this.codePostal = codePostal;
			this.ville = ville;
			this.motDePpasse = motDePpasse;
			
		}
	
	//constructeur selectAll/ quelques chose pour vous
		
		
	public boUtilisateur(String nom, String prenom, String email, String telephone, String rue, String codePostal,
			String ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	
	/**
	 * Constructeur pour l'affichage de l'utilisateur sur la fiche article
	 * @param noUtilisateur
	 * @param pseudo
	 */
	public boUtilisateur(int noUtilisateur, String pseudo) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
	}

	/*
	 * constructeur pour le select by id
	 */
	
	public boUtilisateur(int noUtilisateur, String pseudo, String rue, String codePostal, String ville, int credit) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.credit = credit;
	}

	
	/**
	 * construcvteur pour le update utilisateur
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePpasse
	 */

	public boUtilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePpasse) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePpasse = motDePpasse;
	}

	//Affichage des données de l'utilisateur
	@Override
	public String toString() {
		return "boUtilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePpasse=" + motDePpasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + "]";
	}


}
