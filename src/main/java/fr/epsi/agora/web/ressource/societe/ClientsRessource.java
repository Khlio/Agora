package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.AjoutClientMessage;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheClients;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.web.Session;

public class ClientsRessource extends ServerResource {

	@Inject
	public ClientsRessource(BusCommande busCommande, RechercheClients recherche, RechercheSocietes rechercheSocietes) {
		this.busCommande = busCommande;
		this.recherche = recherche;
		this.rechercheSocietes = rechercheSocietes;
	}
	
	@Override
	protected void doInit() {
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			societe = rechercheSocietes.societeDeLUtilisateur(idUtilisateur);
		} else {
			setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		if (isCommitted()) {
			return new JacksonRepresentation<>(null);
		}
		return new JacksonRepresentation<>(recherche.tousDuneSociete(societe));
	}
	
	@Put
	public void ajouteClient(Form formulaire) {
		if (isCommitted()) {
			return;
		}
		AjoutClientMessage commande = new AjoutClientMessage(UUID.fromString(societe.getId()), formulaire.getFirstValue("nom"),
				formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), formulaire.getFirstValue("dateDeNaissance"),
				formulaire.getFirstValue("lieuDeNaissance"), formulaire.getFirstValue("metier"), formulaire.getFirstValue("nationalite"),
				formulaire.getFirstValue("adresse1"), formulaire.getFirstValue("adresse2"), formulaire.getFirstValue("codePostal"),
				formulaire.getFirstValue("telephonePortable"), formulaire.getFirstValue("telephoneFixe"));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private BusCommande busCommande;
	private RechercheClients recherche;
	private RechercheSocietes rechercheSocietes;
	private DetailsSociete societe;

}
