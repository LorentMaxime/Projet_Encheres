package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

/**
 * Classe contenant les transactions SQL Server pour la gestion de l'utilisateur
 * @author Groupe G (LORENT Maxime, TANGUY Cyril, COIGNARD Manuel
 *
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	/////////////////////////////////// CONSTANTES ////////////////////////////////////////
	private static final String INSERT = "INSERT INTO "
									   + "UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
									   + " VALUES (?,?,?,?,?,?,?,?,?,100,0);";
	
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?;";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	
	private static final String DESACTIVATION_PROFIL = "UPDATE UTILISATEURS SET utilisateur_actif= 0 WHERE no_utilisateur=?";
	
	private static final String UPDATE_PROFIL = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=100, administrateur=0 WHERE no_utilisateur=?";
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Méthode permet d'insérer un nouvel utilisateur dans la base de données 
	 * @throws BusinessException 
	 */
	@Override
	public void insert(boUtilisateur nouvelUtilisateur) throws BusinessException {
		
		
		try (Connection cnx = JdbcTools.getConnection()){
			cnx.setAutoCommit(false);
			
			try{
				// appel aux methodes de verification sur email et pseudo si deja en bdd ou non
					validerEmail(nouvelUtilisateur);
					validerPseudo(nouvelUtilisateur);
					
				
				// Si email et pseudo ne sont pas déjà en bdd, fait l'insert
				PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, nouvelUtilisateur.getPseudo());
				pstmt.setString(2, nouvelUtilisateur.getNom());
				pstmt.setString(3, nouvelUtilisateur.getPrenom());
				pstmt.setString(4, nouvelUtilisateur.getEmail());			
				pstmt.setString(5, nouvelUtilisateur.getTelephone().equals("")?null:nouvelUtilisateur.getTelephone());
				pstmt.setString(6, nouvelUtilisateur.getRue());
				pstmt.setString(7, nouvelUtilisateur.getCodePostal());
				pstmt.setString(8, nouvelUtilisateur.getVille());
				pstmt.setString(9, nouvelUtilisateur.getMotDePpasse());				
				pstmt.executeUpdate();
				
				ResultSet resultSet = pstmt.getGeneratedKeys();
				
				if(resultSet.next())
				{
					nouvelUtilisateur.setNoUtilisateur(resultSet.getInt(1));
				}
				cnx.commit();
			}
			catch(SQLException e)
			{
				cnx.rollback();
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesErreursDAL.INSERT_USER_NULL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Méthode permettant de récupérer un utilisateur dans la base de données via son adresse mail
	 * et retournant un objet boUtilisateur
	 */
	@Override
	public boUtilisateur connectionEmail(String email) throws BusinessException {
		
		boUtilisateur utilisateur = null;
		
		try(Connection cnx = JdbcTools.getConnection()){
			
			/* Utilisation des constantes ResultSet.TYPE_SCROLL_INSENSITIVE pour permettre la navigation dans le ResultSet
			 * et de ResultSet.CONCUR_READ_ONLY pour ne permettre que la lecture seule
			 */
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			pstmt.setString(1, email);
			
			ResultSet rs= pstmt.executeQuery();

				int nb = isUnique(rs);
				
				if(nb!=1) {
					BusinessException be = new BusinessException();
					switch(nb) {
						case 0  : be.ajouterErreur(CodesErreursDAL.NO_USER_FOUND);
								  break;
						default : be.ajouterErreur(CodesErreursDAL.MULTIPLE_USERS_FOUND);
					}
					throw be;
				}
				
				//Positionne le curseur sur la 1ère ligne de résultat du ResultSet
				rs.absolute(1);
				boolean utilisateurActif = rs.getBoolean("utilisateur_actif");
				
				// Si tentative de connection a un profil desactivé, message erreur adequat
				if (utilisateurActif == false) {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesErreursDAL.SELECT_PROFIL_DESACTIVE);
					throw be;
				}else {		// Sinon construction de l'utilisateur			
	
					//Récupération des données de l'utilisateur 
					int noId = rs.getInt("no_utilisateur");
					String pseudo = rs.getString("pseudo");
					String nom = rs.getString("nom");
					String prenom = rs.getString("prenom");
					String tel = rs.getString("telephone");
					String mdp = rs.getString("mot_de_passe");
					String adresse = rs.getString("rue");
					String cp = rs.getString("code_postal");
					String ville = rs.getString("ville");
					int credit = rs.getInt("credit");
					boolean admin = rs.getBoolean("administrateur");
					
					utilisateur =new boUtilisateur(noId, pseudo, nom, prenom, email, tel, adresse, cp, ville, mdp, credit, admin);
				}		
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreursDAL.SELECT_USER_ERROR);
			throw be;
		}

		return utilisateur;
	}

	/**
	 * Méthode permettant de récupérer un utilisateur dans la base de données via son pseudo
	 * et retournant un objet boUtilisateur
	 */
	@Override
	public boUtilisateur connectionPseudo(String pseudo) throws BusinessException {
		
		boUtilisateur utilisateur = null;
		
		try(Connection cnx = JdbcTools.getConnection()){
			
			/* Utilisation des constantes ResultSet.TYPE_SCROLL_INSENSITIVE pour permettre la navigation dans le ResultSet
			 * et de ResultSet.CONCUR_READ_ONLY pour ne permettre que la lecture seule
			 */
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			pstmt.setString(1, pseudo);
			
			ResultSet rs= pstmt.executeQuery();

				int nb = isUnique(rs);
				
				if(nb!=1) {
					BusinessException be = new BusinessException();
					switch(nb) {
						case 0  : be.ajouterErreur(CodesErreursDAL.NO_USER_FOUND);
								  break;
						default : be.ajouterErreur(CodesErreursDAL.MULTIPLE_USERS_FOUND);
					}
					throw be;
				}
				
				//Positionne le curseur sur la 1ère ligne de résultat du ResultSet
				rs.absolute(1);
				boolean utilisateurActif = rs.getBoolean("utilisateur_actif");
				
				// Si tentative de connection a un profil desactivé, message erreur adequat
				if (utilisateurActif == false) {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesErreursDAL.SELECT_PROFIL_DESACTIVE);
					throw be;
				}else {		// Sinon construction de l'utilisateur			
	
					//Récupération des données de l'utilisateur 
					int noId = rs.getInt("no_utilisateur");
					String email = rs.getString("email");
					String nom = rs.getString("nom");
					String prenom = rs.getString("prenom");
					String tel = rs.getString("telephone");
					String mdp = rs.getString("mot_de_passe");
					String adresse = rs.getString("rue");
					String cp = rs.getString("code_postal");
					String ville = rs.getString("ville");
					int credit = rs.getInt("credit");
					boolean admin = rs.getBoolean("administrateur");
					
					utilisateur =new boUtilisateur(noId, pseudo, nom, prenom, email, tel, adresse, cp, ville, mdp, credit, admin);
				}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreursDAL.SELECT_USER_ERROR);
			throw be;
		}

		return utilisateur;
	}
	
	/**
	 * methode qui desactive un utilisateur en bdd donc supprimant le profil et ses fonctions
	 */
	@Override
	public void desactivationUtilisateur(int noUtillisateur) throws BusinessException {
		
		try (
				Connection cnx = JdbcTools.getConnection()
			){
			
			PreparedStatement pStmt = cnx.prepareStatement(DESACTIVATION_PROFIL);
			pStmt.setInt(1, noUtillisateur);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreursDAL.SELECT_USER_ERROR);
			throw be;
		}
	}
	
	/**
	 * Methode qui modifie les données sur un utilisateur en bdd
	 */
	@Override
	public void update(boUtilisateur modificationUtilisateur) throws BusinessException {
		
		try(
				Connection cnx = JdbcTools.getConnection()
			) {
				
				// verification sur email et pseudo si deja existant en bdd
				validerEmail(modificationUtilisateur);
				validerPseudo(modificationUtilisateur);
			
				// Si ok apres verif fait les modif en bdd
				PreparedStatement pStmt = cnx.prepareStatement(UPDATE_PROFIL);
			
				pStmt.setString(1, modificationUtilisateur.getPseudo());
				pStmt.setString(2, modificationUtilisateur.getNom());
				pStmt.setString(3, modificationUtilisateur.getPrenom());
				pStmt.setString(4, modificationUtilisateur.getEmail());			
				pStmt.setString(5, modificationUtilisateur.getTelephone().equals("")?null:modificationUtilisateur.getTelephone());
				pStmt.setString(6, modificationUtilisateur.getRue());
				pStmt.setString(7, modificationUtilisateur.getCodePostal());
				pStmt.setString(8, modificationUtilisateur.getVille());
				pStmt.setString(9, modificationUtilisateur.getMotDePpasse());
				pStmt.setInt(10, modificationUtilisateur.getNoUtilisateur());
				pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.ajouterErreur(CodesErreursDAL.UPDATE_PROFIL_ERROR);
			throw bException;
		}

	}
	
	/**
	 * Méthode permettant de connaître le nombre de ligne dans le résultat de la transaction SQL
	 * @param rs résultat de la transaction SQL exécutée (ResultSet)
	 * @return le nombre de ligne contenu dans le rs
	 * @throws SQLException en cas d'erreur, remonte l'exception pour être gérée par la méthode appelante
	 */
	private int isUnique(ResultSet rs) throws SQLException {
		//Initialisation du compteur de ligne
		int nb=0;
		
		//Positionne le curseur sur la dernière ligne du rs
		if(rs.last()) {
			//récupère le numéro de la ligne
			nb = rs.getRow();
		}
		
		//Retourne le numéro de ligne récupérée
		return nb;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
///									METHODES GESTION ERREURS										///
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * methode verifiant si un email existe deja en bdd
	 * @param utilisateur
	 * @throws BusinessException
	 */
	public void validerEmail(boUtilisateur utilisateur) throws BusinessException {
		
		try (Connection cnx = JdbcTools.getConnection()){
			BusinessException be = new BusinessException();
			
			// Verification si email deja en bdd
			PreparedStatement verifMail = cnx.prepareStatement(SELECT_BY_EMAIL);						
			verifMail.setString(1,utilisateur.getEmail());			
			ResultSet rs = verifMail.executeQuery();
			if (rs.next()) {
				be.ajouterErreur(CodesErreursDAL.INSERT_EMAIL_ERREUR);
				throw be;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * methode verifiant si un pseudo existe deja en bdd
	 * @param utilisateur
	 * @throws BusinessException
	 */
	public void validerPseudo(boUtilisateur utilisateur) throws BusinessException {
		
		try (Connection cnx = JdbcTools.getConnection()){
			BusinessException be = new BusinessException();
			
			// Verification si pseudo deja en bdd
			PreparedStatement verifPseudo = cnx.prepareStatement(SELECT_BY_PSEUDO);
			verifPseudo.setString(1,utilisateur.getPseudo());			
			ResultSet rSet = verifPseudo.executeQuery();
			if (rSet.next()){
				be.ajouterErreur(CodesErreursDAL.INSERT_PSEUDO_ERREUR);
				throw be;
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
