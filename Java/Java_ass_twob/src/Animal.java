import java.util.*;
/**
 * Abstract animal class
 * @author adwithyamagow
 *
 */
abstract class Animal implements Comparable<Animal>,AttackRules{
	//private int count =0;
	private int dcol=-1;
	private int drow=-1;
	private int row,col;
	/**
	 * Contructor for Animal
	 * @param x
	 * @param y
	 */
	protected Animal(int x,int y) {
		this.row = x;
		this.col = y;
	}
	 /**
	 * Custom message on the animal
	 */
	abstract void message();
	 /**
	  * This is a getter for the dead position of the animal
	 * @return an integer array with the row and column the animal died at
	 */
	int[] getDeadPos() {
			int[] pos = {drow,dcol};
			return pos;
		}
		/**
		 * Set the dead row and column for the animal
		 * @param x:row
		 * @param y:column
		 */
		void setDeadPos(int x,int y) {
			this.drow =x;
			this.dcol = y;
		}
	/**
	 * Getter for the alive position of the animal
	 * @return integer array with the row and column of the alive animal position
	 */
	int[] getPos() {
		int[] pos = {row,col};
		return pos;
	}
	/**
	 * Set the new row and column for the alive animal
	 * @param x:row
	 * @param y:column
	 */
	void setPos(int x,int y) {
		this.row =x;
		this.col = y;
	}
	/**
	 * Get the ID of an animal
	 * @return ID of the animal
	 */
	abstract String getID();
	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Animal a) {
		return getID().compareTo(a.getID());
	}
	/**
	 * Displays the message of the animal moving
	 * @param new position of the animal
	 */
	void moveMessage(int[] npos) {
		int[] pos = new int[2];
		pos = this.getPos();
		System.out.println( this.getID() +" moved from (" +pos[0]+"," + pos[1] + ") to (" + npos[0]+ ","+ npos[1] + ")");
		}
	/**
	 * Calculates where the animal should move next
	 * @return int array with row and column of where the animal should move next
	 */
	abstract int[] newPos();
	/**
	 * Attacks another animal
	 * @param animal to attack
	 */
	public Animal attack(Animal a) {
		Random rand = new Random();
		float rnum = rand.nextFloat();
		int[] pos = getPos();
		int[] cpos = a.getPos();
		if(this.getID() == a.getID()) {
			if(rnum<=0.5) {
				//Attacker wins 
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
				System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
				return a;
			}
			else {
				//Attacker dies
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
				System.out.println(this.getID() + " dies at ("+ pos[0] +","+pos[1]+")");
				return this;
			}
		}
		else if(a.getID() == "Turtle") {
			if(rnum<=0.2) {
				//Animal wins
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
				System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
				return a;
			}
			//Turtule wins
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
			System.out.println(this.getID() + " dies at ("+ pos[0] +","+pos[1]+")");
			return this;
		}
		else {
			//Attacker dies
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
			System.out.println(this.getID() + " dies at ("+ pos[0] +","+pos[1]+")");
		return this;
		}
	
	}
	/*public Animal attack(Turtle a) {
		Random rand = new Random();
		int[] pos = getPos();
		int[] cpos = a.getPos();
		if(rand.nextFloat()<=0.2) {
			//Animal wins
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
			System.out.print(a.getID() + "dies at ("+ cpos[0] +","+cpos[1]+")");
			return a;
		}
		//Turtule wins
		System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
		System.out.print(this.getID() + " dies at ("+ pos[0] +","+pos[1]+")");
		return this;
	}*/
	/**
	 * Displays a message when the animal does not move
	 * @param position the animal stays at
	 */
	void notMoveMessage(int[] npos) {
		int[] pos = new int[2];
		pos = this.getPos();
		System.out.println( this.getID() +" stayed in (" +pos[0]+"," + pos[1] + ")");
		}
}