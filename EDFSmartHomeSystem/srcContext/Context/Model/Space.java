package Context.Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




/**
 * Bean JAXB this beans implement the model of zone
 * @author J60277
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "space", propOrder = { "id", "name"})
@XmlRootElement(name = "space")
public class Space {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "name")
	private String name;
	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public Space() {	}
	/**
	 * this is another constructor
	 * @param nom
	 * @author J60277
	 */
	public Space(final String name) {
		this.id = createSpaceFormName(name);
		this.name = name;
	}
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setId(final String id) {
		this.id = id;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public static String createSpaceFormName(final String name) {
		String str = "";
		str = name + "@" + name;
		return str;
	}

}
