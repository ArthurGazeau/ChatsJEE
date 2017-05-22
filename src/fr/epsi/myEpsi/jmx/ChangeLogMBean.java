package fr.epsi.myEpsi.jmx;

import org.apache.logging.log4j.Level;

public interface ChangeLogMBean {

	public String getNiveau();

	public void debug();

	public void info();

	public void error();

}

