package service.model.handlers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
	 * cr�ation d'un nouveau modes .
	 * @param label the name of the mode
	 * @param <list> rule  list of rule composing the mode
	 * @throws UnsupportedEncodingException
	 */
	public void createMode(final String label,final boolean active, final List<Rule> rule) {
		try {
			modes.add(new ModeOperator(label,active,rule));
			Writer.serializeModes();
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
	 * Permet de supprimer un mode existant &agrave; partir de son ID
	 * @param userid ID de l'utilisateur &agrave; supprimer
	 * @return <code>true</code> si l'utilisateur a &eacute;t&eacute; trouv&eacute; et <code>false</code> sinon
	 */
	public boolean removeModeFromId(final String modeid){
		boolean result = modes.remove(getModeFromId(modeid));
		Writer.serializeModes();
		return result;
	}
	/**
	 * Permet de r�cup�rer l'instance d'un mode � partir de son ID
	 * @param modeid ID du mode &agrave; r&eacute;cup&eacute;rer 
	 * @return mode correspondant
	 */
	public ModeOperator getModeFromId(final String modeid) {
		for(ModeOperator u : modes){
			if(u.getId().equals(modeid)) return u;
		}
		return null;
	}
	public void UpdateAllMode() {
		for(ModeOperator u : modes){
			if(u.isActivated() != false) {
				u.setActivated(false);
				Writer.serializeModes();

			}
		}
	
	}

	/**
	 * Mise � jour de mode par id.
	 * @param mode ID du mode &agrave; mettre &agrave; jour
	 * @param label nom du mode
	 * @param active etat du mode
	 * @param rule les r�gles qui forment le mode
	 */
	public void updateMode(final String modeid,final String modelabel,final boolean active,List<Rule> rule){
		ModeOperator op = getModeFromId(modeid);
		op.setId(modeid);
		op.setLabel(modelabel);
		op.setActivated(active);
		op.setRules(rule);
		Writer.serializeModes();
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */	
	public void print(){
		System.out.print("=> "+modes.size()+" element charg�s \n");
	}
}