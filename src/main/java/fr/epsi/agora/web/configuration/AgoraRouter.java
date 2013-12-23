package fr.epsi.agora.web.configuration;

import org.restlet.Context;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

import com.google.inject.Injector;

import fr.epsi.agora.web.ressource.utilisateur.UtilisateurRessource;
import fr.epsi.agora.web.ressource.utilisateur.UtilisateursRessource;
import fr.epsi.agora.web.restlet.GuiceFinder;

public class AgoraRouter extends Router {

	public AgoraRouter(Context context, Injector injector) {
		super(context);
		this.injector = injector;
		attacheRoutes();
	}
	
	private void attacheRoutes() {
		attach("/utilisateurs", UtilisateursRessource.class);
		attach("/utilisateurs/{id}", UtilisateurRessource.class);
	}
	
	@Override
	public Finder createFinder(Class<? extends ServerResource> resourceClass) {
		return new GuiceFinder(getContext(), resourceClass, injector);
	}
	
	private Injector injector;
	
}
