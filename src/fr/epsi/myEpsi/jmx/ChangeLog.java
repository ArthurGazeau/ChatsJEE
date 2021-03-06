package fr.epsi.myEpsi.jmx;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class ChangeLog implements ChangeLogMBean {

	private Level niveau;
	private Logger logger = LogManager.getLogger(ChangeLog.class);
	private LoggerContext ctx;
	private Configuration config;
	private LoggerConfig loggerConfig;

	public ChangeLog() {
		ctx = (LoggerContext) LogManager.getContext(false);
		config = ctx.getConfiguration();
		loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
		niveau = loggerConfig.getLevel();

	}

	@Override
	public String getNiveau() {
		return niveau.toString();
	}

	@Override
	public void debug() {
		loggerConfig.setLevel(Level.DEBUG);
		ctx.updateLoggers();
		niveau = loggerConfig.getLevel();
		
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
	}

	@Override
	public void info() {
		loggerConfig.setLevel(Level.INFO);
		ctx.updateLoggers();
		niveau = loggerConfig.getLevel();
		
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
	}

	@Override
	public void error() {
		loggerConfig.setLevel(Level.ERROR);
		ctx.updateLoggers();
		niveau = loggerConfig.getLevel();
		
		logger.debug("debug");
		logger.info("info");
		logger.error("error");
	}

}

