package service.resources;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import service.server.TemplateEngine1;

@Path("/")
public class NavResource {
	@GET
	@Produces("text/html")
	public String getHome(@Context HttpServletRequest req) {
		HttpSession session = req.getSession();
		TemplateEngine1.setSession(req.getSession());
			return TemplateEngine1.buildFromFile("home.html");
		}

	@GET
	@Produces("text/html")
	@Path("/form/mode/add")
	public String getModeForm(@Context HttpServletRequest req) {
		TemplateEngine1.setSession(req.getSession());
		return TemplateEngine1.buildFromFile("addmode.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/mode/delete")
	public String getModeDeleteForm(@Context HttpServletRequest req) {
		TemplateEngine1.setSession(req.getSession());
		return TemplateEngine1.buildFromFile("deletemode.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/rule/add")
	public String getRuleForm(@Context HttpServletRequest req) {
		TemplateEngine1.setSession(req.getSession());
		return TemplateEngine1.buildFromFile("addrule.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/mode/change")
	public String getModeChangeForm(@Context HttpServletRequest req) {
		TemplateEngine1.setSession(req.getSession());
		return TemplateEngine1.buildFromFile("changemode.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/mode/addrule")
	public String getModeaddRuleForm(@Context HttpServletRequest req) {
		TemplateEngine1.setSession(req.getSession());
		return TemplateEngine1.buildFromFile("addruletomode.html");
	}
	@GET
	@Path("/{filename}")
	public String getFile(@PathParam("filename") String filename) {
		return TemplateEngine1.getFile(filename);
	}
	
	@GET
	@Produces("text/html")
	@Path("/form/login")
	public String getLogin(@Context HttpServletRequest req) {
		TemplateEngine1.setSession(req.getSession());
		return TemplateEngine1.buildFromFile("login.html");
	}
	

}



