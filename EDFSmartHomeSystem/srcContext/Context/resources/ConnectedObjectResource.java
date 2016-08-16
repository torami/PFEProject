package Context.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import Context.server.TemplateEngine;
import Context.server.Server;
import Context.Model.ConnectedObject;
import Context.Model.Handler.ConnectedObjectHandler;


@Path("/connectedobject")
public class ConnectedObjectResource {
	@GET
	@Path("/id/{connectedid}")
	@Produces("text/xml")
	public ConnectedObject getConnectedObjectFromId(@PathParam("connectedid") String cpid) {
		ConnectedObject t = Server.connected.getConnectedObjectFromId(cpid);
		return t;
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
		Server.connected.createConnectedObject(type, etat, emplacement);
		return TemplateEngine.goHome();
	}
	@GET
	@Path("/all")
	@Produces("text/xml")	
	public ConnectedObjectHandler getAllCapteurs() {
		return Server.connected;
	}
	
	@GET
	@Path("/id/{connectedid}/html")
	@Produces("text/html")
	public String getConnectedObjectFromIdHtml(@Context HttpServletRequest req, @PathParam("connectedid") String cpid) {
		TemplateEngine.setSession(req.getSession());
		ConnectedObject t = Server.connected.getConnectedObjectFromId(cpid);
		final StringBuilder sb = new StringBuilder();
		sb.append("\n<h1>Sensor Details</h1>\n<h2>")
		.append(t.getEmplacement()).append("</h2><ul><h2>")
		.append(t.getEtat()).append("</h2><ul><h2>")
		.append(t.getType()).append("</h2><ul><h2>")
		.append("</ul>");
		return TemplateEngine.build(sb.toString());
	}
	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllConnectedObjectHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Capteurs</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>Emplacement</td><td>Type</td><td>Etat</td></tr>");
		List<ConnectedObject> blist = Server.connected.getConnectedObject();
				for (ConnectedObject b : blist) {
			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getEmplacement())
			.append("</td><td>")
			.append(b.getType())
			.append("</td><td>")
			.append(b.getEtat())
			.append("</td></<tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}
}
