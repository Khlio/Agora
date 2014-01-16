package fr.epsi.agora.web.ressource.constat;

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
import fr.epsi.agora.commande.constat.ModificationConstatMessage;
import fr.epsi.agora.commande.constat.SuppressionConstatMessage;
import fr.epsi.agora.requete.constat.DetailsConstat;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

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
			setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		try {
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.json(constat);
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
		try {
			String adresse2 = (null == formulaire.getFirstValue("adresse2") ? "" : formulaire.getFirstValue("adresse2"));
			ModificationConstatMessage commande = new ModificationConstatMessage(UUID.fromString(constat.getId()), formulaire.getFirstValue("nom"),
					formulaire.getFirstValue("adresse1"), adresse2, formulaire.getFirstValue("codePostal"));
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
			SuppressionConstatMessage commande = new SuppressionConstatMessage(UUID.fromString(constat.getId()));
			busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private DetailsConstat constat;
	
}
