package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;

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
		return new JacksonRepresentation<>(recherche.tousDuneSociete(societe));
	}
	
	private RechercheUtilisateurs recherche;
	private RechercheSocietes rechercheSocietes;
	private DetailsSociete societe;
	
}
