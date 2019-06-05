
public class Jaguar extends Feline{

	public Jaguar(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
	}

	@Override
	void message() {
		// TODO Auto-generated method stub
		int[] pos = this.getPos();
		System.out.println("Added Jaguar at (" + pos[0] +"," + pos[1] +"): Jaguar is a Feline, Feline moves in all eight directions, one step a time.");
	}

	@Override
	String getID() {
		// TODO Auto-generated method stub
		return "Jaguar";
	}
	@Override
	public Animal attack(Animal a) {
		//If Jaguar attacks Turtle, Jaguar wins and turtle dies
		int[] pos = getPos();
		int[] cpos = a.getPos();
		if(a.getID() == "Turtle") {
			System.out.println(this.getID() + " from (" + pos[0] +","+pos[1]+") attacks " + a.getID() + " at (" + cpos[0] +","+cpos[1]+") and wins");
			System.out.println(a.getID() + " dies at ("+ cpos[0] +","+cpos[1]+")");
			return a;
			}
		else
			return super.attack(a);
	}
}
