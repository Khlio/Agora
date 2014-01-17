package fr.epsi.agora.commande.constat;

import java.io.File;

import fr.epsi.agora.Constante;
import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;

public class SuppressionMediaAnnexeHandler implements HandlerCommande<SuppressionMediaAnnexeMessage> {

	@Override
	public Object execute(SuppressionMediaAnnexeMessage commande) {
		Constat constat = Entrepots.constats().get(commande.idConstat).get();
		constat.supprimeAnnexe(commande.annexe);
		File fichier = new File(Constante.CHEMIN_MEDIAS + constat.getNom() + File.separator + commande.annexe);
		fichier.delete();
		return null;
	}

	@Override
	public Class<SuppressionMediaAnnexeMessage> typeCommande() {
		return SuppressionMediaAnnexeMessage.class;
	}

}
