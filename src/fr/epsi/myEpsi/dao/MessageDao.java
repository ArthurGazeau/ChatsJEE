package fr.epsi.myEpsi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.Message;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.service.UserService;

public class MessageDao implements IMessageDao {

	private Logger logger = LogManager.getLogger(MessageDao.class);

	private Connection con = null;
	private ResultSet resultat = null;
	private Statement statement = null;

	private UserService userService = new UserService();

	@Override
	public List<Message> getListOfMessages(User user) {
		List<Message> listeMessage = new ArrayList<>();
		con = ConnectionDao.getConnection();
		if (con != null) {
			try {
				statement = con.createStatement();
				String requete = "Select * From MESSAGES";
				if (user.getId() != null) {
					requete += " Where USER_ID = '" + user.getId() + "'";
				}
				logger.debug(requete);
				resultat = statement.executeQuery(requete);
				while (resultat.next()) {
					Long id = resultat.getLong(1);
					String title = resultat.getString(2);
					String content = resultat.getString(3);
					String author = resultat.getString(4);
					Timestamp creationDate = resultat.getTimestamp(5);
					Timestamp updateDate = resultat.getTimestamp(6);
					Integer status = resultat.getInt(7);

					Message message = new Message();
					message.setId(id);
					message.setTitle(title);
					message.setContent(content);
					message.setAuthor(userService.getUserById(author));
					message.setCreationDate(creationDate);
					message.setUpdateDate(updateDate);
					message.setStatus(Status.getStatus(status));
					listeMessage.add(message);
				}
				return listeMessage;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Message getMessage(Long id) {
		Message message = new Message();
		con = ConnectionDao.getConnection();
		if (con != null) {
			try {
				statement = con.createStatement();
				resultat = statement.executeQuery("Select * From MESSAGES Where ID = " + id);
				while (resultat.next()) {
					String title = resultat.getString(2);
					String content = resultat.getString(3);
					String author = resultat.getString(4);
					Timestamp creationDate = resultat.getTimestamp(5);
					Timestamp updateDate = resultat.getTimestamp(6);
					Integer status = resultat.getInt(7);

					message.setId(id);
					message.setTitle(title);
					message.setContent(content);
					message.setAuthor(userService.getUserById(author));
					message.setCreationDate(creationDate);
					message.setUpdateDate(updateDate);
					message.setStatus(Status.getStatus(status));
				}
				return message;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void addMessage(Message message) {
		con = ConnectionDao.getConnection();
		if (con != null) {
			try {
				statement = con.createStatement();
				String requete = "Insert into MESSAGES values (" + (getListOfMessages(new User()).size() + 1) + ",'"
						+ message.getTitle() + "','" + message.getContent() + "','" + message.getAuthor().getId()
						+ "',";
				if (message.getCreationDate() != null) {
					requete += "'" + message.getCreationDate() + "',";
				} else {
					requete += message.getCreationDate() + ",";
				}
				if (message.getUpdateDate() != null) {
					requete += "'" + message.getUpdateDate() + "',";
				} else {
					requete += message.getUpdateDate() + ",";
				}
				requete += message.getStatus().getValue() + ")";
				logger.debug(requete);
				statement.executeUpdate(requete);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateMessageStatus(Message message, int status) {
		con = ConnectionDao.getConnection();
		if (con != null && message.getId() != null) {
			try {
				statement = con.createStatement();
				String requete = "Update MESSAGES set STATUS = " + status + " where ID=" + message.getId();
				logger.debug(requete);
				statement.executeUpdate(requete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteMessage(Message message) {
		con = ConnectionDao.getConnection();
		if (con != null && message.getId() != null) {
			try {
				statement = con.createStatement();
				String requete = "Delete from MESSAGES where ID=" + message.getId();
				logger.debug(requete);
				statement.executeUpdate(requete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
