package fr.epsi.agora.web.ressource.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import org.restlet.data.CookieSetting;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.Constante;
import fr.epsi.agora.domaine.validateur.EmailValidateur;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class ConnexionUtilisateurRessource extends ServerResource {

	@Inject
	public ConnexionUtilisateurRessource(RechercheUtilisateurs recherche) {
		this.recherche = recherche;
	}
	
	@Post
	public Representation connecte(Form formulaire) {
		Erreur erreurEmail = EmailValidateur.valide(formulaire.getFirstValue("email"));
		if (erreurEmail.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurEmail.premiereErreur());
		}
		DetailsUtilisateur details = recherche.detailsDe(formulaire.getFirstValue("email"), formulaire.getFirstValue("motDePasse"));
		try {
			checkNotNull(details);
			
			Session.ajoute(details.getId());
			CookieSetting cookie = new CookieSetting(1, Constante.SESSION_COOKIE, details.getId());
			cookie.setPath("/");
			cookie.setAccessRestricted(true);
			getCookieSettings().add(cookie);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (NullPointerException npe) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(Erreur.IDENTIFIANTS_INTROUVABLE);
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private RechercheUtilisateurs recherche;
	
}
