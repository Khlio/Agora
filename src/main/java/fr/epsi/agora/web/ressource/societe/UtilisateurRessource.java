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

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.ModificationUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;

public class UtilisateurRessource extends ServerResource {

	public UtilisateurRessource(BusCommande busCommande, RechercheUtilisateurs recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(id.toString()).isPresent()) {
			utilisateur = recherche.detailsDe(id);
		} else {
			setStatus(Status.CLIENT_ERROR_FORBIDDEN);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(utilisateur);
	}
	
	@Put
	public void modifie(Form formulaire) {
		if (isCommitted()) {
			return;
		}
		ModificationUtilisateurMessage commande = new ModificationUtilisateurMessage(UUID.fromString(utilisateur.getId()), formulaire.getFirstValue("nom"),
				formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), formulaire.getFirstValue("motDePasse"),
				formulaire.getFirstValue("adresse"), formulaire.getFirstValue("telephone"));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	@Delete
	public void supprime() {
		if (isCommitted()) {
			return;
		}
		SuppressionUtilisateurMessage commande = new SuppressionUtilisateurMessage(UUID.fromString(utilisateur.getId()));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private DetailsUtilisateur utilisateur;
	
}
