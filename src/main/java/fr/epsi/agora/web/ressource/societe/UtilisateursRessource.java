package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class UtilisateursRessource extends ServerResource {

	@Inject
	public UtilisateursRessource(RechercheUtilisateurs recherche, RechercheSocietes rechercheSocietes) {
		this.recherche = recherche;
		this.rechercheSocietes = rechercheSocietes;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		societe = rechercheSocietes.detailsDe(id);
	}
	
	@Get("json")
	public Representation represente() {
		try {
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.json(recherche.tousDuneSociete(societe));
		}  catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private RechercheUtilisateurs recherche;
	private RechercheSocietes rechercheSocietes;
	private DetailsSociete societe;
	
}
