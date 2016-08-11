package Context.Model.Handler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Context.server.Writer;
import Context.Model.User;

/**
 * Gestion des instances de {@link User}.
 * {@link UserHandler} implemente les methodes CRUD sur les {@link User Users}.
 * @author TORKHANI Rami
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UserHandler {
	@XmlElement(name = "user", required=true)
	private static List<User> users = new ArrayList<User>();
	public UserHandler() {
	}
	/**
	 * création d'un nouvel utilisateur.
	 * @param login login de l'utilisateur, celui ci doit &ecirc;tre unique.
	 * @param lastname nom de famille de l'utilisateur
	 * @param firstname pr&eacute;nom de l'utilisateur
	 * @throws UnsupportedEncodingException
	 */
	public void createUser(final String username,final String password) {
		try {
			users.add(new User(username,password));
			Writer.serializeUsers();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Renvoie la liste de tous les utilisateurs
	 * @return liste
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Permet de récupérer l'instance d'un utilisateur à partir de son ID
	 * @param userid ID de l'utilisateur &agrave; r&eacute;cup&eacute;rer 
	 * @return l'utilisateur correspondant
	 */
	public User getUserFromId(final String userid) {
		for(User u : users){
			if(u.getId().equals(userid)) return u;
		}
		return null;
	}
	
	/**
	 * Permet de récupérer l'instance d'un utilisateur à partir de son login
	 * @param login le login de l'utilisateur &agrave; r&eacute;cup&eacute;rer 
	 * @return l'utilisateur correspondant
	 */
	public User getUserFromLogin(final String login) {
		return getUserFromId(User.createUserId(login));
	}
	/**
	 * Mise à jour  d'un utilisateur existant.
	 * @param userid ID de l'utilisateur &agrave; mettre &agrave; jour
	 * @param lastname nouveau nom de famille
	 * @param firstname nouveau pr&eacute;nom
	 */
	public void updateUser(final String userid,final String username, final String password){
		User u = getUserFromId(userid);
		u.setUsername(username);
		u.setPassword(password);
		Writer.serializeUsers();
	}

	/**
	 * Permet de supprimer un utilisateur existant &agrave; partir de son ID
	 * @param userid ID de l'utilisateur &agrave; supprimer
	 * @return <code>true</code> si l'utilisateur a &eacute;t&eacute; trouv&eacute; et <code>false</code> sinon
	 */
	public boolean removeUserFromId(final String userid){
		boolean result = users.remove(getUserFromId(userid));
		Writer.serializeUsers();
		return result;
	}
	/**
	 * Permet de supprimer un utilisateur existant &agrave; partir de son login
	 * @param login login de l'utilisateur &agrave; supprimer
	 * @return <code>true</code> si l'utilisateur a &eacute;t&eacute; trouv&eacute; et <code>false</code> sinon
	 */
	public boolean removeUserFromLogin(final String login){
		return removeUserFromId(User.createUserId(login));
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */	
	public void print(){
		System.out.print("=> "+users.size()+" element chargés \n");
	}

}
