package service.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import service.server.TemplateEngine;

@Path("/")
public class NavResource {
	@GET
	@Produces("text/html")
	public String getHome(@Context HttpServletRequest req) {
		HttpSession session = req.getSession();
		TemplateEngine.setSession(req.getSession());
		
		if(session.getAttribute("serviceid")!=null){
			String serviceid = (String) session.getAttribute("serviceid");
			System.out.println("Vous etes identifiee en tant que :"+serviceid);
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
	@Path("/form/login")
	public String getLogin(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("login.html");
	}
	



	
}
