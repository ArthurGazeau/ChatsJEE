package fr.epsi.myEpsi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.User;

public class UserDao implements IUserDao {

	private Logger logger = LogManager.getLogger(UserDao.class);

	private ResultSet resultat = null;
	private Statement statement = null;

	@Override
	public List<User> getListOfUsers() {
		List<User> users = new ArrayList<>();
		Connection con = ConnectionDao.getConnection();
		try {
			statement = con.createStatement();
			String requete = "SELECT * FROM USERS";
			logger.debug(requete);
			resultat = statement.executeQuery(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (resultat.next()) {
				User user = new User();
				user.setId(resultat.getString(1));
				user.setPassword(resultat.getString(2));
				user.setAdministrator(resultat.getBoolean(3));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserById(String id) {
		User user = new User();
		Connection con = ConnectionDao.getConnection();
		try {

			statement = con.createStatement();
			String requete = "SELECT * FROM USERS WHERE ID='" + id + "'";
			logger.debug(requete);
			resultat = statement.executeQuery(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (resultat.next()) {
				;
				user.setId(resultat.getString(1));
				user.setPassword(resultat.getString(2));
				user.setAdministrator(resultat.getBoolean(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void addUser(User user) {
		Connection con = ConnectionDao.getConnection();
		try {
			statement = con.createStatement();
			String requete = "INSERT INTO USERS (ID,PASSWORD,ISADMINISTRATOR) VALUES ('" + user.getId() + "','"
					+ user.getPassword() + "'," + user.getAdministrator() + ")";
			logger.debug(requete);
			statement.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block1
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User user) {
		Connection con = ConnectionDao.getConnection();
		try {
			statement = con.createStatement();
			String requete ="INSERT INTO USERS (ID,PASSWORD,ISADMINISTRATOR) VALUES ('" + user.getId() + "','"
					+ user.getPassword() + "'," + user.getAdministrator() + ")";
			logger.debug(requete);
			statement.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block1
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User user) {
		Connection con = ConnectionDao.getConnection();
		try {
			statement = con.createStatement();
			String requete = "DELETE FROM USERS WHERE ID='" + user.getId() + "'";
			logger.debug(requete);
			statement.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block1
			e.printStackTrace();
		}
	}

	@Override
	public boolean ifExist(User user) {
		User userTest = getUserById(user.getId());
		if (userTest != null) {
			return true;
		} else {
			return false;
		}
	}

}
