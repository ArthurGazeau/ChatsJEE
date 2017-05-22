package fr.epsi.myEpsi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.User;

public class ConnectionDao {
	private Logger logger = LogManager.getLogger(ConnectionDao.class);

	public static Connection getConnection() {

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "sa", "");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean isAuthorized(User user) {
		Connection con = getConnection();
		if (con != null) {
			try {
				Statement statement = con.createStatement();
				String query = "Select count(*) From USERS WHERE ID='" + user.getId() + "' and PASSWORD='"
						+ user.getPassword() + "'";
				logger.debug(query);
				ResultSet resultat = statement.executeQuery(query);
				int size = 0;
				while (resultat.next()) {
					size = resultat.getInt(1);
				}
				if (size == 0) {
					return false;
				} else {
					return true;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
