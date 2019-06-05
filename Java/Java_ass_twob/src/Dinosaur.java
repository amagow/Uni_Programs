import java.util.Random;

public class Dinosaur extends Animal {

	public Dinosaur(int x, int y) {
		super(x, y);
	}

	/* (non-Javadoc)
	 * @see Animal#message()
	 */
	@Override
	void message() {
		int[] pos = this.getPos();
		System.out.println("Added Dinosaur at (" + pos[0] +"," + pos[1] +"): Dinosaur is an Animal, Dinosour moves in four directions, three steps a time.");
	}

	/* (non-Javadoc)
	 * @see Animal#getID()
	 */
	@Override
	String getID() {
		return "Dinosaur";
	}

	/* (non-Javadoc)
	 * @see Animal#newPos()
	 */
	@Override
	int[] newPos() {
		Random rand = new Random();
		int[] pos = getPos();
		int[] npos = new int[2];
		while(true) {
			int mov = rand.nextInt(4);
			switch(mov) {
			case 0:
				//Go up by 3
				npos[0] = pos[0] -3;
				npos[1] = pos[1];
				break;
			case 1:
				//Go down by 3
				npos[0]=3 + pos[0];
				npos[1] = pos[1];
				break;
			case 2:
				//Go right by 3
				npos[1]=3 + pos[1];
				npos[0] = pos[0];
				break;
			case 3:
				//Go left by 3
				npos[1] = pos[1] - 3;
				npos[0] = pos[0];
				break;
		}
		if(npos[0] >= 0 && npos[0]<15 && npos[1] >= 0 && npos[1] < 15) {
			return npos;
			}
		}
	}
	/* (non-Javadoc)
	 * @see Animal#attack(Animal)
	 */
	@Override
	public Animal attack(Animal a) {
		int[] pos = getPos();
		int[] cpos = a.getPos();
		if(a.getID() == "Dinosaur") {
			return super.attack(a);
		}
		//Animal dies
		else {
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
			System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
			return a;
		}
	}
}
