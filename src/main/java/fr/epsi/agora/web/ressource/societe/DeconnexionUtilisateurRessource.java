package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

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
	public Representation deconnecte() {
		try {
			Session.supprime(utilisateur.getId());
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private RechercheUtilisateurs recherche;
	private DetailsUtilisateur utilisateur;
	
}
