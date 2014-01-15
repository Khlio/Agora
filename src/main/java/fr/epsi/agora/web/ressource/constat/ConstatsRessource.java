package fr.epsi.agora.web.ressource.constat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.joda.time.DateTime;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.constat.CreationConstatMessage;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

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
			return ReponseRessource.NON_CONNECTE;
		}
		try {
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.json(recherche.tousDunUtilisateur(UUID.fromString(session.getNom())));
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Post
	public Representation cree(Representation representation) {
		if (isCommitted()) {
			return ReponseRessource.NON_CONNECTE;
		}
		String nom = null;
		String adresse1 = null;
		String adresse2 = null;
		String codePostal = null;
		String geolocalisation = "";
		String idClient = null;
		try {
			RestletFileUpload upload = new RestletFileUpload(new DiskFileItemFactory());
			List<FileItem> fichiers = upload.parseRepresentation(representation);
			List<String> medias = Lists.newArrayList();
			for (FileItem fichier : fichiers) {
				if (fichier.isFormField()) {
					String champ = fichier.getFieldName();
					BufferedReader buffer = new BufferedReader(new InputStreamReader(fichier.getInputStream()));
					String ligne = buffer.readLine();
					if (champ.equalsIgnoreCase("nom")) {
						nom = ligne;
					} else if (champ.equalsIgnoreCase("adresse1")) {
						adresse1 = ligne;
					} else if (champ.equalsIgnoreCase("adresse2")) {
						adresse2 = ligne;
					} else if (champ.equalsIgnoreCase("geolocalisation")) {
						geolocalisation = ligne;
					} else if (champ.equalsIgnoreCase("clientChoice")) {
						idClient = ligne;
					} else if (champ.equalsIgnoreCase("codePostal")) {
						codePostal = ligne;
					}
				} else {
					if (0 < fichier.getInputStream().available()) {
						medias.add(fichier.getName()); // TODO Enregistrer le fichier sur le cloud
					}
				}
			}
			CreationConstatMessage commande = new CreationConstatMessage(nom, adresse1, adresse2, codePostal, DateTime.now(), geolocalisation,
					UUID.fromString(session.getNom()), UUID.fromString(idClient), medias);
			ListenableFuture<UUID> idConstat = busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.get(Futures.getUnchecked(idConstat).toString());
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private Session session;
	
}
