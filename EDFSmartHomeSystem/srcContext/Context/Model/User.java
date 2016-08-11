package Context.Model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Bean JAXB this beans implement the model of user
 * @author J60277
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="user", propOrder={
		"id",
		"username",
		"password"
}
		)
@XmlRootElement(name = "user")
public class User {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "username")
	private String username;
	@XmlElement(name = "password")
	private String password;

	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public User() {	}
	/**
	 * this is another constructor
	 * @param username	
	 * 			the user is username
	 * @param password
	 * 			the user is password
	 * @throws UnsupportedEncodingException
	 */
	public User(final String login,final String password) throws UnsupportedEncodingException {
		this.username = login;
		this.password = password;
		this.id = createUserId(login);
	}
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */


	public static String createUserId(final String login) {
		String str = "";
		try {
			str = URLEncoder.encode(login, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
