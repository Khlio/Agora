package fr.epsi.agora.web.ressource.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import org.restlet.data.CookieSetting;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.Constante;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;

public class ConnexionUtilisateurRessource extends ServerResource {

	@Inject
	public ConnexionUtilisateurRessource(RechercheUtilisateurs recherche) {
		this.recherche = recherche;
	}
	
	@Post
	public void connecte(Form formulaire) {
		DetailsUtilisateur details = recherche.detailsDe(formulaire.getFirstValue("email"), formulaire.getFirstValue("motDePasse"));
		try {
			checkNotNull(details);
			
			Session.ajoute(details.getId());
			CookieSetting cookie = new CookieSetting(1, Constante.SESSION_COOKIE, details.getId());
			getCookieSettings().add(cookie);
			setStatus(Status.SUCCESS_ACCEPTED);
		} catch (NullPointerException npe) {
			setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
	}
	
	private RechercheUtilisateurs recherche;
	
}
