package Context.resources;

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

import Context.Model.Actuator;
import Context.Model.Handler.ActuatorHandler;
import Context.server.Server;
import Context.server.TemplateEngine;

	@Path("/actuator")
	public class ActuatorResource {
		@GET
		@Path("/id/{actuatorid}")
		@Produces("text/xml")
		public Actuator getactuaotrFromId(@PathParam("actuatorid") String actuatorid) {
			Actuator u = Server.actuator.getActuatorFromId(actuatorid);
			return u;
		}

		@POST
		@Path("/create")
		@Produces("text/html")
		@Consumes("application/x-www-form-urlencoded")
		public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
			TemplateEngine.setSession(req.getSession());
			String type = formParams.getFirst("type");
			String etat = formParams.getFirst("etat");
			String emplacement = formParams.getFirst("emplacement");
			String locked = formParams.getFirst("locked");
			boolean verrous = false;
			if (locked.equals(1))
			{Server.actuator.createActuator(true, type, etat, emplacement);}
			else {Server.actuator.createActuator(verrous, type, etat, emplacement);}
			return TemplateEngine.goHome();
		}	
		

		
		@GET
		@Path("/all/")
		@Produces("text/xml")
		public ActuatorHandler getAllActuators() {
			return Server.actuator;
		}
		@GET
		@Path("/all/html")
		@Produces("text/html")
		public String getAllActuatorHtml(@Context HttpServletRequest req) {
			TemplateEngine.setSession(req.getSession());
			final StringBuilder sb = new StringBuilder();
			sb.append("<h1>User</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
			sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>Emplacement</td><td>Type</td><td>Etat</td><td>State</td></tr>");
			List<Actuator> blist = Server.actuator.getActuator();
					for (Actuator b : blist) {
				sb.append("\n<tr><td>")
				.append(b.getId())
				.append("</td><td>")
				.append(b.getEmplacement())
				.append("</td><td>")
				.append(b.getType())
				.append("</td><td>")
				.append(b.getEtat())
				.append("</td><td>")
				.append(b.isLocked())
				.append("</td></<tr>");
			}
			sb.append("\n</table>");
			return TemplateEngine.build(sb.toString());
		}
}
