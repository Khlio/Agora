package fr.epsi.agora.persistance.mongo;

import static org.fest.assertions.Assertions.assertThat;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.FakeFabriqueConstat;

public class EntrepotConstatsMongoTest extends EntrepotAggregatsMongoTest<Constat> {

	@Override
	public void setUp() {
		entrepot = new EntrepotConstatsMongo(fournisseur);
	}

	@Override
	public void peutAjouter() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		entrepot.ajoute(constat);
		mongolinkRule.cleanSession();
		
		Constat constatTrouve = entrepot.get(constat.getId()).orNull();
		assertThat(constatTrouve).isNotNull();
		assertThat(constat.getNom()).isEqualTo("Tout cassé");
		assertThat(constat.getDate()).isEqualTo("01/01/2014");
		assertThat(constat.getGeolocalisation()).isEqualTo("");
		assertThat(constat.getUtilisateur()).isNotNull();
		assertThat(constat.getClient()).isNotNull();
	}

	@Override
	public void peutModifier() {
		Constat constat = FakeFabriqueConstat.nouveau();
		entrepot.ajoute(constat);
		constat.setNom("Casser tout");
		
		entrepot.modifie(constat);
		mongolinkRule.cleanSession();
		
		Constat constatTrouve = entrepot.get(constat.getId()).orNull();
		assertThat(constatTrouve).isNotNull();
		assertThat(constatTrouve.getNom()).isEqualTo("Casser tout");
	}

	@Override
	public void peutSupprimer() {
		Constat constat = FakeFabriqueConstat.nouveau();
		entrepot.ajoute(constat);
		
		entrepot.supprime(constat);
		mongolinkRule.cleanSession();
		
		assertThat(entrepot.get(constat.getId()).isPresent()).isFalse();
	}

}
