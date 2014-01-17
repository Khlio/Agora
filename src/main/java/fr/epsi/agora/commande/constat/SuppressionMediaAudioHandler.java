package fr.epsi.agora.commande.constat;

import java.io.File;

import fr.epsi.agora.Constante;
import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;

public class SuppressionMediaAudioHandler implements HandlerCommande<SuppressionMediaAudioMessage> {

	@Override
	public Object execute(SuppressionMediaAudioMessage commande) {
		Constat constat = Entrepots.constats().get(commande.idConstat).get();
		constat.supprimeAudio(commande.audio);
		File fichier = new File(Constante.CHEMIN_MEDIAS + constat.getNom() + File.separator + commande.audio);
		fichier.delete();
		return null;
	}

	@Override
	public Class<SuppressionMediaAudioMessage> typeCommande() {
		return SuppressionMediaAudioMessage.class;
	}

}
