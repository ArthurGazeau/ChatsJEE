package fr.epsi.myEpsi.jmx;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import fr.epsi.myEpsi.beans.Message;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.service.MessageService;
import fr.epsi.myEpsi.service.UserService;

public class NbMessageLog implements NbMessageLogMBean {

	private MessageService messageService;
	private UserService userService;

	public NbMessageLog() {
		this.messageService = new MessageService();
		this.userService = new UserService();
	}

	@Override
	public String getNbMessage() {
		List<Message> listeMessage = messageService.getListOfMessages(new User());
		return String.valueOf(listeMessage.size());
	}

	@Override
	public void ajouterMessage(String title, String content) {
		List<User> listeUser = userService.getListOfUsers();
		User admin = null;
		for (User u : listeUser) {
			if (u.getAdministrator()) {
				admin = u;
				break;
			}
		}

		Message message = new Message();
		//Gestion Id dans Dao
		message.setTitle(title);
		message.setContent(content);
		message.setAuthor(admin);
		message.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
		message.setUpdateDate(null);
		message.setStatus(Status.PUBLIC);
		
		messageService.addMessage(message);
		
	}

}
