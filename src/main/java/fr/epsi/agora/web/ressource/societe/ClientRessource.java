package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.ModificationClientMessage;
import fr.epsi.agora.commande.societe.SuppressionClientMessage;
import fr.epsi.agora.domaine.validateur.EmailValidateur;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.domaine.validateur.TelephoneValidateur;
import fr.epsi.agora.requete.societe.DetailsClient;
import fr.epsi.agora.requete.societe.RechercheClients;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class ClientRessource extends ServerResource {

	@Inject
	public ClientRessource(BusCommande busCommande, RechercheClients recherche, RechercheSocietes rechercheSocietes) {
		this.busCommande = busCommande;
		this.recherche = recherche;
		this.rechercheSocietes = rechercheSocietes;
	}
	
	@Override
	protected void doInit() {
		UUID idClient = UUID.fromString(getRequestAttributes().get("idClient").toString());
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			client = recherche.detailsDe(idClient);
		} else {
			setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		try {
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.json(client);
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Put
	public Representation modifie(Form formulaire) {
		if (isCommitted()) {
			return ReponseRessource.NON_CONNECTE;
		}
		Erreur erreurEmail = EmailValidateur.valide(formulaire.getFirstValue("email"));
		if (erreurEmail.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurEmail.premiereErreur());
		}
		Erreur erreurTelephonePortable = TelephoneValidateur.valide(formulaire.getFirstValue("telephonePortable"));
		if (erreurTelephonePortable.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurTelephonePortable.premiereErreur());
		}
		try {
			ModificationClientMessage commande = new ModificationClientMessage(UUID.fromString(client.getId()), formulaire.getFirstValue("nom"),
					formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), formulaire.getFirstValue("dateDeNaissance"),
					formulaire.getFirstValue("lieuDeNaissance"), formulaire.getFirstValue("metier"), formulaire.getFirstValue("nationalite"),
					formulaire.getFirstValue("adresse1"), formulaire.getFirstValue("adresse2"), formulaire.getFirstValue("codePostal"),
					formulaire.getFirstValue("telephonePortable"), formulaire.getFirstValue("telephoneFixe"));
			busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Delete
	public Representation supprime() {
		if (isCommitted()) {
			return ReponseRessource.NON_CONNECTE;
		}
		try {
			String idSociete = rechercheSocietes.societeDuClient(UUID.fromString(client.getId())).getId();
			SuppressionClientMessage commande = new SuppressionClientMessage(UUID.fromString(client.getId()), UUID.fromString(idSociete));
			busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private BusCommande busCommande;
	private RechercheClients recherche;
	private RechercheSocietes rechercheSocietes;
	private DetailsClient client;
	
}
