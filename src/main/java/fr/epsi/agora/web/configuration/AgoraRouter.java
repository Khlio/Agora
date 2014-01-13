package fr.epsi.agora.web.configuration;

import org.restlet.Context;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

import com.google.inject.Injector;

import fr.epsi.agora.web.ressource.constat.ConstatRessource;
import fr.epsi.agora.web.ressource.constat.ConstatsClientRessource;
import fr.epsi.agora.web.ressource.constat.ConstatsRessource;
import fr.epsi.agora.web.ressource.societe.ClientRessource;
import fr.epsi.agora.web.ressource.societe.ClientsRessource;
import fr.epsi.agora.web.ressource.societe.ConnexionUtilisateurRessource;
import fr.epsi.agora.web.ressource.societe.DeconnexionUtilisateurRessource;
import fr.epsi.agora.web.ressource.societe.SocieteRessource;
import fr.epsi.agora.web.ressource.societe.SocietesRessource;
import fr.epsi.agora.web.ressource.societe.UtilisateurRessource;
import fr.epsi.agora.web.ressource.societe.UtilisateursRessource;
import fr.epsi.agora.web.restlet.GuiceFinder;

public class AgoraRouter extends Router {

	public AgoraRouter(Context context, Injector injector) {
		super(context);
		this.injector = injector;
		attacheRoutes();
	}
	
	private void attacheRoutes() {
		attach("/societes", SocietesRessource.class);
		attach("/societes/{id}", SocieteRessource.class);
		attach("/societes/{id}/utilisateurs", UtilisateursRessource.class);
		
		attach("/utilisateurs/{idUtilisateur}", UtilisateurRessource.class);
		attach("/utilisateurs/{idUtilisateur}/clients", ClientsRessource.class);
		attach("/utilisateurs/{idUtilisateur}/clients/{idClient}", ClientRessource.class);
		attach("/utilisateurs/{idUtilisateur}/constats", ConstatsRessource.class);
		attach("/utilisateurs/{idUtilisateur}/constats/{idConstat}", ConstatRessource.class);
		attach("/utilisateurs/{idUtilisateur}/constats/clients/{idClient}", ConstatsClientRessource.class);
		
		attach("/connexion", ConnexionUtilisateurRessource.class);
		attach("/deconnexion/{id}", DeconnexionUtilisateurRessource.class);
	}
	
	@Override
	public Finder createFinder(Class<? extends ServerResource> resourceClass) {
		return new GuiceFinder(getContext(), resourceClass, injector);
	}
	
	private Injector injector;
	
}
