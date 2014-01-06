package fr.epsi.agora.persistance.fake;

import com.google.common.base.Optional;

import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class FakeEntrepotUtilisateurs extends FakeEntrepotAggregats<Utilisateur> implements EntrepotUtilisateurs {

	@Override
	public Optional<Utilisateur> get(Object id) {
		for (Utilisateur utilisateur : aggregats) {
			if (utilisateur.getId().equals(id)) {
				return Optional.of(utilisateur);
			}
		}
 		return Optional.absent();
	}

}
