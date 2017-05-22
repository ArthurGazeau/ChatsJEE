package fr.epsi.myEpsi.jmx;

public interface NbMessageLogMBean {
	
	public String getNbMessage();
	
	public void ajouterMessage(String title, String content);

}
