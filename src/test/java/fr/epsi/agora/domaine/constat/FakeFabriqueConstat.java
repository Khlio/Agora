package fr.epsi.agora.domaine.constat;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.societe.FakeFabriqueClient;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;

public class FakeFabriqueConstat {

	private FakeFabriqueConstat() {
	}
	
	public static Constat nouveau() {
		return FabriqueConstat.nouveau("Tout cassé", "1 rue du Bordel", "bis", "87000", DateTime.now(), "", FakeFabriqueUtilisateur.nouveau().getId(),
				FakeFabriqueClient.nouveau().getId(), Lists.newArrayList("\\\\medias\\audio.mp3"), Lists.newArrayList("\\\\annexes\\photo.jpg"));
	}
	
}
