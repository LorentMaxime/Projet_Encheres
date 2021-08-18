package fr.eni.projetEnchere.dal.jdbcTools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//autre nom pour la JdbcTools
public abstract class JdbcTools {
	private static DataSource dataSource;
	
	/**
	 * Au chargement de la classe, la DataSource est recherchée dans l'arbre JNDI
	 * Le bloc static ne s'active qu'à l'instanciation de la classe ou à l'appel d'une méthode static de la classe
	 * Mais 1 seule et 1 seule fois
	 */
	static
	{
		Context context;
		try {
			context = new InitialContext();
			JdbcTools.dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible d'accéder à la base de données");
		}
	}
	
	/**
	 * Cette méthode retourne une connection opérationnelle issue du pool de connexion
	 * vers la base de données. 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		return JdbcTools.dataSource.getConnection();
	}
}