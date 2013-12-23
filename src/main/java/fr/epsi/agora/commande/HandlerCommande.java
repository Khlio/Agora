package fr.epsi.agora.commande;

public interface HandlerCommande<T extends Message> {

	Object execute(T commande);
	
	Class<T> typeCommande();
	
}
