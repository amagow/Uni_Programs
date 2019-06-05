/**
 * Dog animal in forest
 * @author adwithyamagow
 *
 */
class Dog extends Canine{
	protected Dog(int x, int y) {
		super(x,y);
	}
	/* (non-Javadoc)
	 * @see Animal#message()
	 */
	void message(){
		int[] pos = this.getPos();
		System.out.println("Added Dog at (" + pos[0] +"," + pos[1] +"): Dog is a Canine, Canine moves in four directions, one or two step at a time." );
	}
	/* (non-Javadoc)
	 * @see Animal#getID()
	 */
	String getID() {
		return "Dog";
	}
/*	Dog attacks(Dog a) {
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