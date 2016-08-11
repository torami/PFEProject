package service.model;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean JAXB this beans implement the model of user
 * @author J60277
 */
@XmlRootElement(name = "services")
@XmlAccessorType (XmlAccessType.FIELD)
public class Service {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "subject")
	private String subject;
	@XmlElement(name = "activationState")
	private boolean activationState ;
    @XmlElement(name = "activity")
	private List<Activity> activity = null;
	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public Service() {	}
	/**
	 * this is another constructor
	 * @param username	
	 * 			the user is username
	 * @param password
	 * 			the user is password
	 * @throws UnsupportedEncodingException
	 */
	public Service (final String subject,final boolean state,final List<Activity> activity) throws UnsupportedEncodingException {
		this.subject = subject;
		this.activationState = state;
		this.activity  = activity;
		this.id = createServiceId(subject);
	}
	
	public String getId() {
		return id;
	}
	public String getSubject() {
		return subject;
	}
	public boolean isActivationState() {
		return activationState;
	}
	public List<Activity> getActivity() {
		return activity;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setActivationState(boolean activationState) {
		this.activationState = activationState;
	}
	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}
	public static String createServiceId(final String subject) {
		String str = "";
		try {
			str = URLEncoder.encode(subject, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}