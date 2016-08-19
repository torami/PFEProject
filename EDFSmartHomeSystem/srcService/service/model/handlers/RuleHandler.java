package service.model.handlers;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Context.Model.Space;
import Context.Model.User;
import service.model.Rule;
import service.server.Writer;



/**
 * Gestion des instances de {@link Rule}.
 * {@link RuleHandler} implemente les methodes CRUD sur les {@link Rule}.
 * @author TORKHANI Rami
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rules")
public class RuleHandler {
	@XmlElement(name = "rule", required=true)
	private static List<Rule> rules = new ArrayList<Rule>();
	public RuleHandler() {	}
	/**
	 * création d'un nouveau rule .
	 * @param label the name of the mode
	 * @param <list> rule  list of rule composing the mode
	 * @throws UnsupportedEncodingException
	 */
	public void createRule(final User user,String action, final Space space) {
		try {
			rules.add(new Rule(user,action,space));
			Writer.serializeRules();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Permet de récupérer l'instance d'un rule à partir de son ID
	 * @param rule ID du role &agrave; r&eacute;cup&eacute;rer 
	 * @return rule correspondant
	 */
	public Rule getRuleFromId(final String ruleid) {
		for(Rule u : rules){
			if(u.getId().equals(ruleid)) return u;
		}
		return null;
	}

	/**
	 * Renvoie la liste de tous les rules
	 * @return liste
	 */
	public List<Rule> getRules() {
		return rules;
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */	
	public void print(){
		System.out.print("=> "+rules.size()+" element chargés \n");
	}
}
