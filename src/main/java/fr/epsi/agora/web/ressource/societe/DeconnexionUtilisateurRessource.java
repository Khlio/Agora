package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.Constante;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;

public class DeconnexionUtilisateurRessource extends ServerResource {

	@Inject
	public DeconnexionUtilisateurRessource(RechercheUtilisateurs recherche) {
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		utilisateur = recherche.detailsDe(id);
	}
	
	@Post
	public void deconnecte() {
		getCookieSettings().getFirst(Constante.SESSION_COOKIE).setMaxAge(0);
		Session.supprime(utilisateur.getId());
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private RechercheUtilisateurs recherche;
	private DetailsUtilisateur utilisateur;
	
}
