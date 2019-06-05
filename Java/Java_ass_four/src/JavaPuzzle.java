import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * My own image panel class
 * @author adwithyamagow
 *
 */
class MyImagePanel extends JPanel{
	
	private int sqside=10;
	/**
	 * Default constructor
	 */
	public MyImagePanel() {
		super();
		initUI();
	}
	private void initUI() {

		setPreferredSize(new Dimension(800, 800));
		setLayout(new GridLayout(sqside, sqside, 0, 0));
	}
}
/**
 * Java Puzzle class
 * @author adwithyamagow
 *
 */
public class JavaPuzzle {
	private File selectedFile;
	private BufferedImage source;
	private BufferedImage resized;
	private List<Point> solution;
	private List<JButton> imgs;
	private boolean valid = false;
	private Image cropimage;
	private JButton jb1;
	private JButton jb2;
	private JButton jb3;
	private JScrollPane scroller;
	private JTextArea jta;
	private MyImagePanel mip;
	private JPanel jpup;
	private JFrame jf;
	private int rwidth;
	private int rheight;
	private boolean previous;
	private JButton prevBtn;
	private JButton currBtn;
	private int sqside = 10;

	private final int HEIGHT = 800;
	private final int WIDTH = 800;
	private final int NUMBER_OF_IMGS = 100;
	/**
	 * Main for the class
	 * @param args
	 */
	public static void main(String[] args) {
		JavaPuzzle jp = new JavaPuzzle();
		jp.getFile();
		jp.go();
	}
	private void go() {
		//Load Image and Resize it
		try {
			source = loadImage();
			resized = resizeImage(source, WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_ARGB);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//getSolution
		makeSolution();

		//Get Resized image width and height
		rwidth = resized.getWidth(null);
		rheight = resized.getHeight(null);

		imgs = new ArrayList<>();
		//Make JFrame
		jf = new JFrame("Puzzle Image");

		//Make upper JPanel
		jpup = new JPanel();
		jpup.setLayout(new BorderLayout());
		jf.getContentPane().add(jpup);

		//Make new Image Panel
		mip = new MyImagePanel();
		jpup.add(mip);

		//Make new JText Area
		jta = new JTextArea(5, 20);
		jta.setLineWrap(true);
		jta.setText("Game Started!");
		jta.setEditable(false);

		//Make scroller for JTextArea
		scroller = new JScrollPane(jta);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jpup.add(scroller,BorderLayout.SOUTH);

		//Making filtered buttons and adding to imgs
		makeCropButtons();

		//Add buttons to panel
		buttonsToPanel();

		//Making bottom JPanel
		JPanel jpbot = new JPanel();

		//Make buttons and add to Panel
		jb1 = new JButton("Load Another Image");
		jb2 = new JButton("Show Original Image");
		jb3 = new JButton("Exit");
		jb1.addActionListener(new LoadImageListener());
		jb2.addActionListener(new ShowImageListener());
		jb3.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);

			}
		});
		jpbot.add(jb1);
		jpbot.add(jb2);
		jpbot.add(jb3);
		jf.getContentPane().add(jpbot,BorderLayout.SOUTH);


		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jf.pack();
		jf.setVisible(true);
	}
	private void makeCropButtons() {
		for (int i = 0; i < sqside; i++) {

			for (int j = 0; j < sqside; j++) {

				cropimage = jf.createImage(new FilteredImageSource(resized.getSource(),
						new CropImageFilter(j * rwidth/ sqside, i * rheight / sqside,
								rwidth/ sqside, rheight/ sqside)));
				JButton button = new JButton(new ImageIcon(cropimage));
				button.putClientProperty("position", new Point(i, j));
				button.putClientProperty("isLocked", false);
				imgs.add(button);

			}
			Collections.shuffle(imgs);
		}
		

	}
	private void buttonsToPanel() {
		for (int i = 0; i < NUMBER_OF_IMGS; i++) {

			JButton imgbtn = imgs.get(i);
			mip.add(imgbtn);
			imgbtn.setBorder(BorderFactory.createLineBorder(Color.gray));
			imgbtn.addMouseListener(new swapListener());
		}

	}
	private BufferedImage resizeImage(BufferedImage oldsource, int w, int h, int type) {
		BufferedImage resizedImage = new BufferedImage(w, h, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(oldsource, 0, 0, w, h, null);
		g.dispose();
		return resizedImage;
	}
	private BufferedImage loadImage() throws IOException{
		BufferedImage bimg = ImageIO.read(selectedFile);
		return bimg;
	}
	private void makeSolution() {
		solution = new ArrayList<>();
		for(int i = 0;i<sqside;i++) {
			for(int j=0;j<sqside;j++) {
				solution.add(new Point(i,j));
			}
		}
	}
	private void getFile() {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		while (!valid) {
			int rvalue = fileChooser.showOpenDialog(null);
			if (rvalue == JFileChooser.CANCEL_OPTION) {
				System.exit(-1);
			}
			selectedFile = fileChooser.getSelectedFile();
			try {
				Image image = ImageIO.read(selectedFile);
				if (image != null) {
					valid = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "PLEASE SELECT AN IMAGE FILE!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException ex) {
				System.out.println("The file " + selectedFile + " could not be opened , an error occurred.");
			} 
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
					Image image = ImageIO.read(selectedFile);
					if (image != null) {
						jf.dispose();
						go();
					}
					else {
						JOptionPane.showMessageDialog(null, "PLEASE SELECT AN IMAGE FILE!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException ex) {
					System.out.println("The file " + selectedFile + " could not be opened , an error occurred.");
				} 
			}
		}
		
	}
	private class ShowImageListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			OrigImageFrame f = new OrigImageFrame();
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.pack();
			f.setVisible(true);
			f.setSize(new Dimension(WIDTH, HEIGHT));

		}

	}
	private class OrigImageFrame extends JFrame{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6951599119344556942L;

		/**
		 * Default constructor for class
		 */
		public OrigImageFrame() {

			OrigImagePanel  panel= new OrigImagePanel();
			panel.setLayout(new BorderLayout());
			panel.setSize(new Dimension(WIDTH, HEIGHT));
			add(panel);
		}
	}
	private class OrigImagePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = -1268771141802568930L;

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(resized,0,0,this);
		}
	}
	private class swapListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			prevBtn = (JButton)e.getSource();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (!((boolean)prevBtn.getClientProperty("isLocked") ||(boolean)currBtn.getClientProperty("isLocked"))) {
				//JButton currentBtn = (JButton) e.getSource();
				int pidx = imgs.indexOf(prevBtn);
				int cidx = imgs.indexOf(currBtn);
				Collections.swap(imgs, pidx, cidx);
				updateButtons();
				
				int idx = imgs.indexOf(prevBtn);
			
				if ((new Point(idx/sqside,idx%sqside)).equals((Point) prevBtn.getClientProperty("position"))) {
					prevBtn.putClientProperty("isLocked", true);
					jta.append("\nImage block in correct position!");
				}
				checkSolution();
			}
			else {
				prevBtn = null;
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currBtn = (JButton)e.getSource();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		private void updateButtons() {
			mip.removeAll();
			for(JButton jb : imgs) {
				mip.add(jb);
			}
			mip.validate();
		}
		private void checkSolution() {
			List<Point> current = new ArrayList<>();

	        for (JComponent btn : imgs) {
	            current.add((Point) btn.getClientProperty("position"));
	        }
	        if (compareList(solution, current)) {
	            JOptionPane.showMessageDialog(jf, "You win!!!",
	                    "Message", JOptionPane.INFORMATION_MESSAGE);
	        }
		}
		private boolean compareList(List ls1, List ls2) {
	        
	        return ls1.toString().contentEquals(ls2.toString());
	    }
	}
}

