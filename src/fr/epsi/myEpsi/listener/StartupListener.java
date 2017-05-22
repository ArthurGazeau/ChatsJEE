package fr.epsi.myEpsi.listener;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.jmx.ChangeLog;
import fr.epsi.myEpsi.jmx.NbMessageLog;
import fr.epsi.myEpsi.jmx.Premier;
import fr.epsi.myEpsi.service.MessageService;
import fr.epsi.myEpsi.service.UserService;

/**
 * Application Lifecycle Listener implementation class StartupListener
 *
 */
@WebListener
public class StartupListener implements ServletContextListener {

	private Logger logger = LogManager.getLogger(StartupListener.class);

	/**
	 * Default constructor.
	 */
	public StartupListener() {
		// TODO Auto-generated constructor stub
		MessageService messageService = new MessageService();
		logger.error("Nb message " + messageService.getListOfMessages(new User()).size());

		UserService userService = new UserService();
		logger.error("Nb user " + userService.getListOfUsers().size());

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName namePremier = null;
		ObjectName nameChangeLog = null;
		ObjectName nameNbMessageLog = null;

		try {
			namePremier = new ObjectName("fr.epsi.myEpsi.jmx:type=PremierMBean");
			Premier mbean = new Premier();

			mbs.registerMBean(mbean, namePremier);

			nameChangeLog = new ObjectName("fr.epsi.myEpsi.jmx:type=ChangeLogMBean");
			ChangeLog changeLog = new ChangeLog();

			mbs.registerMBean(changeLog, nameChangeLog);

			nameNbMessageLog = new ObjectName("fr.epsi.myEpsi.jmx:type=NbMessageLogMBean");
			NbMessageLog nbMessageLog = new NbMessageLog();

			mbs.registerMBean(nbMessageLog, nameNbMessageLog);

		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}
	}

}
