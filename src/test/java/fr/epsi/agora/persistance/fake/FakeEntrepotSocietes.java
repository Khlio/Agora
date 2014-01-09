package fr.epsi.agora.persistance.fake;

import com.google.common.base.Optional;

import fr.epsi.agora.domaine.societe.EntrepotSocietes;
import fr.epsi.agora.domaine.societe.Societe;

public class FakeEntrepotSocietes extends FakeEntrepotAggregats<Societe> implements EntrepotSocietes {

	@Override
	public Optional<Societe> get(Object id) {
		for (Societe societe : aggregats) {
			if (societe.getId().equals(id)) {
				return Optional.of(societe);
			}
		}
		return Optional.absent();
	}

}
