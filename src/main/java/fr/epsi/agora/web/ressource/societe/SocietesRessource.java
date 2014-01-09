package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.CreationSocieteMessage;
import fr.epsi.agora.requete.societe.RechercheSocietes;

public class SocietesRessource extends ServerResource {

	@Inject
	public SocietesRessource(BusCommande busCommande, RechercheSocietes recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(recherche.toutes());
	}
	
	@Post
	public void cree(Form formulaire) {
		CreationSocieteMessage commande = new CreationSocieteMessage(formulaire.getFirstValue("siret"), formulaire.getFirstValue("nom"));
		ListenableFuture<UUID> idSociete = busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
		//TODO redirection ?
	}
	
	private BusCommande busCommande;
	private RechercheSocietes recherche;
	
}
