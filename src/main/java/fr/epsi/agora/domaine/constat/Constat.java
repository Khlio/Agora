package fr.epsi.agora.domaine.constat;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.Aggregat;

public class Constat implements Aggregat {

	protected Constat() {
	}
	
	public Constat(UUID id) {
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
	
	public String getAdresse1() {
		return adresse1;
	}
	
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	
	public String getAdresse2() {
		return adresse2;
	}
	
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getGeolocalisation() {
		return geolocalisation;
	}

	public void setGeolocalisation(String geolocalisation) {
		this.geolocalisation = geolocalisation;
	}

	public UUID getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UUID utilisateur) {
		this.utilisateur = utilisateur;
	}

	public UUID getClient() {
		return client;
	}

	public void setClient(UUID client) {
		this.client = client;
	}
	
	public List<String> getAudios() {
		return audios;
	}
	
	public void setAudios(List<String> audios) {
		this.audios = audios;
	}
	
	public List<String> getAnnexes() {
		return annexes;
	}
	
	public void setAnnexes(List<String> annexes) {
		this.annexes = annexes;
	}
	
	public void ajouteAudio(String audio) {
		audios.add(audio);
	}
	
	public void supprimeAudio(String audio) {
		audios.remove(audio);
	}
	
	public void ajouteAnnexe(String annexe) {
		annexes.add(annexe);
	}
	
	public void supprimeAnnexe(String annexe) {
		annexes.remove(annexe);
	}

	private UUID id;
	private String nom;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private DateTime date;
	private String geolocalisation;
	private UUID utilisateur;
	private UUID client;
	private List<String> audios = Lists.newArrayList();
	private List<String> annexes = Lists.newArrayList();

}
