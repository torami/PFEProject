package service.resources;


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

import Context.Model.User;
import service.model.Service;
import service.model.handlers.ServiceHandler;
import service.server.Server;
import service.server.TemplateEngine;


@Path("/service")
public class ServiceResource {
	@GET
	@Path("/id/{serviceid}")
	@Produces("text/xml")
	public Service getServiceFromId(@PathParam("serviceid") String serviceid) {
		Service u = Server.serh.getServiceFromId(serviceid);
		return u;
	}
	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String service = formParams.getFirst("service");
		Server.serh.createService(service,false,null);
		return TemplateEngine.goHome();
	}	
	
	@POST
	@Path("/login")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String login(@Context HttpServletRequest req, @FormParam("ser vice") String service) {
		TemplateEngine.setSession(req.getSession());
		if(Server.serh.getServiceFromId(service)!=null){
			HttpSession session = req.getSession();
			session.setAttribute("serviceid", Service.createServiceId(service));
			System.out.println("Service LOGIN : "+service);
		}
		return TemplateEngine.goHome();
	}	
	
	@GET
	@Path("/logout")
	@Produces("text/html")
	public String logout(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		HttpSession session = req.getSession();
		String serviceid = (String) session.getAttribute("serviceid");
		System.out.println("Service Repo LOGOUT : "+serviceid);
		session.removeAttribute("serviceid");
		return TemplateEngine.redirect("/", 2, "you will be disconnected in 2 seconds.");
	}	
	
	@GET
	@Path("/all/")
	@Produces("text/xml")
	public ServiceHandler getAllServices() {
		return Server.serh;
	}

	

}
