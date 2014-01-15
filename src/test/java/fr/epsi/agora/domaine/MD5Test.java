package fr.epsi.agora.domaine;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class MD5Test {

	@Test
	public void peutCrypterEnMD5() {
		String test = MD5.crypte("bouh");
		
		assertThat(test).isEqualTo("c9c5384adec41a13eea91ed4d20d809e");
	}
	
}
