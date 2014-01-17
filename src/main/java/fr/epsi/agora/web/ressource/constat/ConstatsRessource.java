package fr.epsi.agora.web.ressource.constat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import fr.epsi.agora.Constante;
import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.constat.CreationConstatMessage;
import fr.epsi.agora.domaine.validateur.Erreur;
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
			List<String> audios = Lists.newArrayList();
			List<String> annexes = Lists.newArrayList();
			for (FileItem fichier : fichiers) {
				if (fichier.isFormField()) {
					String champ = fichier.getFieldName();
					BufferedReader buffer = new BufferedReader(new InputStreamReader(fichier.getInputStream()));
					String ligne = buffer.readLine();
					if (champ.equalsIgnoreCase("nom")) {
						nom = ligne.replace(' ', '_');
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
						if (fichier.getFieldName().equals("uploadClientDesc")) {
							String nomFichier = fichier.getName().replace(' ', '_');
							if (estUnFichierAudio(nomFichier)) {
								audios.add(nomFichier);
								enregistreFichier(nom, nomFichier, fichier.getInputStream());
							} else {
								setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
								return ReponseRessource.get(Erreur.FORMAT_NON_SUPPORTE);
							}
						} else if (fichier.getFieldName().equals("uploadAnomalie")) {
							String nomFichier = fichier.getName().replace(' ', '_');
							if (estUnFichierAudioOuImage(nomFichier)) {
								annexes.add(nomFichier);
								enregistreFichier(nom, nomFichier, fichier.getInputStream());
							} else {
								setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
								return ReponseRessource.get(Erreur.FORMAT_NON_SUPPORTE);
							}
						}
					}
				}
			}
			CreationConstatMessage commande = new CreationConstatMessage(nom, adresse1, adresse2, codePostal, DateTime.now(), geolocalisation,
					UUID.fromString(session.getNom()), UUID.fromString(idClient), audios, annexes);
			ListenableFuture<UUID> idConstat = busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			redirectSeeOther("../../../informationConstat.html?id=" + Futures.getUnchecked(idConstat).toString());
			return ReponseRessource.get(Futures.getUnchecked(idConstat).toString());
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private boolean estUnFichierAudio(String fichier) {
		return fichier.contains("mp3") || fichier.contains("ogg") || fichier.contains("3ga") || fichier.contains("aac") || fichier.contains("wave") || fichier.contains("wav");
	}
	
	private boolean estUnFichierAudioOuImage(String fichier) {
		return estUnFichierAudio(fichier) || fichier.contains("jpg") || fichier.contains("jpeg") || fichier.contains("bmp") || fichier.contains("png") || fichier.contains("gif");
	}
	
	private void enregistreFichier(String nomDossier, String nomFichier, InputStream in) {
		File dossierACreer = new File(Constante.CHEMIN_MEDIAS + nomDossier);
		dossierACreer.mkdir();
		File fichierACreer = new File(Constante.CHEMIN_MEDIAS + nomDossier + File.separator + nomFichier);
		
		FileOutputStream fos = null;
		try {
			fichierACreer.createNewFile();
			fos = new FileOutputStream(fichierACreer);
			byte[] buffer = new byte[8192];
			int lu = 0;
			while ((lu = in.read(buffer)) >= 0) {
				fos.write(buffer, 0, lu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private Session session;
	
}
