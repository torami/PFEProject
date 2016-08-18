package service.model;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import Context.Model.ConnectedObject;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="activity", propOrder={
		"id",
		"label",
		"resources"
}
		)
@XmlRootElement(name = "activity")
public class Activity {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "label")
	private String label;
	@XmlElement(name = "resources")
	private List<ConnectedObject> resources = null;

	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public Activity() {	}

	/**
	 * this is another constructor
	 * @param label	
	 * 			the service is subject
	 * @param resource
	 * 			the maintained object
	 * @throws UnsupportedEncodingException
	 */
	public Activity(final String label, final List<ConnectedObject> resources) throws UnsupportedEncodingException {
		this.label = label;
		this.resources= resources;
		this.id = createActivityId(label);
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setResources(List<ConnectedObject> resources) {
		this.resources = resources;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public List<ConnectedObject> getResources() {
		return resources;
	}

	public static String createActivityId(final String label) {
		String str = "";
		try {
			str = URLEncoder.encode(label, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
