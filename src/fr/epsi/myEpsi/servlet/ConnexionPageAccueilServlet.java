package fr.epsi.myEpsi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.Message;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.service.ConnectionService;
import fr.epsi.myEpsi.service.MessageService;

/**
 * Servlet implementation class PageAccueil
 */
@WebServlet("/PageAccueil")
public class ConnexionPageAccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MessageService connectionService;
	private Logger logger = LogManager.getLogger(ConnexionPageAccueilServlet.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionPageAccueilServlet() {
        super();
        connectionService = new MessageService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Message>listeMessage = connectionService.getListOfMessages((User)request.getSession().getAttribute("user"));
		logger.info("listeMessage=" + listeMessage.size());
				request.setAttribute("messages", listeMessage);
		request.getRequestDispatcher("WEB-INF/jsp/pageAccueil.jsp").forward(request, response);
		

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
	
	}


	

}
