package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.requete.societe.DetailsClient;
import fr.epsi.agora.requete.societe.RechercheClients;

public class ClientRessource extends ServerResource {

	@Inject
	public ClientRessource(BusCommande busCommande, RechercheClients recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() throws ResourceException {
		UUID id = UUID.fromString(getRequestAttributes().get("idClient").toString());
		client = recherche.detailsDe(id);
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(client);
	}
	
	private BusCommande busCommande;
	private RechercheClients recherche;
	private DetailsClient client;
	
}
