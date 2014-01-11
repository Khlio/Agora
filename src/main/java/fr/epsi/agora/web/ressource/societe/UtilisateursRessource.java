package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheSocietes;

public class UtilisateursRessource extends ServerResource {

	@Inject
	public UtilisateursRessource(RechercheSocietes recherche) {
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		societe = recherche.detailsDe(id);
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(societe.getUtilisateurs());
	}
	
	private RechercheSocietes recherche;
	private DetailsSociete societe;
	
}
