import java.util.Random;
//Canine moves in four directions, one or two steps a time. 

/**
 * Abstract class for canine types
 * @author adwithyamagow
 *
 */
abstract class Canine extends Animal{
	protected Canine(int x,int y){
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
				//Go up by 2
				npos[0] = pos[0] -2;
				npos[1] = pos[1];
				break;
			case 5:
				//Go down by 2
				npos[0]=2 + pos[0];
				npos[1] = pos[1];
				break;
			case 6:
				//Go right by 2
				npos[1]=2 + pos[1];
				npos[0] = pos[0];
				break;
			case 7:
				//Go left by 2
				npos[1] = pos[1] -2;
				npos[0] = pos[0];
				break;
		}
			if(npos[0] >= 0 && npos[0]<15 && npos[1] >= 0 && npos[1] < 15) {
				return npos;}
			}
	}
	/* (non-Javadoc)
	 * @see Animal#attack(Animal)
	 */
	@Override
	public Animal attack(Animal a) {
		Random rand = new Random();
		float rnum = rand.nextFloat();
		int[] pos = getPos();
		int[] cpos = a.getPos();
		if(a.getID() == "Cat" || a.getID() == "Lion" || a.getID() == "Tiger") {
			if(rnum<=0.5) {
				//Canine dies and is returned
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and loses");
				System.out.println(this.getID() + " dies at ("+ pos[0] +","+pos[1]+")");
				return this;
			}
			else {
				//Feline dies and is returned
				System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
				System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
				return a;
			}
		}
		else 
			return super.attack(a);
	}
}