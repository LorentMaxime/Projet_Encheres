package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boEnchere;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String INSERT = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) "
			+ "VALUES(CURRENT_TIMESTAMP,?,?,?)";

	private static final String SELECT_DATE_ARTICLE_ID = "SELECT * FROM(SELECT date_enchere, no_article FROM ENCHERES) as select";
	
	private static final String MODIF_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ? AND prix_initial < ?";

	@Override
	public void insert(boEnchere nvlEnchere) {
		try (Connection cnx = JdbcTools.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, nvlEnchere.getMontantEnchere());
			pstmt.setInt(2, nvlEnchere.getArticleId().getNoArticle());
			pstmt.setInt(3, nvlEnchere.getUtilisateurId().getNoUtilisateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				nvlEnchere.setNoEnchere(rs.getInt(1));
			}
			
			modifPrixVente(nvlEnchere);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boEnchere selectDateArticleId(LocalDate dateEnchere, boArticleVendu articleId) throws SQLException {
		boEnchere select = null;

		try (Connection cnx = JdbcTools.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_DATE_ARTICLE_ID);

			pstmt.setDate(1, Date.valueOf(dateEnchere));
			pstmt.setObject(2, articleId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				LocalDate date = rs.getDate("date_enchere").toLocalDate();
				boArticleVendu id = (boArticleVendu) rs.getObject("no_article");
				select = new boEnchere(date, id);

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreursDAL.ARTICLE_INSERT_ERREUR);
		}
		return select;

	}
	
	/**
	 * Méthode permettant de mettre à jour le prix de vente d'un article suite à une enchère
	 * @param article objet de type {@link boArticleVendu}
	 * @param be objet {@link BusinessException} contenant la liste des code erreur en cas de levée d'exception
	 * @throws BusinessException
	 */
	private void modifPrixVente(boEnchere nvlleEnchere) throws BusinessException{
		
		try (Connection cnx = JdbcTools.getConnection()) {
			
			int nbLignesModifiees = 0;
			
			PreparedStatement pstmt = cnx.prepareStatement(MODIF_PRIX_VENTE);

			pstmt.setInt(1, nvlleEnchere.getMontantEnchere());
			pstmt.setInt(2, nvlleEnchere.getArticleId().getNoArticle());
			pstmt.setInt(3, nvlleEnchere.getMontantEnchere());

			nbLignesModifiees = pstmt.executeUpdate();
			
			/*
			if(nbLignesModifiees == 0) {
				be.ajouterErreur(CodesErreursDAL.MODIF_PRIX_VENTE_ECHEC);
				throw be;
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreursDAL.MODIF_PRIX_VENTE_ERROR);
		}
	}
}