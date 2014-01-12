package fr.epsi.agora.web.ressource.constat;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;

public class ConstatsRessource extends ServerResource {

	@Inject
	public ConstatsRessource(BusCommande busCommande, RechercheConstats recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID idUtilisateur = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(idUtilisateur.toString()).isPresent()) {
			session = Session.get(idUtilisateur.toString()).get();
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
		return new JacksonRepresentation<>(recherche.tousDunUtilisateur(UUID.fromString(session.getNom())));
	}
	
	@Post
	public void cree(Representation representation) throws FileUploadException, IOException {
		if (isCommitted()) {
			return;
		}
		RestletFileUpload upload = new RestletFileUpload(new DiskFileItemFactory());
		List<FileItem> fichiers = upload.parseRepresentation(representation);
		for (FileItem fichier : fichiers) {
			if (fichier.isFormField()) {
				fichier.getFieldName(); //TODO alimenter la commande avec les champs du formulaire
			} else {
				fichier.getInputStream(); //TODO mettre le fichier en base
			}
		}
		/*CreationConstatMessage commande = new CreationConstatMessage(formulaire.getFirstValue("nom"), formulaire.getFirstValue("adresse"), DateTime.now(),
				formulaire.getFirstValue("geolocalisation"), UUID.fromString(session.getNom()), UUID.fromString(formulaire.getFirstValue("client")));*/
		//ListenableFuture<UUID> idConstat = busCommande.envoie(commande);
		//redirectSeeOther("../constat.html?constat=" + Futures.getUnchecked(idConstat));
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private Session session;
	
}
