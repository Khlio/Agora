package fr.epsi.agora.domaine.societe;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.Aggregat;
import fr.epsi.agora.domaine.client.Client;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class Societe implements Aggregat {

	protected Societe() {
	}
	
	public Societe(UUID id) {
		this.id = id;
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public List<Client> getClients() {
		return clients;
	}
	
	public Utilisateur ajouteUtilisateur(Utilisateur utilisateur) {
		utilisateur.setSociete(this);
		utilisateurs.add(utilisateur);
		return utilisateur;
	}
	
	public void supprimeUtilisateur(Utilisateur utilisateur) {
		utilisateur.setSociete(null);
		utilisateurs.remove(utilisateur);
	}
	
	public Client ajouteClient(Client client) {
		client.setSociete(this);
		clients.add(client);
		return client;
	}
	
	public void supprimeClient(Client client) {
		client.setSociete(null);
		clients.remove(client);
	}

	private UUID id;
	private String siret;
	private String nom;
	private List<Utilisateur> utilisateurs = Lists.newArrayList();
	private List<Client> clients = Lists.newArrayList();

}
