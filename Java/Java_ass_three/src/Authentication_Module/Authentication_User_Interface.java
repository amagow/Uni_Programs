package Authentication_Module;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * This is the Authentication User Module
 * @author adwithyamagow
 *
 */
public class Authentication_User_Interface implements Hash{
	private File f = new File("./User.txt");
	private ArrayList<User> Uarr = new ArrayList<User>();
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ParseException {
		Authentication_User_Interface obj = new Authentication_User_Interface();
		obj.driver();
	}

	/* (non-Javadoc)
	 * @see Hash#hashPassword(java.lang.String)
	 */
	@Override
	public String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(password.getBytes());
		byte[] b = md.digest();
		StringBuffer sb = new StringBuffer();
		for(byte b1 : b){
			sb.append(Integer.toHexString(b1 & 0xff).toString());
			
		}
		return sb.toString();
	}
	private void driver() throws NoSuchAlgorithmException, IOException, ParseException {
		boolean exists = f.exists();
		if(!exists) {
			f.createNewFile();
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			pw.print("{\"user_array\":[ ]}");
			pw.close();
		}
		else {
			String file = this.readFile();
			JSONParser parser = new JSONParser();
			JSONObject jobj = (JSONObject)parser.parse(file);
			this.JSONtoUarr(jobj);
		}
		String numInput;
		this.printMenu();
		try {
			numInput = this.getInput();
			int n = Integer.parseInt(numInput);
			while (n < 5) {
				switch (n) {
				case 1:
					this.authenticateUser(n);
					break;
				case 2:
					this.addUser();
					break;
				case 3:
					this.authenticateUser(n);
					break;
				case 4:
					this.resetPass();
					break;
				case 0:
					this.writeAllUsersToFile();
					break;
				default:
					System.out.println("Please Input a number between 1-4, or 0");
					break;
				}
				if(n ==0)
					break;
				System.out.print("Please enter your command (1-4, or 0 to terminate the system): ");
				numInput = this.getInput();
				n = Integer.parseInt(numInput);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	private void JSONtoUarr(JSONObject obj) {
		JSONArray jarr = (JSONArray) obj.get("user_array");
		jarr.forEach(item -> {
			JSONObject jobj = (JSONObject) item;
			User u = new User();
			u.setUsername(jobj.get("username").toString());
			u.setPassword(jobj.get("hash_password").toString());
			u.setFullName(jobj.get("Full Name").toString());
			u.setEmailAdress(jobj.get("Email").toString());
			u.setPhoneNo(jobj.get("Phone number").toString());
			u.setFailLoginCount(Integer.parseInt(jobj.get("Fail count").toString()));
			u.setLastLoginDate(jobj.get("Last Login Date").toString());
			u.setAccLocked(Boolean.parseBoolean(jobj.get("Account locked").toString()));
			Uarr.add(u);
		});
	}
	private void printMenu() {
		System.out.print("Welcome to the COMP2396 Authentication system! \n"+
				"1. Authenticate user\n" + 
				"2. Add user record\n" + 
				"3. Edit user record\n" + 
				"4. Reset user password\n" + 
				"What would you like to perform?\n" + 
				"Please enter your command (1-4, or 0 to terminate the system): ");
	}
	private String getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	private boolean isCorrectFormatpw(String s) {
		int len = s.length();
		
		if (len > 5) {
			boolean capital = false, small = false, digit = false;
			for (int i = 0; i < len; i++) {
				if (s.charAt(i) >= '0' && s.charAt(i)<='9' ) {
					digit = true;
				}
				if (s.charAt(i) >= 'a' && s.charAt(i)<='z' ) {
					small = true;
				}
				if (s.charAt(i) >= 'A' && s.charAt(i)<='Z' ) {
					capital = true;
				}
			}
			if(capital == true && small == true && digit == true)
				return true;
			return false;
		}
		else
			return false;
	}
	private void addUser() throws IOException, NoSuchAlgorithmException, ParseException{
		User u = new User();
		System.out.print("Please enter your username: ");
		u.setUsername(this.getInput());
		u.setPassword(this.addPassword());
		System.out.print("Please enter your full name: ");
		u.setFullName(this.getInput());
		System.out.print("Please enter your email address: ");
		u.setEmailAdress(this.getInput());
		System.out.print("Please enter your Phone number: ");
		u.setPhoneNo(this.getInput());
		System.out.println("Record added successfully!");
		u.modifyDate();
		Uarr.add(u);
	}
	private String addPassword() throws IOException, NoSuchAlgorithmException {
		String s3 = null;
		System.out.print("Please enter your password: ");
		String s = this.getInput();
		while (!this.isCorrectFormatpw(s)) {
			System.out.println("Your password has to fulfil: at least 1 small letter, 1 capital letter, 1 digit!");
			System.out.print("Please enter your password: ");
			s = this.getInput();
		}
		System.out.print("Please re-enter your password: ");
		String s2 = this.getInput();
		if(!s2.equals(s)) {
			System.out.println("Please enter the same password as before!");
			s3 = this.addPassword();
		}
		if(s3 == null) 
			return this.hashPassword(s);
		else
			return this.hashPassword(s3);
	}
	private String addNewPassword() throws IOException, NoSuchAlgorithmException {
		String s3 = null;
		System.out.print("Please enter your new password: ");
		String s = this.getInput();
		while (!this.isCorrectFormatpw(s)) {
			System.out.println("Your password has to fulfil: at least 1 small letter, 1 capital letter, 1 digit!");
			System.out.print("Please enter your new password: ");
			s = this.getInput();
		}
		System.out.print("Please re-enter your new password: ");
		String s2 = this.getInput();
		if(!s2.equals(s)) {
			System.out.println("Please enter the same password as before!");
			s3 = this.addPassword();
		}
		if(s3 == null) 
			return this.hashPassword(s);
		else
			return this.hashPassword(s3);
	}
	@SuppressWarnings("unchecked")
	private void writeAllUsersToFile() {
		JSONObject jobj = new JSONObject();
		JSONArray jarr = new  JSONArray();
		for(User u: Uarr) {
			JSONObject jobj2 = createNewJSONObj(u);
			jarr.add(jobj2);
		}
		jobj.put("user_array", jarr);
		try {
			this.writeToFile(jobj.toJSONString());
		} catch (IOException e) {
			System.out.println("IO exception whilw writing to file");
			e.printStackTrace();
		}
	}
	private void authenticateUser(int n) throws IOException, NoSuchAlgorithmException {
		System.out.print("Please enter your username: ");
		String inputUsername = this.getInput();
		System.out.print("Please enter your password: ");
		String hinputpw = this.hashPassword(this.getInput());
		for(User u: Uarr) {
			if(u.getUsername().equals("admininstrator"))
				continue;
			if (!u.isAccLocked()) {
				if (u.getUsername().equals(inputUsername)) {
					if (u.getPassword().equals(hinputpw)) {
						u.modifyDate();
						u.setFailLoginCount(0);
						System.out.println("Login success! Hello " + u.getUsername() + "!");
						if(n == 3) {
							u.setPassword(this.addNewPassword());
							System.out.print("Please enter your new full name: ");
							u.setFullName(this.getInput());
							System.out.print("Please enter your new email address: ");
							u.setEmailAdress(this.getInput());
							System.out.println("Record update successfully!");
						}
					}
					else {
						int lcount = u.getFailLoginCount();
						if (++lcount >= 3) {
							System.out.println("Login failed! Your account has been locked!");
							u.setAccLocked(true);
						} 
						else {
							u.setFailLoginCount(lcount);
							System.out.println("Login Failed!");
							this.authenticateUser(n);
						}
					} 
				} 
			}
			else {
				System.out.println("This account is locked! You cannot enter. Please ask the administrator to change the password");
			}
		}
	}
	private String readFile() 
			throws IOException {
		String line = null;
		BufferedReader	br = new BufferedReader(new FileReader(f));
		String         ls = System.getProperty("line.separator");
		StringBuilder  wholeString = new StringBuilder();
		while((line = br.readLine()) != null){
			wholeString.append(line);
			wholeString.append(ls);
		}
		br.close();
		return wholeString.toString();
		
	}
	private void writeToFile(String s) throws IOException {
		PrintStream ps = new PrintStream(f);
        /*try {
            fileToWrite.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileToWrite.flush();
        fileToWrite.close();*/
		ps.println(s);
		ps.close();
	}
	@SuppressWarnings("unchecked")
	private JSONObject createNewJSONObj(User u) {
		JSONObject obj = new JSONObject();
		obj.put("username", u.getUsername());
		obj.put("hash_password", u.getPassword());
		obj.put("Full Name", u.getFullName());
		obj.put("Email", u.getEmailAdress());
		obj.put("Phone number", u.getPhoneNo());
		obj.put("Fail count", u.getFailLoginCount());
		obj.put("Last Login Date", u.getLastLoginDate());
		obj.put("Account locked", u.isAccLocked());
		return obj;
	}
	private void resetPass() throws NoSuchAlgorithmException, IOException {
		boolean exists = false;
		String adminpw = null;
		for(User u:Uarr) {
			if(u.getUsername().equals("administrator"))
				adminpw = u.getPassword();
				exists = true;
		}
		if(exists == false) {
			System.out.println("Administrator account not exist, please create the administrator account by setting up a password for it.");
			User u = new User();
			u.setUsername("administrator");
			u.setPassword(this.addPassword());
			u.setFullName("Administrator");
			u.setEmailAdress("admin@cs.hku.hk");
			u.setPhoneNo("12345678");
			u.setFailLoginCount(0);
			u.modifyDate();
			u.setAccLocked(false);
			Uarr.add(u);
			System.out.println("Administrator account created successfully!");
			return;
		}
		System.out.print("Please enter the password of administrator: ");
		String inadminpw = this.getInput();
		if (this.hashPassword(inadminpw).equals(adminpw)) {
			System.out.print("Please enter the user account need to reset:");
			String user = this.getInput();
			for (User u : Uarr) {
				if (u.getUsername().equals("administrator")) {
					u.modifyDate();
				}
				if (u.getUsername().equals(user)) {
					u.setPassword(this.addNewPassword());
					u.setFailLoginCount(0);
					u.setAccLocked(false);
					System.out.println("Password update successfully!");
				}
			} 
		}
		else {
			System.out.println("Please input the right admin password");
		}
	}
}
	


