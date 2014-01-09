package fr.epsi.agora.web.ressource.societe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.AjoutClientMessage;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheSocietes;

public class ClientsRessource extends ServerResource {

	@Inject
	public ClientsRessource(BusCommande busCommande, RechercheSocietes recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() throws ResourceException {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		societe = recherche.detailsDe(id);
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(societe.getClients());
	}
	
	@Put
	public void ajouteClient(Form formulaire) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date dateDeNaissance = null;
		try {
			dateDeNaissance = sdf.parse(formulaire.getFirstValue("dateDeNaissance"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AjoutClientMessage commande = new AjoutClientMessage(UUID.fromString(societe.getId()), formulaire.getFirstValue("nom"),
				formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), dateDeNaissance,
				formulaire.getFirstValue("lieuDeNaissance"), formulaire.getFirstValue("metier"), formulaire.getFirstValue("nationalite"),
				formulaire.getFirstValue("adresse"), formulaire.getFirstValue("telephone"));
		busCommande.envoie(commande);
	}
	
	private BusCommande busCommande;
	private RechercheSocietes recherche;
	private DetailsSociete societe;

}
