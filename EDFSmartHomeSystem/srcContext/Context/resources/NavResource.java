package Context.resources;

import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import service.server.TemplateEngine1;
import Context.server.TemplateEngine;

@Path("/")
public class NavResource {
	@GET
	@Produces("text/html")
	public String getHome(@Context HttpServletRequest req) {
		HttpSession session = req.getSession();
		TemplateEngine.setSession(req.getSession());
		
		if(session.getAttribute("userid")!=null){
			String userid = (String) session.getAttribute("userid");
			System.out.println("Vous etes identifiee en tant que :"+userid);
			return TemplateEngine.buildFromFile("home.html");
		}else{
			return TemplateEngine.buildFromFile("login.html");
		}
	}
	
	@GET
	@Path("/{filename}")
	public String getFile(@PathParam("filename") String filename) {
		return TemplateEngine.getFile(filename);
	}
	@GET
	@Produces("text/html")
	@Path("/form/connectedobject/add")
	public String getConnectedObjectForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addconnectedobjct.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/actuator/add")
	public String getActuatorForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("actuators.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/space/add")
	public String getSpaceForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addspace.html");
	}

	@GET
	@Produces("text/html")
	@Path("/form/openings/add")
	public String getOpeningsForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addopening.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/login")
	public String getLogin(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("login.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/user/add")
	public String getUserForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("adduser.html");
	}




	
}
