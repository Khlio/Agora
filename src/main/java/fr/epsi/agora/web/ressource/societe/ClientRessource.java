package fr.epsi.agora.web.ressource.societe;

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
import fr.epsi.agora.commande.societe.ModificationClientMessage;
import fr.epsi.agora.commande.societe.SuppressionClientMessage;
import fr.epsi.agora.requete.societe.DetailsClient;
import fr.epsi.agora.requete.societe.RechercheClients;
import fr.epsi.agora.web.Session;

public class ClientRessource extends ServerResource {

	@Inject
	public ClientRessource(BusCommande busCommande, RechercheClients recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID idClient = UUID.fromString(getRequestAttributes().get("idClient").toString());
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			client = recherche.detailsDe(idClient);
		} else {
			setStatus(Status.CLIENT_ERROR_FORBIDDEN);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(client);
	}
	
	@Put
	public void modifie(Form formulaire) {
		if (isCommitted()) {
			return;
		}
		ModificationClientMessage commande = new ModificationClientMessage(UUID.fromString(client.getId()), formulaire.getFirstValue("nom"),
				formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), formulaire.getFirstValue("dateDeNaissance"),
				formulaire.getFirstValue("lieuDeNaissance"), formulaire.getFirstValue("metier"), formulaire.getFirstValue("nationalite"),
				formulaire.getFirstValue("adresse"), formulaire.getFirstValue("telephone"));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	@Delete
	public void supprime() {
		if (isCommitted()) {
			return;
		}
		SuppressionClientMessage commande = new SuppressionClientMessage(UUID.fromString(client.getId()));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private BusCommande busCommande;
	private RechercheClients recherche;
	private DetailsClient client;
	
}
