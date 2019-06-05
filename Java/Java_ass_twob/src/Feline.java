import java.util.Random;

/**
 * Abstract class Feline for forest
 * @author adwithyamagow
 *
 */
abstract class Feline extends Animal{
	protected Feline(int x, int y) {
		super(x,y);
	}
		/* (non-Javadoc)
		 * @see Animal#newPos()
		 */
		int[] newPos() {
			Random rand = new Random();
			int[] pos = getPos();
			int[] npos = new int[2];
			while(true) {
				int mov = rand.nextInt(8);
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
				case 4:
					//Go right and up by 1
					npos[1]=1 + pos[1];
					npos[0] = pos[0] -1;
					break;
				case 5:
					//Go right and down by 1
					npos[1]=1 + pos[1];
					npos[0]=1 + pos[0];
					break;
				case 6:
					//Go left and up by 1
					npos[0] = pos[0] -1;
					npos[1] = pos[1] -1;
					break;
				case 7:
					//Go left and down by 1
					npos[0]=1 + pos[0];
					npos[1] = pos[1] -1;
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
			//Canine is returned
			int[] pos = getPos();
			int[] cpos = a.getPos();
			if(a.getID() == "Dog" || a.getID() == "Fox" || a.getID() == "Wolf") {
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
				System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
				return a;	
			}
			else
				return super.attack(a);
		}
}