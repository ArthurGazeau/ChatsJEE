package fr.epsi.myEpsi.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.myEpsi.beans.Message;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.service.IMessageService;
import fr.epsi.myEpsi.service.MessageService;



public class MessageTest {
	
	static IMessageService messageService;
	@BeforeClass
	public static void beforeClass(){
		messageService = new MessageService();
	}
	

	@Test
	public void deleteOtherMessageUser() {
		User user = new User();
		user.setId("ADMIN");
		user.setAdministrator(false);
		
		Message message = new Message();
		message.setAuthor(user);
		message.setStatus(Status.PUBLIC);
		messageService.addMessage(message);
		
		List<Message> liste1 = messageService.getListOfMessages(user);
		liste1.get(liste1.size()-1);
		Message mess =liste1.get(liste1.size()-1);
		mess.getAuthor().setId("jhon");
		messageService.deleteMessage(mess);
		
		List<Message> liste2 = messageService.getListOfMessages(user);

		assertEquals(liste1.size() , liste2.size());

		
	}
}

