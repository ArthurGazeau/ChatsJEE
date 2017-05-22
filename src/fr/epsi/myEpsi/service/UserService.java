package fr.epsi.myEpsi.service;

import java.util.List;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.IUserDao;
import fr.epsi.myEpsi.dao.UserDao;

public class UserService implements IUserService {

	private IUserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	@Override
	public List<User> getListOfUsers() {
		List user = userDao.getListOfUsers();
		return user;
	}

	@Override
	public User getUserById(String id) {
		User user = userDao.getUserById(id);
		return user;
	}

	@Override
	public void addUser(User user) {
		if (!userDao.ifExist(user)) {
			userDao.addUser(user);
		} else {
			System.out.println("L'utilisateur existe déjà");
		}

	}

	@Override
	public void updateUser(User user) {
		User userToUpdate = getUserById(user.getId());
		if (userToUpdate != null) {
			if (user.getPassword().isEmpty()) {
				user.setPassword(userToUpdate.getPassword());
			}
			if (user.getAdministrator() == null) {
				user.setAdministrator(userToUpdate.getAdministrator());
			}
			userDao.updateUser(user);
		} else {
			System.out.println("L'utilisateur n'existe pas");
		}

	}

	@Override
	public void deleteUser(User user) {
		if (userDao.ifExist(user)) {
			User userAdmin = userDao.getUserById(user.getId());
			if (!userAdmin.getAdministrator()) {
				userDao.deleteUser(user);
			} else {
				System.out.println("Impossible de supprimer un Administrateur");
			}
		} else {
			System.out.println("L'utilisateur n'existe pas");
		}
	}

	@Override
	public boolean ifExist(User user) {
		return ifExist(user);

	}

}
