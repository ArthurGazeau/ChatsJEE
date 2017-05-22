package fr.epsi.myEpsi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.service.IUserService;
import fr.epsi.myEpsi.service.UserService;

public class UserTest {
	
	String id ="Alberta";
  
	static IUserService userService;
	@BeforeClass
	public static void beforeClass(){
		userService = new UserService();
	}
	
	@Test
	public void createDoubleAccount() {
		List t1 = userService.getListOfUsers();
		User user = new User();		
		user.setId(id);
		user.setPassword("TEST");
		user.setAdministrator(false);
		userService.addUser(user);
		userService.addUser(user);
		List t2 =userService.getListOfUsers();
		assertTrue(t1.size() == t2.size()-1 || t1.size() == t2.size());		
	}
	
	@Test
	public void deleteAdministrator() {
		List t1 = userService.getListOfUsers();
		User user = new User();		
		user.setId("ADMIN");
		userService.deleteUser(user);
		List t2 =userService.getListOfUsers();
		assertEquals(t1.size() , t2.size());	
	}
	
}
