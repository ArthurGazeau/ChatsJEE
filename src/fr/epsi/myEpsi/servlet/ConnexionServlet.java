package fr.epsi.myEpsi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.ConnectionDao;
import fr.epsi.myEpsi.service.ConnectionService;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(ConnexionServlet.class);

	private ConnectionService connectionService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
		super();
		logger.info("Init ConnexionServlet");
		connectionService = new ConnectionService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User();
		user.setId(email);
		user.setPassword(password);
		if(connectionService.isAuthorized(user)){
			request.getSession().setAttribute("user", user);
			//acces liste des messages
			request.getRequestDispatcher("WEB-INF/jsp/pageAccueil.jsp").forward(request, response);
		}else{
			logger.error(email);
			request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
		}
	}

}
