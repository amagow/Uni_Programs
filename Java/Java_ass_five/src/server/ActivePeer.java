package server;

import java.io.Serializable;

public class ActivePeer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private String IP;
	private int port;
	public ActivePeer() {
		super();
	}
	public ActivePeer(String IP, int port) {
		this.IP = IP;
		this.port = port;
	}
	/**
	 * get the IP address
	 * @return the IP address
	 */
	public String getIP() {
		return IP;
	}
	/**
	 * set the IP address
	 * @param IP address
	 */
	public void setIP(String iP) {
		IP = iP;
	}
	/**
	 * get Port
	 * @return Port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * set Port
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}
}
