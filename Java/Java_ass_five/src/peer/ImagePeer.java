package peer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Authentication_Module.Hash;
import Authentication_Module.User;
import server.ActivePeer;
import server.MyImage;

import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Image Peer Class
 * @author adwithyamagow
 *
 */
public class ImagePeer implements Hash{
	//Server Variables
	private Integer port;
	private boolean flag = false;
	private String serv;
	private String uname;
	private String upass;
	private ServerSocket ss;
	private Socket s;
	private ObjectOutputStream objoutstream;
	private ObjectInputStream objinstream;
	private ArrayList<ActivePeer> active;
	private ArrayList<ObjectOutputStream> writers = new ArrayList<>();
	private ArrayList<Integer> needImgs = new ArrayList<>();

	//Final variables
	private final int sqside = 10;
	private final int NUMBER_OF_IMGS = 100;
	private final int WIDTH = 700;
	private final int HEIGHT = 700;

	//GUI variables
	JLabel[][] panelHolder = new JLabel[sqside][sqside];    
	//private List<JLabel> imgs;
	private JFrame jf;
	private JPanel jp;


	public static void main(String[] args) {
		ImagePeer ip = new ImagePeer();
		ip.go();
	}

	@SuppressWarnings("unchecked")
	private synchronized void go() {
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "OK");
		serv = JOptionPane.showInputDialog(null, "Connecting to server:");
		uname = JOptionPane.showInputDialog(null, "Username");
		upass = JOptionPane.showInputDialog(null, "Password");
		try {
			upass = hashPassword(upass);
			setupNetworking();
			//System.out.println("Network Established");
			objoutstream.writeObject(uname);
			objoutstream.writeObject(upass);
			objoutstream.flush();
			String message = (String)objinstream.readObject();

			//Please change this before submitting
			message = "success";

			if(message.equals("success")) {			
				/*while(true) {
					ImageIcon ii = (ImageIcon) objinstream.readObject();
					JLabel jl = new JLabel(ii);
					jp.add(jl);
					jp.validate();

				}*/
				while(true) {
					if(!flag) {
						for(int i = 0; i<100;i++) {
							needImgs.add(i);
						}
						writers.add(objoutstream);
						port = (Integer)objinstream.readObject();
						active= (ArrayList<ActivePeer>) objinstream.readObject();
						startGUI();
						ss = new ServerSocket(port);
						Thread t = new Thread(new ClientAccepter(ss));
						t.start();
						for(ActivePeer ea: active){
							if(ea.getPort() != 9000 && ea.getPort() != port) {
								try {
									Thread tt = new Thread(new PeerConnector(ea.getIP(), ea.getPort()));
									tt.start();
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
						t = new Thread(new ClientTalker(s,objinstream,objoutstream));
						t.start();
						flag = true;
					}
					else {
						Object obj= (Object) objinstream.readObject();
						if(obj instanceof ActivePeer) {
							ActivePeer objactive = (ActivePeer) obj;
							if(!active.contains(objactive)) {
								active.add(objactive);
							}
						}
						else if(obj instanceof MyImage) {
							if(!needImgs.isEmpty()) {
								MyImage ii = (MyImage)obj;
								if(ii != null) {
									Point p1 = ii.getP();
									panelHolder[p1.x][p1.y].setIcon(ii);
									}
							}
							else {
								MyImage ii = (MyImage)obj;
								Point p1 = ii.getP();
								panelHolder[p1.x][p1.y].setIcon(null);
								panelHolder[p1.x][p1.y].setIcon(ii);
							}
						}
						//						Iterator<?> gi = active.iterator();
						//						jp.removeAll();
						//						for(ActivePeer opop: newactive)
						//						{
						//							jp.add(new JButton());
						//						}
						//						jp.validate();
					}
				}



			}
			else if(message.equals("locked")){
				JOptionPane.showMessageDialog(null, "Account Locked", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(message.equals("failed")) {
				JOptionPane.showMessageDialog(null, "Login Fail", "Message", JOptionPane.INFORMATION_MESSAGE);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startGUI() {
		jf = new JFrame("Image Peer #" +  (port.intValue() - 9000));
		jf.setSize(new Dimension(WIDTH, HEIGHT));
		jp =  new JPanel();
		jp.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		jp.setSize(new Dimension(WIDTH, HEIGHT));
		jp.setLayout(new GridLayout(sqside, sqside,0,0));
		jf.add(jp);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int m = 0; m < sqside; m++) {
			for(int n = 0; n < sqside; n++) {
				panelHolder[m][n] = new JLabel();
				jp.add(panelHolder[m][n]);
			}
		}

	}

	private void setupNetworking() throws UnknownHostException, IOException {
		s = new Socket(serv, 9000);
		objoutstream = new ObjectOutputStream(s.getOutputStream());
		objinstream = new ObjectInputStream(s.getInputStream());
	}

	/* (non-Javadoc)
	 * @see Authentication_Module.Hash#hashPassword(java.lang.String)
	 */
	@Override
	public String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] b = md.digest();
		StringBuffer sb = new StringBuffer();
		for(byte b1 : b){
			sb.append(Integer.toHexString(b1 & 0xff).toString());

		}
		return sb.toString();
	}

	private class ClientAccepter extends Thread{
		private ServerSocket ss;
		private ClientAccepter(ServerSocket servsocket) throws IOException {
			ss = servsocket;
		}
		@Override
		public void run() {
			while(true) {
				try {
					Socket s = ss.accept();
					Thread t = new Thread(new ClientHandler(s));
					t.start();
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				}

			}
		}
	}
	//Run from Main loop
	//For sending out information it wants
	private class PeerConnector extends Thread{
		private Socket s;
		private ObjectOutputStream objoutstream;
		private ObjectInputStream objinstream;
		private PeerConnector(String stringip,int n)  {
			InetAddress inetAddress;
			try {
				inetAddress = InetAddress.getByName(stringip);
				s = new Socket(inetAddress, n);
			} catch ( IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		@Override
		public synchronized void run() {
			try {
				setupNetworking();
				while(!needImgs.isEmpty()) {
					Random r = new Random();
					int pos = r.nextInt(100);
					int i = needImgs.get(pos);
					objoutstream.writeObject(i);
					objoutstream.flush();
					needImgs.remove(pos);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		private void setupNetworking() throws IOException {
			objoutstream = new ObjectOutputStream(s.getOutputStream());
			writers.add(objoutstream);
			objinstream = new ObjectInputStream(s.getInputStream());
		}
	}
	//Run from socket loop 
	//Reads what it wants and then provides it that information
	private class ClientHandler extends Thread{
		private Socket s;
		private ObjectOutputStream objoutstream;
		private ObjectInputStream objinstream;
		private ClientHandler(Socket soc) throws IOException {
			this.s = soc;
		}
		@Override
		public synchronized void run() {
			try {
				setupNetworking();
				Object obj= (Object) objinstream.readObject();
				if(obj instanceof MyImage) {
					MyImage ii = (MyImage)obj;
					if(ii != null) {
						Point p1 = ii.getP();
						panelHolder[p1.x][p1.y].setIcon(ii);
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		private void setupNetworking() throws IOException {
			objoutstream = new ObjectOutputStream(s.getOutputStream());
			writers.add(objoutstream);
			objinstream = new ObjectInputStream(s.getInputStream());
		}
	}
	private class ClientTalker extends Thread{
		private Socket s;
		private ObjectOutputStream objoutstream;

		private ClientTalker(Socket s,ObjectInputStream i, ObjectOutputStream o) {
			this.s = s;
			objoutstream = o;
		}
		@Override
		public synchronized void run() {
			try {
				while(!needImgs.isEmpty()) {
					Random r = new Random();
					int pos = r.nextInt(100);
					int i = needImgs.get(pos);
					objoutstream.writeObject(i);
					objoutstream.flush();
					needImgs.remove(pos);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
