package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Authentication_Module.User;


/**
 * Image server class
 * @author adwithyamagow
 *
 */
public class ImageServer{
	//Server Variables
	//private ArrayList<PrintWriter> clientOutputStreams;
	//private ArrayList<BufferedReader> clientInputStreams;
	private ArrayList<ActivePeer> activelist;
	private ArrayList<ObjectOutputStream> writers = new ArrayList<>();
	private ServerSocket ss;
	
	
	//Authentication module Variables
	private File f = new File("./User.txt");
	private ArrayList<User> Uarr = new ArrayList<User>();
	
	//GUI variables
	private File selectedFile;
	private BufferedImage selectedimg;
	private BufferedImage resizedimg;
	private List<JLabel> imgs;
	private ArrayList<MyImage> posimg = new ArrayList<>();
	private JFrame jf;
	private JPanel jp;
	private JLabel prevBtn;
	private JLabel currBtn;
	
	//Final Variables
	private final int sqside = 10;
	private final int NUMBER_OF_IMGS = 100;
	private final int JPHEIGHT = 700;
	private final int JPWIDTH = 700;
	public static void main(String[] args){
		ImageServer imgserv = new ImageServer();
		imgserv.getFile();
		imgserv.go();
	}

	private void getFile() {
		JFileChooser fileChooser = new JFileChooser();
		while(true) {
			int rval = fileChooser.showOpenDialog(null);
			if(rval == JFileChooser.CANCEL_OPTION)
				System.exit(-1);
			selectedFile = fileChooser.getSelectedFile();
			try {
				BufferedImage img = ImageIO.read(selectedFile);
				if(img != null) {
					selectedimg = img;
					break;
				}
				else {
					JOptionPane.showMessageDialog(null, "PLEASE SELECT AN IMAGE FILE!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "The file " + selectedFile + " could not be opened , an error occurred.", "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	private void go() {
		try {
			//Server Socket
			ss = new ServerSocket(9000);
			activelist = new ArrayList<>();
			doGUI();
			
			//server side
			ActivePeer a = new ActivePeer("localhost",9000);
			activelist.add(a);
			while(true) {
				Socket soc = ss.accept();
				//setupNetworking();
				Thread t = new Thread(new ClientHandler(soc));
				t.start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*private void setupNetworking() throws IOException {
		br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		pw = new PrintWriter(soc.getOutputStream());
	}*/

	private void doGUI() throws IOException, ParseException {
		//Reading information from User.txt
		String file = readFile();
		JSONParser parser = new JSONParser();
		JSONObject jobj = (JSONObject)parser.parse(file);
		JSONtoUarr(jobj);
		
		//Making JFrame
		jf = new JFrame("Image Server");
		
		//Making Button for JFrame
		JButton jb = new JButton("Load Another Image");
		jb.addActionListener(new LoadImageListener());
		jf.add(jb,BorderLayout.SOUTH);
		
		//Making Panel for the server Image
		jp =  new JPanel();
		jp.setPreferredSize(new Dimension(JPWIDTH, JPHEIGHT));
		jp.setLayout(new GridLayout(sqside, sqside,0,0));
		jp.setOpaque(false);
		jf.add(jp);
		
		//Resize Image
		resizedimg = resizeImage(selectedimg , JPWIDTH, JPHEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		
		imgs = new ArrayList<>();
		
		//Making filtered buttons and adding to imgs
		makeCropButtons();

		//Add buttons to panel
		buttonsToPanel();
		
		//Setting defaults for JFrame
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void JSONtoUarr(JSONObject obj) {
		JSONArray jarr = (JSONArray) obj.get("user_array");
		jarr.forEach(item -> {
			JSONObject jobj = (JSONObject) item;
			User u = new User();
			u.setUsername(jobj.get("username").toString());
			u.setPassword(jobj.get("hash_password").toString());
			u.setFullName(jobj.get("Full Name").toString());
			u.setEmailAdress(jobj.get("Email").toString());
			u.setPhoneNo(jobj.get("Phone number").toString());
			u.setFailLoginCount(Integer.parseInt(jobj.get("Fail count").toString()));
			u.setLastLoginDate(jobj.get("Last Login Date").toString());
			u.setAccLocked(Boolean.parseBoolean(jobj.get("Account locked").toString()));
			Uarr.add(u);
		});
	}
	
	private String readFile() 
			throws IOException {
		String line = null;
		BufferedReader	br = new BufferedReader(new FileReader(f));
		String         ls = System.getProperty("line.separator");
		StringBuilder  wholeString = new StringBuilder();
		while((line = br.readLine()) != null){
			wholeString.append(line);
			wholeString.append(ls);
		}
		br.close();
		return wholeString.toString();
		
	}
	
	private void buttonsToPanel() {
		for (int i = 0; i < NUMBER_OF_IMGS; i++) {

			JLabel imglabel = imgs.get(i);
			jp.add(imglabel);
			
			imglabel.addMouseListener(new swapListener());
		}

	}
	
private void makeCropButtons() {
		for (int i = 0; i < sqside; i++) {

			for (int j = 0; j < sqside; j++) {

				Image cropimage = jf.createImage(new FilteredImageSource(resizedimg.getSource(),
						new CropImageFilter(j * JPWIDTH/ sqside, i * JPHEIGHT / sqside,
								JPWIDTH/ sqside, JPHEIGHT/ sqside)));
				MyImage o = new MyImage(cropimage,new Point(i,j));
				JLabel label = new JLabel(new ImageIcon(cropimage));
				posimg.add(o);
				imgs.add(label);

			}
		}
	}

	private BufferedImage resizeImage(BufferedImage oldsource, int w, int h, int type) {
		BufferedImage resizedImage = new BufferedImage(w, h, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(oldsource, 0, 0, w, h, null);
		g.dispose();
		return resizedImage;
	}
	
	private class swapListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			prevBtn = (JLabel)e.getSource();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int pidx = imgs.indexOf(prevBtn);
			int cidx = imgs.indexOf(currBtn);
			MyImage o1 = posimg.get(pidx);
			o1.setP(new Point(cidx/10,cidx%10));
			MyImage o2 = posimg.get(cidx);
			o2.setP(new Point(pidx/10,pidx%10));
			Collections.swap(imgs, pidx, cidx);
			Collections.swap(posimg, pidx, cidx);
			updateButtons();
			for(ObjectOutputStream oos: writers) {
				try {
					oos.writeObject(o1);
					oos.writeObject(o2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currBtn = (JLabel)e.getSource();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		private void updateButtons() {
			jp.removeAll();
			for(JLabel jl : imgs) {
				jp.add(jl);
			}
			jp.validate();
		}
	}
	
	private class LoadImageListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
			int rvalue = fileChooser.showOpenDialog(null);
			if(rvalue == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				try {
					BufferedImage image = ImageIO.read(selectedFile);
					if (image != null) {
						jf.dispose();
						selectedimg = image;
						doGUI();
					}
					else {
						JOptionPane.showMessageDialog(null, "PLEASE SELECT AN IMAGE FILE!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException | ParseException ex) {
					JOptionPane.showMessageDialog(null, "The file " + selectedFile + " could not be opened , an error occurred.", "Error",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				} 
			}
		}
		
	}
	
	private String authenticateUser( String inputUsername, String hinputpw) throws IOException, NoSuchAlgorithmException {
		String s = null;
		
		int n = 0;
		for(User u: Uarr) {
			if (u.getUsername().equals(inputUsername)) {
				n++;
			}
		}
		if(n == 0)
			return "failed";
		
		for(User u: Uarr) {
			if (!u.isAccLocked()) {
				if (u.getUsername().equals(inputUsername)) {
					if (u.getPassword().equals(hinputpw)) {
						u.modifyDate();
						u.setFailLoginCount(0);
						s = "success";
						
					}
					else {
						int lcount = u.getFailLoginCount();
						if (++lcount >= 3) {
							u.setAccLocked(true);
							u.setFailLoginCount(lcount);
							s = "locked";
						} 
						else {
							u.setFailLoginCount(lcount);
							s = "failed";
						}
					} 
					break;
				} 
			}
			else {
				s = "locked";
			}
		}
		return s;	
	}
	
	private class ClientHandler implements Runnable{
		private Socket s;
		private ObjectOutputStream objoutstream;
		private ObjectInputStream objinstream;
		private ClientHandler(Socket soc) throws IOException {
			this.s = soc;
			objoutstream = new ObjectOutputStream(s.getOutputStream());
			objinstream = new ObjectInputStream(s.getInputStream());
			
		}
		@Override
		public synchronized void run() {
			try {
				
				String uname = (String)objinstream.readObject();
				String upass = (String)objinstream.readObject();
				String ver = authenticateUser(uname, upass);
				objoutstream.writeObject(ver);
				objoutstream.flush();
				
				/*for(JLabel jl: imgs) {
					objoutstream.writeObject(jl.getIcon());
					
					}
				objoutstream.flush();*/
				//PLease Change this
				ver = "success";
				
				if(ver.equals("success")) {
					addActivePeer();
					while(true) {
						Integer tint = (Integer)objinstream.readObject();
						MyImage o = posimg.get(tint);
						objoutstream.writeObject(o);
						//objoutstream.writeObject();
					}
				}
				else {
					Thread.currentThread().interrupt();
					return;
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		private synchronized void addActivePeer() throws IOException {
			int port_id = activelist.size() + 9000;
			ActivePeer newa = new ActivePeer(s.getInetAddress().getHostAddress(), port_id);
			objoutstream.writeObject(port_id);
			objoutstream.writeObject(activelist);
			activelist.add(newa);
			writers.add(objoutstream);
			updateEveryActive(newa);
			
		}
		private void updateEveryActive(ActivePeer a) throws IOException {
			Iterator<ObjectOutputStream> oi = writers.iterator();
			while(oi.hasNext()) {
				ObjectOutputStream oos = oi.next();
//				oos.reset();
				oos.writeObject(a);
				oos.flush();
			}
		}
	}
}
