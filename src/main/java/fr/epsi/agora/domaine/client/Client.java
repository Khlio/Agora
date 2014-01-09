package fr.epsi.agora.domaine.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.Aggregat;
import fr.epsi.agora.domaine.societe.Societe;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class Client implements Aggregat {

	protected Client() {
	}
	
	public Client(UUID id) {
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

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}

	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}

	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
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
	
	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	
	public Utilisateur ajouteUtilisateur(Utilisateur utilisateur) {
		utilisateurs.add(utilisateur);
		return utilisateur;
	}
	
	public void supprimeUtilisateur(Utilisateur utilisateur) {
		utilisateurs.remove(utilisateur);
	}

	private UUID id;
	private String nom;
	private String prenom;
	private String email;
	private Date dateDeNaissance;
	private String lieuDeNaissance;
	private String metier;
	private String nationalite;
	private String adresse;
	private String telephone;
	private Societe societe;
	private List<Utilisateur> utilisateurs = Lists.newArrayList();

}
