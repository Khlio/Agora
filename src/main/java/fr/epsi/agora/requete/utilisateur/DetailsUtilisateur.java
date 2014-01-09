package fr.epsi.agora.requete.utilisateur;

import java.util.Date;
import java.util.List;

import org.jongo.marshall.jackson.oid.Id;

import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.societe.Societe;
import fr.epsi.agora.requete.client.DetailsClient;

public class DetailsUtilisateur {
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDerniereConnexion() {
		return derniereConnexion;
	}

	public void setDerniereConnexion(Date derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}

	public Boolean isConnecte() {
		return connecte;
	}

	public void setConnecte(Boolean connecte) {
		this.connecte = connecte;
	}
	
	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public List<DetailsClient> getClients() {
		return clients;
	}

	public void setClients(List<DetailsClient> clients) {
		this.clients = clients;
	}

	@Id
	private String id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private String adresse;
	private String telephone;
	private Date derniereConnexion;
	private Boolean connecte;
	private Societe societe;
	private List<DetailsClient> clients = Lists.newArrayList();
	
}
