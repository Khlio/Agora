package fr.epsi.agora.web.ressource.constat;

import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.constat.CreationConstatMessage;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;

public class ConstatsRessource extends ServerResource {

	@Inject
	public ConstatsRessource(BusCommande busCommande, RechercheConstats recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			session = Session.get(idUtilisateur.toString()).get();
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
		return new JacksonRepresentation<>(recherche.tousDunUtilisateur(UUID.fromString(session.getNom())));
	}
	
	@Post
	public void cree(Form formulaire) {
		if (isCommitted()) {
			return;
		}
		CreationConstatMessage commande = new CreationConstatMessage(formulaire.getFirstValue("nom"), formulaire.getFirstValue("date"),
				formulaire.getFirstValue("geolocalisation"), UUID.fromString(session.getNom()), UUID.fromString(formulaire.getFirstValue("client")));
		ListenableFuture<UUID> idConstat = busCommande.envoie(commande);
		redirectSeeOther("../constat.html?constat=" + Futures.getUnchecked(idConstat));
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private Session session;
	
}
