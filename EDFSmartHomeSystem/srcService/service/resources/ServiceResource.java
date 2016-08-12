package service.resources;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import service.model.Service;
import service.model.handlers.ServiceHandler;
import service.server.Server;
import service.server.TemplateEngine;


@Path("/service")
public class ServiceResource {
	@GET
	@Path("/id/{serviceid}")
	@Produces("text/xml")
	public Service getUserFromId(@PathParam("serviceid") String serviceid) {
		Service u = Server.serh.getServiceFromId(serviceid);
		return u;
	}



	
	@GET
	@Path("/logout")
	@Produces("text/html")
	public String logout(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		HttpSession session = req.getSession();
		String userid = (String) session.getAttribute("userid");
		System.out.println("USER LOGOUT : "+userid);
		session.removeAttribute("userid");
		return TemplateEngine.redirect("/", 2, "you will be disconnected in 2 seconds.");
	}	
	
	@GET
	@Path("/all/")
	@Produces("text/xml")
	public ServiceHandler getAllServices() {
		return Server.serh;
	}

	

}
