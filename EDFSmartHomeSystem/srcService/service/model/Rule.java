package service.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import Context.Model.Space;
import Context.Model.User;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="rule", propOrder={
		"id",
		"user",
		"action",
		"space"
}
		)
public class Rule {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "user")
	private User user;
	@XmlElement(name = "action")
	private String action;
	@XmlElement(name = "space")
	private Space space ;

	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public Rule() {}
	/**
	 * this is another constructor
	 * @param label	
	 * 			the mode is name
	 * @param rule the mode is rule list
	 * @throws UnsupportedEncodingException
	 */
	public Rule(final User user,String action, final Space space) throws UnsupportedEncodingException {
		this.user=user;
		this.action=action;
		this.space=space;
		this.id = createRuleId(user.getUsername(),action);
	}
	
	public String getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public String getAction() {
		return action;
	}
	public Space getSpace() {
		return space;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setSpace(Space space) {
		this.space = space;
	}
	public static String createRuleId(final String label,final String action) {
		String str = "";
		try {
			str = URLEncoder.encode(label, "UTF-8")+"@"+URLEncoder.encode(label, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

}
