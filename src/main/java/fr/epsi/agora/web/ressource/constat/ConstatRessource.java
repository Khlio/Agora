package fr.epsi.agora.web.ressource.constat;

import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.constat.ModificationConstatMessage;
import fr.epsi.agora.commande.constat.SuppressionConstatMessage;
import fr.epsi.agora.requete.constat.DetailsConstat;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;

public class ConstatRessource extends ServerResource {

	@Inject
	public ConstatRessource(BusCommande busCommande, RechercheConstats recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID idConstat = UUID.fromString(getRequestAttributes().get("idConstat").toString());
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			constat = recherche.detailsDe(idConstat);
		} else {
			setStatus(Status.CLIENT_ERROR_FORBIDDEN);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(constat);
	}
	
	@Put
	public void modifie(Form formulaire) {
		if (isCommitted()) {
			return;
		}
		ModificationConstatMessage commande = new ModificationConstatMessage(UUID.fromString(constat.getId()), formulaire.getFirstValue("nom"),
				formulaire.getFirstValue("date"), formulaire.getFirstValue("geolocalisation"));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	@Delete
	public void supprime() {
		if (isCommitted()) {
			return;
		}
		SuppressionConstatMessage commande = new SuppressionConstatMessage(UUID.fromString(constat.getId()));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private DetailsConstat constat;
	
}
