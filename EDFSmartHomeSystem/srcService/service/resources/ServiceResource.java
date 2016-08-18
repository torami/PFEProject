package service.resources;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import Context.Model.ConnectedObject;
import Context.server.Server;
import service.server.TemplateEngine1;
import service.model.Service;
import service.model.handlers.ServiceHandler;
import service.server.ServerS;


@Path("/service")
public class ServiceResource {
	@GET
	@Path("/id/{serviceid}")
	@Produces("text/xml")
	public Service getServiceFromId(@PathParam("serviceid") String serviceid) {
		Service u = ServerS.serh.getServiceFromId(serviceid);
		return u;
	}
	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllConnectedObjectHtml(@Context HttpServletRequest req) {
		TemplateEngine1.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Capteurs</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>Subject</td><td>ActivationState</td><td>Activity</td></tr>");
		List<Service> blist = ServerS.serh.getServices();
				for (Service b : blist) {
			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getSubject())
			.append("</td><td>")
			.append(b.isActivationState())
			.append("</td><td>")
			.append(b.getActivity().size())
			.append("</td></<tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine1.build(sb.toString());
	}

	
	@GET
	@Path("/all/")
	@Produces("text/xml")
	public ServiceHandler getAllServices() {
		return ServerS.serh;
	}

}
