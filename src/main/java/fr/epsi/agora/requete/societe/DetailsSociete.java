package fr.epsi.agora.requete.societe;

import java.util.List;

import org.jongo.marshall.jackson.oid.Id;

import com.google.common.collect.Lists;

public class DetailsSociete {

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public List<String> getUtilisateurs() {
		return utilisateurs;
	}
	
	public void setUtilisateurs(List<String> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	public List<String> getClients() {
		return clients;
	}
	
	public void setClients(List<String> clients) {
		this.clients = clients;
	}
	
	@Id
	private String id;
	private String siret;
	private String nom;
	private List<String> utilisateurs = Lists.newArrayList();
	private List<String> clients = Lists.newArrayList();
	
}
