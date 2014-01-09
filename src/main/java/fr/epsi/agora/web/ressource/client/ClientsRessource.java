package fr.epsi.agora.web.ressource.client;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.requete.client.RechercheClients;

public class ClientsRessource extends ServerResource {

	@Inject
	public ClientsRessource(BusCommande busCommande, RechercheClients recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(recherche.tous());
	}
	
	private BusCommande busCommande;
	private RechercheClients recherche;

}
