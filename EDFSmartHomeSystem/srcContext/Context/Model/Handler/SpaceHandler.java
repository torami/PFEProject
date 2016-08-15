package Context.Model.Handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import Context.server.Server;
import Context.server.Writer;
import Context.Model.Space;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zones")
public class SpaceHandler {
	@XmlElement(name = "space", required=true)
	private static List<Space> spaces = new ArrayList<Space>();
	/**
	 * Constructeur vide pour JAXB
	 */
	public SpaceHandler() {	}
	public void createSpace(String nom){
		String spaceid = Space.createSpaceFormName(nom);
		if(getSpaceFromId(spaceid)==null) {
			spaces.add(new Space(nom));
			Writer.serializeSpaces();
		}
	}
	public List<Space> getSpace() {
		return spaces;
	}
	public Space getSpaceFromId(final String spaceid) {
		for(Space zn : spaces){
			if(zn.getId().equals(spaceid)) return zn;
		}
		return null;
	}
	public Space getSpaceFromNom(final String spacename) {
		for(Space zn : spaces){
			if(zn.getName().equals(spacename)) return zn;
		}
		return null;
	}

	public void print(){
		System.out.print("=> "+spaces.size()+" element chargés \n");
	}
}

