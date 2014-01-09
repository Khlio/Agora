package fr.epsi.agora.persistance.fake;

import com.google.common.base.Optional;

import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.EntrepotClients;

public class FakeEntrepotClients extends FakeEntrepotAggregats<Client> implements EntrepotClients {

	@Override
	public Optional<Client> get(Object id) {
		for (Client client : aggregats) {
			if (client.getId().equals(id)) {
				return Optional.of(client);
			}
		}
		return Optional.absent();
	}

}
