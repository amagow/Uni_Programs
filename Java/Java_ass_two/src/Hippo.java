import java.util.Random;

/**
 * Fox animal for forest
 * @author adwithyamagow
 *
 */
class Hippo extends Animal{
	protected Hippo(int x, int y) {
		super(x,y);
	}
	/* (non-Javadoc)
	 * @see Animal#message()
	 */
	void message(){
		int[] pos = this.getPos();
		System.out.println("Added Hippo at (" + pos[0] +"," + pos[1] +"): Hippo is an Animal, Hippo moves in four directions, one step at a time." );
	}
	/* (non-Javadoc)
	 * @see Animal#getID()
	 */
	String getID() {
		return "Hippo";
	}
	/* (non-Javadoc)
	 * @see Animal#newPos()
	 */
	int[] newPos() {
		Random rand = new Random();
		int[] pos = getPos();
		int[] npos = new int[2];
		while(true) {
			int mov = rand.nextInt(4);
			switch(mov) {
			case 0:
				//Go up by 1
				npos[0] = pos[0] -1;
				npos[1] = pos[1];
				break;
			case 1:
				//Go down by 1
				npos[0]=1 + pos[0];
				npos[1] = pos[1];
				break;
			case 2:
				//Go right by 1
				npos[1]=1 + pos[1];
				npos[0] = pos[0];
				break;
			case 3:
				//Go left by 1
				npos[1] = pos[1] -1;
				npos[0] = pos[0];
				break;
		}
			if(npos[0] >= 0 && npos[0]<15 && npos[1] >= 0 && npos[1] < 15) {
				return npos;}
			}
	}
/*	Hippo attacks(Hippo a) {
		Random rand = new Random();
		float rnum = rand.nextFloat();
		int[] pos = getPos();
		int[] cpos = a.getPos();
		if(rnum<=0.5) {
			//Attacker wins 
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
			System.out.print(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
			return a;
		}
		else {
			//Attacker dies
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
			System.out.print(this.getID() + " dies at ("+ pos[0] +","+pos[1]+")");
			return this;
		}
	}*/
}