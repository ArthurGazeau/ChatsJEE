package fr.epsi.myEpsi.service;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.ConnectionDao;

public class ConnectionService implements IConnectionService {

	private ConnectionDao connectionDao;

	public ConnectionService() {
		super();
		this.connectionDao = new ConnectionDao();
	}

	@Override
	public boolean isAuthorized(User user) {
		return connectionDao.isAuthorized(user);
	}

}
