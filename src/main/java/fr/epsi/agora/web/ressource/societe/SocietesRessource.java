package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.CreationSocieteMessage;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.domaine.validateur.SiretValidateur;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class SocietesRessource extends ServerResource {

	@Inject
	public SocietesRessource(BusCommande busCommande, RechercheSocietes recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Get("json")
	public Representation represente() {
		try {
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.json(recherche.toutes());
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Post
	public Representation cree(Form formulaire) {
		Erreur erreurSiret = SiretValidateur.valide(formulaire.getFirstValue("siret"));
		if (erreurSiret.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurSiret.premiereErreur());
		}
		try {
			CreationSocieteMessage commande = new CreationSocieteMessage(formulaire.getFirstValue("siret"), formulaire.getFirstValue("nom"));
			ListenableFuture<UUID> idSociete = busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.get(Futures.getUnchecked(idSociete).toString());
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private BusCommande busCommande;
	private RechercheSocietes recherche;
	
}
