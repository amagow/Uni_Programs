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
			Cat c = new Cat(row,col);
			b.SetValue(row, col, c.getID().toLowerCase().charAt(0));
			aList.add(c);
			c.message();
			break;
		case 5:
			Lion l = new Lion(row,col);
			b.SetValue(row, col, l.getID().toLowerCase().charAt(0));
			aList.add(l);
			l.message();
			break;
		case 6:
			Tiger t = new Tiger(row,col);
			b.SetValue(row, col, t.getID().toLowerCase().charAt(0));
			aList.add(t);
			t.message();
			break;
		case 7:
			Hippo h = new Hippo(row,col);
			b.SetValue(row, col, h.getID().toLowerCase().charAt(0));
			aList.add(h);
			h.message();
			break;
		case 8:
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
		System.out.println("4.\tCat (c)");
		System.out.println("5.\tLion (l)");
		System.out.println("6.\tTiger (t)");
		System.out.println("7.\tHippo (h)");
		System.out.println("8.\tTurtle (u)");
		System.out.println("What would you like to add to the forest?");
		System.out.print("Please enter your choice (1-8, or 0 to finish the animal input) :");
	}
	private void moveAnimal(Animal a) {
		int[] dpos = a.getDeadPos();
		if(dpos[1]>=0 && dpos[0] >= 0) {
			return;
		}
		int[] pos =a.getPos();
		int[] npos = a.newPos();
		//if(npos[0] - pos[0] ==2 ||npos[0] - pos[0] ==-2||npos[1] - pos[1] ==2||npos[1] - pos[1] ==-2 ) {
	//		attackTwice(a,pos,npos);
	//	}
		//else {
			if(pos[0] == npos[0] && npos[1] == pos[1]) {
				a.notMoveMessage(npos);
			}
			else if(b.GetValue(npos[0], npos[1]) != '\u0000') {
				for(Animal c:aList) {
					if(!a.equals(c)) {
						int[] cpos = c.getPos();
						if(cpos[0] == npos[0] && cpos[1] == npos[1]) {
							Animal loser = a.attack(c);
							if(loser.equals(a)) {
								a.setPos(-1, -1);
								a.setDeadPos(pos[0], pos[1]);
								dList.add(a);
								b.SetValue(pos[0] , pos[1], '\u0000');
							}
							else {
								//Attacker wins
								c.setPos(-1, -1);
								c.setDeadPos(cpos[0], cpos[1]);
								dList.add(c);
								b.SetValue(pos[0] , pos[1], '\u0000');
								a.setPos(npos[0], npos[1]);
								if(a.getID() == "Turtle") {
									b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(1));}
								else {
									b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));}
								}
							}
						}	
					}
				}
				else {
					//When the given new position is free;
					a.moveMessage(npos);
					b.SetValue(pos[0] , pos[1], '\u0000');
					a.setPos(npos[0], npos[1]);
					if(a.getID() == "Turtle")
						b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(1));
					else
						b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));
				}
			//}
		}
	private void attackTwice(Animal a , int[] opos, int[] npos) {
		int[] oldposition = opos;
		int[] ipos = opos;
		int attacks =0;
		int moves =0;
		while(moves != 2) {
			if(npos[0]>ipos[0]) {
				int x = ipos[0];
				++ipos[0];
				if(b.GetValue(ipos[0], ipos[1]) == '\u0000') {
					if(moves == 1) {
						b.SetValue(x, opos[1], '\u0000');
						b.SetValue(ipos[0], ipos[1], a.getID().toLowerCase().charAt(0));
					}
					else {
						
					}
					++moves;
					if(moves == 2 && attacks < 2) {
						a.moveMessage(oldposition);
					}
					a.setPos(ipos[0], ipos[1]);
				}
				else {
					for(Animal c:aList) {
						if(!a.equals(c)) {
							int[] cpos = c.getPos();
							if(cpos[0] == npos[0] && cpos[1] == npos[1]) {
								Animal loser = a.attack(c);
								++attacks;
								++moves;
								if(moves == 2 && attacks < 2) {
									a.moveMessage(npos);
								}
								if(loser.equals(a)) {
									a.setPos(-1, -1);
									a.setDeadPos(opos[0], opos[1]);
									dList.add(a);
									b.SetValue(opos[0] , opos[1], '\u0000');
									return;
								}
								else {
									//Attacker wins
									c.setPos(-1, -1);
									c.setDeadPos(cpos[0], cpos[1]);
									dList.add(c);
									b.SetValue(opos[0] , opos[1], '\u0000');
									a.setPos(ipos[0], ipos[1]);
									b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));
									}
								}
							}
						}
					}
				}
			else if(npos[0]<ipos[0]) {
				int x = ipos[0];
				--ipos[0];
				if(b.GetValue(ipos[0], ipos[1]) == '\u0000') {
					b.SetValue(x, opos[1], '\u0000');
					b.SetValue(ipos[0], ipos[1], a.getID().toLowerCase().charAt(0));
					++moves;
					if(moves == 2 && attacks < 2) {
						a.moveMessage(oldposition);
					}
					a.setPos(ipos[0], ipos[1]);
				}
				else {
					for(Animal c:aList) {
						if(!a.equals(c)) {
							int[] cpos = c.getPos();
							if(cpos[0] == npos[0] && cpos[1] == npos[1]) {
								Animal loser = a.attack(c);
								++attacks;
								++moves;
								if(moves == 2 && attacks < 2) {
									a.moveMessage(npos);
								}
								if(loser.equals(a)) {
									a.setPos(-1, -1);
									a.setDeadPos(opos[0], opos[1]);
									dList.add(a);
									b.SetValue(opos[0] , opos[1], '\u0000');
									return;
								}
								else {
									//Attacker wins
									c.setPos(-1, -1);
									c.setDeadPos(cpos[0], cpos[1]);
									dList.add(c);
									b.SetValue(opos[0] , opos[1], '\u0000');
									a.setPos(ipos[0], ipos[1]);
									b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));
									}
								}
							}
						}
					}
				}
			else if(npos[1]>ipos[1]) {
				int x = ipos[1];
				++ipos[1];
				if(b.GetValue(ipos[0], ipos[1]) == '\u0000') {
					b.SetValue(opos[0], x, '\u0000');
					b.SetValue(ipos[0], ipos[1], a.getID().toLowerCase().charAt(0));
					++moves;
					if(moves == 2 && attacks < 2) {
						a.moveMessage(oldposition);
					}
					a.setPos(ipos[0], ipos[1]);
				}
				else {
					for(Animal c:aList) {
						if(!a.equals(c)) {
							int[] cpos = c.getPos();
							if(cpos[0] == npos[0] && cpos[1] == npos[1]) {
								Animal loser = a.attack(c);
								++attacks;
								++moves;
								if(moves == 2 && attacks < 2) {
									a.moveMessage(oldposition);
								}
								if(loser.equals(a)) {
									a.setPos(-1, -1);
									a.setDeadPos(opos[0], opos[1]);
									dList.add(a);
									b.SetValue(opos[0] , opos[1], '\u0000');
									return;
								}
								else {
									//Attacker wins
									c.setPos(-1, -1);
									c.setDeadPos(cpos[0], cpos[1]);
									dList.add(c);
									b.SetValue(opos[0] , opos[1], '\u0000');
									a.setPos(ipos[0], ipos[1]);
									b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));
									}
								}
							}
						}
					}
				}
			else if(npos[1]<ipos[1]) {
				int x = ipos[1];
				--ipos[1];
				if(b.GetValue(ipos[0], ipos[1]) == '\u0000') {
					b.SetValue(opos[0], x, '\u0000');
					b.SetValue(ipos[0], ipos[1], a.getID().toLowerCase().charAt(0));
					++moves;
					if(moves == 2 && attacks < 2) {
						a.moveMessage(oldposition);
					}
					a.setPos(ipos[0], ipos[1]);
				}
				else {
					for(Animal c:aList) {
						if(!a.equals(c)) {
							int[] cpos = c.getPos();
							if(cpos[0] == npos[0] && cpos[1] == npos[1]) {
								Animal loser = a.attack(c);
								++attacks;
								++moves;
								if(moves == 2 && attacks < 2) {
									a.moveMessage(npos);
								}
								if(loser.equals(a)) {
									a.setPos(-1, -1);
									a.setDeadPos(opos[0], opos[1]);
									dList.add(a);
									b.SetValue(opos[0] , opos[1], '\u0000');
									return;
								}
								else {
									//Attacker wins
									c.setPos(-1, -1);
									c.setDeadPos(cpos[0], cpos[1]);
									dList.add(c);
									b.SetValue(opos[0] , opos[1], '\u0000');
									a.setPos(ipos[0], ipos[1]);
									b.SetValue(npos[0], npos[1], a.getID().toLowerCase().charAt(0));
									}
								}
							}
						}
					}
				}
			}
		}
	}
