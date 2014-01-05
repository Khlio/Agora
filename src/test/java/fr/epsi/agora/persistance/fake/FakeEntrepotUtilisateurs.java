package fr.epsi.agora.persistance.fake;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class FakeEntrepotUtilisateurs implements EntrepotUtilisateurs {

	@Override
	public Optional<Utilisateur> get(Object id) {
		for (Utilisateur utilisateur : utilisateurs) {
			if (utilisateur.getId().equals(id)) {
				return Optional.of(utilisateur);
			}
		}
 		return Optional.absent();
	}

	@Override
	public Utilisateur ajoute(Utilisateur aggregat) {
		utilisateurs.add(aggregat);
		return aggregat;
	}
	
	@Override
	public void modifie(Utilisateur aggregat) {
		supprime(get(aggregat.getId()).get());
		utilisateurs.add(aggregat);
	}

	@Override
	public void supprime(Utilisateur aggregat) {
		utilisateurs.remove(aggregat);
	}
	
	private List<Utilisateur> utilisateurs = Lists.newArrayList();

}
