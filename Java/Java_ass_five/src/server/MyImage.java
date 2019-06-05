/**
 * 
 */
package server;

import java.awt.Image;
import java.awt.Point;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author adwithyamagow
 *
 */
public class MyImage extends ImageIcon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point p;
	/**
	 * @return the p
	 */
	public Point getP() {
		return p;
	}

	/**
	 * @param p the point to set
	 */
	public void setP(Point p) {
		this.p = p;
	}

	/**
	 * 
	 */
	public MyImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyImage(Image i,Point p) {
		super(i);
		this.p = p;
	}
	/**
	 * @param filename
	 */
	public MyImage(String filename) {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param location
	 */
	public MyImage(URL location) {
		super(location);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 */
	public MyImage(Image image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param imageData
	 */
	public MyImage(byte[] imageData) {
		super(imageData);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param filename
	 * @param description
	 */
	public MyImage(String filename, String description) {
		super(filename, description);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param location
	 * @param description
	 */
	public MyImage(URL location, String description) {
		super(location, description);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 * @param description
	 */
	public MyImage(Image image, String description) {
		super(image, description);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param imageData
	 * @param description
	 */
	public MyImage(byte[] imageData, String description) {
		super(imageData, description);
		// TODO Auto-generated constructor stub
	}

}
