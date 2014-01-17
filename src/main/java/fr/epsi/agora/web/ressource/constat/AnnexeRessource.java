package fr.epsi.agora.web.ressource.constat;

import java.util.UUID;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.constat.SuppressionMediaAnnexeMessage;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class AnnexeRessource extends ServerResource {

	@Inject
	public AnnexeRessource(BusCommande busCommande) {
		this.busCommande = busCommande;
	}
	
	@Override
	protected void doInit() {
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			idConstat = UUID.fromString(getRequestAttributes().get("idConstat").toString());
		} else {
			setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			setCommitted(true);
		}
	}
	
	@Put
	public Representation supprimeFichier(StringRepresentation nomFichier) {
		if (isCommitted()) {
			return ReponseRessource.NON_CONNECTE;
		}
		try {
			SuppressionMediaAnnexeMessage commande = new SuppressionMediaAnnexeMessage(idConstat, nomFichier.getText());
			busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private BusCommande busCommande;
	private UUID idConstat;
	
}
