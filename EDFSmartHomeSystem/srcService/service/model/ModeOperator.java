package service.model;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import Context.Model.ConnectedObject;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="mode", propOrder={
		"id",
		"label",
		"rules"
}
		)
public class ModeOperator {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "label")
	private String label;
	@XmlElement(name = "rules")
	private List<Rule> rules = null;
	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public ModeOperator() {}
	/**
	 * this is another constructor
	 * @param label	
	 * 			the mode is name
	 * @param rule the mode is rule list
	 * @throws UnsupportedEncodingException
	 */
	public ModeOperator(final String label, final List<Rule> rules) throws UnsupportedEncodingException {
		this.label = label;
		this.rules= rules;
		this.id = createModeOperatorId(label);
	}
	
	public String getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public List<Rule> getRules() {
		return rules;
		
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	public static String createModeOperatorId(final String label) {
		String str = "";
		try {
			str = URLEncoder.encode(label, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
