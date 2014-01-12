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
import fr.epsi.agora.commande.societe.AjoutUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionSocieteMessage;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheSocietes;

public class SocieteRessource extends ServerResource {

	@Inject
	public SocieteRessource(BusCommande busCommande, RechercheSocietes recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		societe = recherche.detailsDe(id);
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(societe);
	}
	
	@Put
	public void ajouteUtilisateur(Form formulaire) {
		AjoutUtilisateurMessage commande = new AjoutUtilisateurMessage(UUID.fromString(societe.getId()), formulaire.getFirstValue("nom"),
				formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), formulaire.getFirstValue("motDePasse"),
				formulaire.getFirstValue("adresse"), formulaire.getFirstValue("telephone"));
		busCommande.envoie(commande);
		redirectSeeOther("../../societe/societe.html?id=" + UUID.fromString(societe.getId()));
	}
	
	@Delete
	public void supprime() {
		SuppressionSocieteMessage commande = new SuppressionSocieteMessage(UUID.fromString(societe.getId()));
		busCommande.envoie(commande);
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private BusCommande busCommande;
	private RechercheSocietes recherche;
	private DetailsSociete societe;
	
}
