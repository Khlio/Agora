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
import fr.epsi.agora.commande.societe.ModificationUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;

public class UtilisateurRessource extends ServerResource {

	@Inject
	public UtilisateurRessource(BusCommande busCommande, RechercheUtilisateurs recherche, RechercheSocietes rechercheSocietes) {
		this.busCommande = busCommande;
		this.recherche = recherche;
		this.rechercheSocietes = rechercheSocietes;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(id.toString()).isPresent()) {
			utilisateur = recherche.detailsDe(id);
		} else {
			setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
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
				formulaire.getFirstValue("adresse"), formulaire.getFirstValue("codePostal"), formulaire.getFirstValue("telephone"));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	@Delete
	public void supprime() {
		String idSociete = rechercheSocietes.societeDeLUtilisateur(UUID.fromString(utilisateur.getId())).getId();
		SuppressionUtilisateurMessage commande = new SuppressionUtilisateurMessage(UUID.fromString(utilisateur.getId()), UUID.fromString(idSociete));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private RechercheSocietes rechercheSocietes;
	private DetailsUtilisateur utilisateur;
	
}
