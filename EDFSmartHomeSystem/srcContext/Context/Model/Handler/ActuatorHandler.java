package Context.Model.Handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Context.Model.Actuator;
import Context.Model.ConnectedObject;
import Context.server.Server;
import Context.server.Writer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "actuators")
public class ActuatorHandler {
	@XmlElement(name = "actuator", required=true)
	private static List<Actuator> actuators = new ArrayList<Actuator>();
	/**
	 * Constructeur vide pour JAXB
	 */
	public ActuatorHandler() {	}
	
	public void createActuator(final boolean state, final String type,final String etat,final String emplacement){
		String actuatorid = ConnectedObject.createConnectedObjectIdFormemplacement(emplacement,type);
		Server.connected.createConnectedObject(type, etat, emplacement);
		if(getActuatorFromId(actuatorid)==null) {
			actuators.add(new Actuator(state,type,etat,emplacement));
			Writer.serializeActuator();
			Writer.serializeConnectedObject();
		}
	}
	public List<Actuator> getActuator() {
		return actuators;
	}
	public Actuator getActuatorFromId(final String actid) {
		for(Actuator cap : actuators){
			if(cap.getId().equals(actid)) return cap;
		}
		return null;
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */
	public void print(){
		System.out.print("=> "+actuators.size()+" Elements chargés \n");
	}
}