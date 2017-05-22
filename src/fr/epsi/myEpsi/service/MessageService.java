package fr.epsi.myEpsi.service;

import java.util.List;

import fr.epsi.myEpsi.beans.Message;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.MessageDao;

public class MessageService implements IMessageService {

	private MessageDao messageDao;

	public MessageService() {
		this.messageDao = new MessageDao();
	}

	@Override
	public List<Message> getListOfMessages(User user) {
		// cas ou ce n'est pas un admin
		if (user.getId() == null) {
			return messageDao.getListOfMessages(user);

		} else if(user.getAdministrator()){
			user.setId(null);
			return messageDao.getListOfMessages(user);

		}else{
			return messageDao.getListOfMessages(user);
		}

		
	}

	@Override
	public Message getMessage(Long id) {
		return messageDao.getMessage(id);
	}

	@Override
	public void addMessage(Message message) {
		messageDao.addMessage(message);
	}

	@Override
	public void updateMessageStatus(Message message, int status) {
		messageDao.updateMessageStatus(message, status);

	}

	@Override
	public void deleteMessage(Message message) {
		Message messageToDelete = messageDao.getMessage(message.getId());
		if (messageToDelete.getAuthor().getId().equals(message.getAuthor().getId())) {
			messageDao.deleteMessage(message);
		} else {
			System.out.println("Seul les administrateurs peuvent supprimer les messages d'autre utilisateurs");
		}
	}

}
