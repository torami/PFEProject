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
@XmlType(name = "actuator", propOrder = { "id","type","etat","emplacement","locked"})
@XmlRootElement(name = "actuator")
public class Actuator extends ConnectedObject {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "type")
	private String type;
	@XmlElement(name = "etat")
	private String etat;
	@XmlElement(name = "emplacement")
	private String emplacement;
	@XmlElement(name = "locked")
	private boolean locked = false;

	public Actuator (){  }
	public Actuator (final boolean statelocked,final String type,final String etat,final String emplacement){
		this.locked=statelocked;
		
		this.type=type;
		this.etat=etat;
		this.emplacement=emplacement;
		this.id= createConnectedObjectIdFormemplacement(emplacement, type);
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
