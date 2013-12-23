package fr.epsi.agora.web.representation;

import java.util.Map;

import org.restlet.data.MediaType;

import com.google.common.collect.Maps;

public class ModeleEtVue {

	public static ModeleEtVue cree() {
		return cree(MediaType.APPLICATION_JSON);
	}
	
	public static ModeleEtVue cree(MediaType mediaType) {
		ModeleEtVue resultat = new ModeleEtVue();
		resultat.texte = "";
		resultat.type = mediaType;
		return resultat;
	}
	
	private ModeleEtVue() {
	}
	
	public String getTexte() {
		return texte;
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	public MediaType getType() {
		return type;
	}
	
	public ModeleEtVue avec(String clef, Object valeur) {
		data.put(clef, valeur);
		return this;
	}
	
	private String texte;
	private MediaType type;
	private Map<String, Object> data = Maps.newConcurrentMap();
	
}
