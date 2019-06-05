package Authentication_Module;
import java.security.NoSuchAlgorithmException;
/**
 * Hashing Interface
 * @author adwithyamagow
 *
 */
public interface Hash {
	public String hashPassword(String password) throws NoSuchAlgorithmException;
}
