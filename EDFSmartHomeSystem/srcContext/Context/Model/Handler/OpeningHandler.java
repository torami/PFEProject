package Context.Model.Handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Context.server.Server;
import Context.server.Writer;
import Context.Model.Opening;
import Context.Model.Space;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "openings")
public class OpeningHandler {
	@XmlElement(name = "opening", required=true)
	private static List<Opening> openings = new ArrayList<Opening>();
	public OpeningHandler(){	}
	
	public void createOpening(final String type,final String etat,final String emplacement) {
		if(getOpeningFromId(Opening.createIssueId(type,emplacement)) == null) {
			openings.add(new Opening(type,etat,emplacement));
			Space s = Server.space.getSpaceFromNom(emplacement);
			List<Opening> a = s.getOpening();
			a.add(new Opening(type,etat,emplacement));
			s.setOpening(a);
			Writer.serializeOpenings();
			Writer.serializeSpaces();
		}
	}
	public List<Opening> getOpenings() {
		return openings;
	}
	public Opening getOpeningFromId(String issueid) {
		for(Opening t : openings){
			if(t.getId().equals(issueid)) return t;
		}
		return null;
	}
	public Opening getIssueFromNom(String issuenom) {
		for(Opening t : openings){
			if(t.getEmplacement().equals(issuenom)) return t;
		}
		return null;
	}

	public void print(){
		System.out.print("=> "+openings.size()+" elements chargés \n");
	}

}