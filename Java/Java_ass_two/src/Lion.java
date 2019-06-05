/**
 * Lion class for forest
 * @author adwithyamagow
 *
 */
class Lion extends Feline{
	protected Lion(int x, int y) {
		super(x,y);
	}
	/* (non-Javadoc)
	 * @see Animal#message()
	 */
	void message(){
		int[] pos = this.getPos();
		System.out.println("Added Lion at (" + pos[0] +"," + pos[1] +"): Lion is a Feline, Feline moves in all eight directions, one step a time.");
	}
	/* (non-Javadoc)
	 * @see Animal#getID()
	 */
	String getID() {
		return "Lion";
	}
	/* (non-Javadoc)
	 * @see Feline#attack(Animal)
	 */
	@Override
	public Animal attack(Animal a) {
		//Lion wins Hippo dies
		int[] pos = getPos();
		int[] cpos = a.getPos();
		if(a.getID() == "Hippo") {
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
			System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
			return a;
			}
		else
			return super.attack(a);
	}
/*	Lion attacks(Lion a) {
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
