import java.io.*;
import java.util.*;
/**
 * Forest object for the Forest game
 * @author adwithyamagow
 *
 */
public class Forest {
	private Board b = new Board();
	private ArrayList<Animal> aList = new ArrayList<Animal>();
	//private ArrayList<Integer> indexList = new ArrayList<Integer>();
	private ArrayList<Animal> dList = new ArrayList<Animal>();
	/**
	 * This is the main method for Forest game
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Forest f = new Forest();
		f.start();
	}
	private void start() {
		int num;
		String s ="";
		b.DrawBoard();
		outputAnimalOptions();
		while((num = inputs())!=0) {
			PlaceAnimal(num);
			b.DrawBoard();
			outputAnimalOptions();
		}
		Collections.sort(aList);
		while(!s.equals("exit")) {
			System.out.print("Press enter to iterate, type 'print' to print the Forest or 'exit' to quit:");
			s = input();
			if(s.equals("print")) {
				b.DrawBoard();
				continue;
			}
			if(!s.equals("exit") && !s.equals("print")) {
				for(Animal a:aList) {
					moveAnimal(a);//Create a function that returns all the index to 
				}
				for(int i =0;i<dList.size();i++) {
					Animal dead = dList.get(i);
					for(int j=0;j<aList.size();j++) {
						Animal tokill = aList.get(j);
						if(dead.equals(tokill)) {
							aList.remove(tokill);
							j--;
						}
					}
				}
				//indexList.clear();
				b.DrawBoard();
			}
		}
		b.DrawBoard();
		System.out.println();
		for(Animal a:dList) {
			int[] dpos = a.getDeadPos();
			System.out.println(a.getID() + " died at location (" + dpos[0] +","+dpos[1]+")");
		}
	}
	//This is for the numbers input when inputing animals
	private int inputs(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String str = br.readLine();
			return Integer.parseInt(str);
		} catch (IOException e) {
			System.out.println("Error while taking userInput");
			return -1;
		}
	}
	//This is for the commands after the animals have been inputed.
	private String input(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String str = br.readLine();
			return str;
		} catch (IOException e) {
			System.out.println("Error while taking userInput");
			return null;
		}
	}
	private void PlaceAnimal(int n) { //Do we have to move this?
		Random rand = new Random();
		int row,col;
		//This loop is to find a place for the animal that is unoccupied
		while(true) {
			row = rand.nextInt(15);
			col = rand.nextInt(15);
			if( b.GetValue(row, col) == '\u0000')
				break;
		}
		switch(n) {
		case 1:
			Dog d = new Dog(row,col);
			b.SetValue(row, col, d.getID().toLowerCase().charAt(0));
			aList.add(d);
			d.message();
			break;
		case 2:
			Fox f = new Fox(row,col);
			b.SetValue(row, col, f.getID().toLowerCase().charAt(0));
			aList.add(f);
			f.message();
			break;
		case 3:
			Wolf w = new Wolf(row,col);
			b.SetValue(row, col, w.getID().toLowerCase().charAt(0));
			aList.add(w);
			w.message();
			break;
		case 4:
			Jaguar j = new Jaguar(row,col);
			b.SetValue(row, col, j.getID().toLowerCase().charAt(0));
			aList.add(j);
			j.message();
			break;
		case 5:
			Lion l = new Lion(row,col);
			b.SetValue(row, col, l.getID().toLowerCase().charAt(0));
			aList.add(l);
			l.message();
			break;
		case 6:
			Cat c = new Cat(row,col);
			b.SetValue(row, col, c.getID().toLowerCase().charAt(0));
			aList.add(c);
			c.message();
			break;
		case 7:
			Kitten k = new Kitten(row,col);
			b.SetValue(row, col, k.getID().toLowerCase().charAt(0));
			aList.add(k);
			k.message();
			break;
		case 8:
			Dinosaur i = new Dinosaur(row,col);
			b.SetValue(row, col, i.getID().toLowerCase().charAt(1));
			aList.add(i);
			i.message();
			break;
		case 9:
			Turtle u = new Turtle(row,col);
			b.SetValue(row, col, u.getID().toLowerCase().charAt(1));
			aList.add(u);
			u.message();
			break;
		}
	}
	private void outputAnimalOptions() {
		System.out.println("1.\tDog (d)");
		System.out.println("2.\tFox (f)");
		System.out.println("3.\tWolf (w)");
		System.out.println("4.\tJaguar (j)");
		System.out.println("5.\tLion (l)");
		System.out.println("6.\tCat (c)");
		System.out.println("7.\tKitten (k)");
		System.out.println("8.\tDinosaur (i)");
		System.out.println("9.\tTurtle (u)");
		System.out.println("What would you like to add to the forest?");
		System.out.print("Please enter your choice (1-9, or 0 to finish the animal input) :");
	}
	private void moveAnimal(Animal a) {
		ArrayList<int[]> positions = new ArrayList<int[]>();
		boolean survived = true , animalthere = false;
		int[] dpos = a.getDeadPos();
		if(dpos[1]>=0 && dpos[0] >= 0) {
			return;
		}
		int[] pos =a.getPos();
		int[] npos = a.newPos();
		int [] ipos = new int[2];
		System.arraycopy(pos, 0, ipos, 0, pos.length);
		if(pos[0] == npos[0] && pos[1] == npos[1]) {
			a.notMoveMessage(npos);
			return;
		}
		while(ipos[0] != npos[0] || ipos[1] != npos[1]) {
			if(ipos[0] != npos[0]) {
				if(ipos[0] < npos[0]) {
					//Going Down
					ipos[0] = ipos[0] + 1;
				}
				else {
					//Going Up
					ipos[0] = ipos[0] - 1;
				}
			}
			else {
				if(ipos[1] < npos[1]) {
					//Going Right
					ipos[1] = ipos[1] + 1;
				}
				else {
					//Going Left
					ipos[1] = ipos[1] - 1;
				}
			}
			positions.add(ipos.clone());
		}
		for(int i = 0;i<positions.size();i++) {
			int[] poscheck = positions.get(i);
			if(b.GetValue(poscheck[0], poscheck[1]) != '\u0000') {
				animalthere = true;
			}
			else {
				positions.remove(i);
				--i;
			}
		}
		if(animalthere) {
			ipos = pos.clone();
			for(int[] arr:positions) {
				if(survived) {
					int[] newpos = arr;
					survived = Execattack( a , ipos ,newpos,survived);
					ipos = newpos;
				}
			}
			if(survived) {
				attackMoveMessage(a,pos,npos);
			}
		}
		else {
			//When the given new position is free;
			a.moveMessage(npos);
			b.SetValue(pos[0] , pos[1], '\u0000');
			a.setPos(npos[0], npos[1]);
			if(a.getID() == "Turtle" || a.getID() == "Dinosaur")
				b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(1));
			else
				b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));
		}
	}
	private boolean Execattack(Animal a , int[] pos, int[] npos,boolean survived) {
		survived = false;
			for(Animal c:aList) {
				if(!a.equals(c)) {
					int[] cpos = c.getPos();
					if(cpos[0] == npos[0] && cpos[1] == npos[1]) {
						Animal loser = a.attack(c);
						int[] lpos = loser.getPos();
						loser.setPos(-1, -1);
						loser.setDeadPos(lpos[0], lpos[1]);
						dList.add(loser);
						b.SetValue(pos[0] , pos[1], '\u0000');
						if(loser.equals(c)) {
							survived = true;
							a.setPos(npos[0], npos[1]);
							if(a.getID() == "Turtle" || a.getID() == "Dinosaur") {
								b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(1));
							}
							else {
								b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));
							}
						}
						return survived;
					}				
			}	
		}
			return survived;
	}
	private void attackMoveMessage(Animal a, int[]pos,int[] npos) {
		System.out.println(a.getID() +" moved from (" +pos[0]+"," + pos[1] + ") to (" + npos[0]+ ","+ npos[1] + ")");
	}
}