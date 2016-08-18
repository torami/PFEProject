package service.model.handlers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import service.model.Activity;
import service.model.ModeOperator;
import service.model.Rule;
import service.model.Service;
import service.server.Writer;



/**
 * Gestion des instances de {@link Service}.
 * {@link ServiceHandler} implemente les methodes CRUD sur les {@link Service}.
 * @author TORKHANI Rami
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "modes")
public class ModeOperatorHandler {
	@XmlElement(name = "mode", required=true)
	private static List<ModeOperator> modes = new ArrayList<ModeOperator>();
	public ModeOperatorHandler() {	}
	/**
	 * création d'un nouveau modes .
	 * @param label the name of the mode
	 * @param <list> rule  list of rule composing the mode
	 * @throws UnsupportedEncodingException
	 */
	public void createMode(final String label,final boolean active, final List<Rule> rule) {
		try {
			modes.add(new ModeOperator(label,active,rule));
			Writer.serializeServices();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Renvoie la liste de tous les modes
	 * @return liste
	 */
	public List<ModeOperator> getModes() {
		return modes;
	}
	/**
	 * Permet de récupérer l'instance d'un mode à partir de son ID
	 * @param modeid ID du mode &agrave; r&eacute;cup&eacute;rer 
	 * @return mode correspondant
	 */
	public ModeOperator getModeFromId(final String modeid) {
		for(ModeOperator u : modes){
			if(u.getId().equals(modeid)) return u;
		}
		return null;
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */	
	public void print(){
		System.out.print("=> "+modes.size()+" element chargés \n");
	}
}