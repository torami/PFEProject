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


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "openings")
public class OpeningHandler {
	@XmlElement(name = "opening", required=true)
	private static List<Opening> openings = new ArrayList<Opening>();
	public OpeningHandler(){	}
	
	public void createOpening(final String type,final String etat,final String emplacement) {
		if(getOpeningFromId(Opening.createIssueId(type,emplacement)) == null) {
			openings.add(new Opening(type,etat,emplacement));
			Writer.serializeIssues();
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
	public Issue getIssueFromNom(String issuenom) {
		for(Issue t : issues){
			if(t.getEmplacement().equals(issuenom)) return t;
		}
		return null;
	}
	public void updateIssueFromId(final String oldissueid, final String newissuefinal, String type,final String etat,final String emplacement){
		Issue t = getIssueFromId(oldissueid);
		t.setEmplacement(emplacement);
		t.setEtat(etat);
		t.setType(type);
		Server.tgh.updateTaggingsWithIssueId(oldissueid, t.getId());
		Writer.serializeIssues();
	}
	
	public void removeIssue(String issueid){
		Server.tgh.removeTaggingsZoneWithIssueId(issueid);
		issues.remove(getIssueFromId(issueid));
		Writer.serializeIssues();
	}
	public void print(){
		System.out.print("=> "+issues.size()+" elements chargés \n");
	}

}