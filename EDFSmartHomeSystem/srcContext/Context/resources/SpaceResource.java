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
import Context.Model.Space;
import Context.Model.Handler.SpaceHandler;



@Path("/space")
public class SpaceResource {
	@GET
	@Path("/id/{spaceid}")
	@Produces("text/xml")
	public Space getSpaceFromId(@PathParam("spaceid") String znid) {
		Space zn = Server.space.getSpaceFromId(znid);
		return zn;
	}


	@GET
	@Path("/all")
	@Produces("text/xml")
	public SpaceHandler getAllSpace() {
		return Server.space;
	}
	@GET
	@Path("/id/{spaceid}/html")
	@Produces("text/html")
	public String getSpaceFromIdHtml(@Context HttpServletRequest req, @PathParam("spaceid") String znid) {
		TemplateEngine.setSession(req.getSession());
		Space zn = Server.space.getSpaceFromId(znid);
		final StringBuilder sb = new StringBuilder();
		sb.append("\n<h1>Informations Sur l'espace </h1>\n<h2>");
		sb.append(zn.getName())
		.append("</a></p>");
		return TemplateEngine.build(sb.toString());
	}	
	
	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String Nom = formParams.getFirst("nom");
		Server.space.createSpace(Nom);
		return TemplateEngine.goHome();
	}

	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllSpaceHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Space</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>Name</td></tr>");
		List<Space> blist = Server.space.getSpace();
		
		for (Space b : blist) {
			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getName())
			.append("</td></tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}		
}
