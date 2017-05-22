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
import fr.epsi.myEpsi.service.UserService;


/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getLogger(ConnexionServlet.class);

	private UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
		logger.info("Init InscriptionServlet");
		userService = new UserService();
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
		String password2 = request.getParameter("confirmationPassword");
		if (password.equals(password2)) {
			User user = new User();
			user.setId(email);
			user.setPassword(password);
			user.setAdministrator(false);
			userService.addUser(user);
			//Rediriger sur les messages
		}else{
			request.getRequestDispatcher("WEB-INF/jsp/accueil.jsp").forward(request, response);
		}
	}
}
