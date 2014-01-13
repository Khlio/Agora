package fr.epsi.agora.domaine.societe;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.Aggregat;

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

	public List<UUID> getUtilisateurs() {
		return utilisateurs;
	}

	public List<UUID> getClients() {
		return clients;
	}
	
	public void ajouteUtilisateur(UUID utilisateur) {
		utilisateurs.add(utilisateur);
	}
	
	public void supprimeUtilisateur(UUID utilisateur) {
		utilisateurs.remove(utilisateur);
	}
	
	public void ajouteClient(UUID client) {
		clients.add(client);
	}
	
	public void supprimeClient(UUID client) {
		clients.remove(client);
	}

	private UUID id;
	private String siret;
	private String nom;
	private List<UUID> utilisateurs = Lists.newArrayList();
	private List<UUID> clients = Lists.newArrayList();

}
