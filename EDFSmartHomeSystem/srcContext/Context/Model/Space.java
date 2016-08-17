package Context.Model;

import java.util.List;

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
@XmlType(name = "space", propOrder = { "id", "name","opening","locked"})
@XmlRootElement(name = "space")
public class Space {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "locked")
	private boolean locked = false;
	@XmlElement(name = "opening")
	private List<Opening> opening = null;
	
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
	public Space(final String name, final boolean lock) {
		this.id = createSpaceFormName(name);
		this.name = name;
		this.locked=lock;
	}
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */
	
	public String getId() {
		return id;
	}
	public List<Opening> getOpening() {
		return opening;
	}
	public void setOpening(List<Opening> opening) {
		this.opening = opening;
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
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public static String createSpaceFormName(final String name) {
		String str = "";
		str = name + "@" + name;
		return str;
	}

}
