package fr.epsi.agora.web.ressource.constat;

import java.util.UUID;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;

public class ConstatsClientRessource extends ServerResource {

	@Inject
	public ConstatsClientRessource(RechercheConstats recherche) {
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			idClient = UUID.fromString(getRequestAttributes().get("idClient").toString());
		} else {
			setStatus(Status.CLIENT_ERROR_FORBIDDEN);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		if (isCommitted()) {
			return new JacksonRepresentation<>(null);
		}
		return new JacksonRepresentation<>(recherche.tousDunClient(idClient));
	}
	
	private RechercheConstats recherche;
	private UUID idClient;
	
}
