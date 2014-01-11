package fr.epsi.agora.web.ressource;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.resource.ServerResource;

public final class RessourceHelper {

	private RessourceHelper(ServerResource ressource) {
		this.ressource = ressource;
	}
	
	public static RessourceHelper initialise(ServerResource ressource) {
		Request request = new Request();
		request.setResourceRef("http://localhost");
		ressource.setRequest(request);
		ressource.setResponse(new Response(request));
		return new RessourceHelper(ressource);
	}
	
	public RessourceHelper avec(String id, Object valeur) {
		return avec(id, valeur, true);
	}
	
	public RessourceHelper avec(String id, Object valeur, boolean init) {
		ressource.getRequestAttributes().put(id, valeur.toString());
		if (init) {
			ressource.init(ressource.getContext(), ressource.getRequest(), ressource.getResponse());
		}
		return this;
	}
	
	private ServerResource ressource;
	
}
