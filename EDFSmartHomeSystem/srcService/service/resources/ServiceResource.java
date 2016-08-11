package service.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import service.model.Service;
import service.server.Server;


@Path("/service")
public class ServiceResource {
	@GET
	@Path("/id/{serviceid}")
	@Produces("text/xml")
	public Service getUserFromId(@PathParam("serviceid") String serviceid) {
		Service u = Server.serh.getServiceFromId(serviceid);
		return u;
	}

	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req){
		TemplateEngine.setSession(req.getSession());
		String login = formParams.getFirst("login");
		String password = formParams.getFirst("password");
		Server.uh.createUser(login, password);
		return TemplateEngine.goHome();
	}	
	
	@POST
	@Path("/login")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String login(@Context HttpServletRequest req, @FormParam("login") String login, MultivaluedMap<String, String> formParams) {
		TemplateEngine.setSession(req.getSession());
		String password = formParams.getFirst("password");
		if(Server.uh.VerifyloginAndPassword(login, password)==true){
			HttpSession session = req.getSession();
			session.setAttribute("userid", User.createUserId(login));
			System.out.println("USER LOGIN : "+login);
		}
		return TemplateEngine.goHome();
	}	
	
	@GET
	@Path("/logout")
	@Produces("text/html")
	public String logout(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		HttpSession session = req.get	 Session();
		String userid = (String) session.getAttribute("userid");
		System.out.println("USER LOGOUT : "+userid);
		session.removeAttribute("userid");
		return TemplateEngine.redirect("/", 2, "you will be disconnected in 2 seconds.");
	}	
	
	@GET
	@Path("/all/")
	@Produces("text/xml")
	public UserHandler getAllUsers() {
		return Server.uh;
	}

	

}
