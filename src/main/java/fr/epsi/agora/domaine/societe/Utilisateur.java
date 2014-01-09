package fr.epsi.agora.domaine.societe;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.Aggregat;

public class Utilisateur implements Aggregat {

	protected Utilisateur() {
	}
	
	public Utilisateur(UUID id) {
		this.id = id;
	}
	
	@Override
	public UUID getId() {
		return id;
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

	public List<Client> getClients() {
		return clients;
	}
	
	public Client ajouteClient(Client client) {
		clients.add(client);
		return client;
	}
	
	public void supprimeClient(Client client) {
		clients.remove(client);
	}

	private UUID id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private String adresse;
	private String telephone;
	private Date derniereConnexion;
	private Boolean connecte;
	private List<Client> clients = Lists.newArrayList();

}
