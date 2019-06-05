package Authentication_Module;
import java.util.Date;
import java.text.SimpleDateFormat;
public class User {
	private String username;
	private String password;
	private String fullName;
	private String emailAdress;
	private String phoneNo;
	private int failLoginCount ;
	private boolean accLocked;
	private String lastLoginDate;
	/**
	 * Getter for Username
	 * @return Username 
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Setter For Username
	 * @param username 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Getter for Password
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Setter For Password
	 * @param Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Getter for Full Name
	 * @return name
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * Setter For Full Name
	 * @param Full Name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * Getter for Email Address
	 * @return Email Address
	 */
	public String getEmailAdress() {
		return emailAdress;
	}
	/**
	 * Setter For Email Address
	 * @param Address 
	 */
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	/**
	 * Getter for Phone number
	 * @return number
	 */
	public String getPhoneNo() {
		return phoneNo;
	}/**
	 * Setter For Phone Number
	 * @param number 
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * Getter for Fail Login Count
	 * @return count
	 */
	public int getFailLoginCount() {
		return failLoginCount;
	}
	/**
	 * Setter For Fail Login Count
	 * @param count
	 */
	public void setFailLoginCount(int failLoginCount) {
		this.failLoginCount = failLoginCount;
	}
	/**
	 * Getter for Locked Account
	 * @return locked account
	 */
	public boolean isAccLocked() {
		return accLocked;
	}
	/**
	 * Setter For Locked Account
	 * @param locked Account value
	 */
	public void setAccLocked(boolean accLocked) {
		this.accLocked = accLocked;
	}
	/**
	 * Setter For Last Login Date
	 * @param login date
	 */
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	/**
	 * Getter for Last Login Date
	 * @return date
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	/**
	 * Get a new date value for user
	 */
	public void modifyDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.setLastLoginDate(sdf.format(d).toString());
		
	}
	
}
