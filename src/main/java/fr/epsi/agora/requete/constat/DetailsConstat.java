package fr.epsi.agora.requete.constat;

import java.util.List;

import org.joda.time.DateTime;
import org.jongo.marshall.jackson.oid.Id;

import com.google.common.collect.Lists;

public class DetailsConstat {
	
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
	
	public String getDate() {
		try {
			DateTime dateTime = new DateTime(Long.valueOf(date));
			String jour = ""+(10 > dateTime.getDayOfMonth() ? "0" + dateTime.getDayOfMonth() : dateTime.getDayOfMonth());
			String mois = ""+(10 > dateTime.getMonthOfYear() ? "0" + dateTime.getMonthOfYear() : dateTime.getMonthOfYear());
			return jour + "-" + mois + "-" + dateTime.getYear();
		} catch (Exception e) {
			return "";
		}
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getGeolocalisation() {
		return geolocalisation;
	}
	
	public void setGeolocalisation(String geolocalisation) {
		this.geolocalisation = geolocalisation;
	}
	
	public String getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getClient() {
		return client;
	}
	
	public void setClient(String client) {
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
	
	@Id
	private String id;
	private String nom;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String date;
	private String geolocalisation;
	private String utilisateur;
	private String client;
	private List<String> audios = Lists.newArrayList();
	private List<String> annexes = Lists.newArrayList();
	
}
