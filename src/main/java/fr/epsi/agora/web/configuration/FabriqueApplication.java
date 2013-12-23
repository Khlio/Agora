package fr.epsi.agora.web.configuration;

import org.restlet.Context;

import fr.epsi.agora.web.AgoraApplication;

public class FabriqueApplication {

	public AgoraApplication configure() {
		return new AgoraApplication(new Context());
	}
	
}
