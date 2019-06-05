import java.util.Random;
public class Kitten extends Cat{

	public Kitten(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
	}
	/* (non-Javadoc)
	 * @see Cat#message()
	 */
	@Override
	void message(){
		int[] pos = this.getPos();
		System.out.println("Added Kitten at (" + pos[0] +"," + pos[1] +"): Kitten is Cat, Kitten has 30% chance stay in the same position, and 70% chance move in all eight directions, one step at a time.");
	}
	String getID() {
		return "Kitten";
	}
	/* (non-Javadoc)
	 * @see Feline#newPos()
	 */
	@Override
	int[] newPos() {
		Random rand = new Random();
		int[] pos = getPos();
		int[] npos = new int[2];
		//70% chance to move in 8 directions one step at a time
		if(rand.nextFloat() <= 0.7)
		{
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
		else {
			return pos;
		}
	}
	/* (non-Javadoc)
	 * @see Feline#attack(Animal)
	 */
	@Override
	public Animal attack(Animal a) {
		int[] pos = getPos();
		int[] cpos = a.getPos();
		//Kitten attacks kitten case
		if(a.getID() == "Kitten") {
			return super.attack(a);
		}
		//70% if kitten attacks cat it dies
		else if(a.getID() == "Cat"){
			Random rand = new Random();
			if(rand.nextFloat()<=0.7) {
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
				System.out.println(this.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
				return this;
			}
			else {
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
				System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
				return a;
			}
			
		}
		//Kitten dies when it attacks an animal
		else {
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
			System.out.println(this.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
			return this;
		}
	}
}
