package fr.epsi.agora.domaine;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class MD5 {

	private MD5() {
	}
	
	public static String crypte(final String chaine) {
		checkNotNull(chaine);
		
		Hasher hasher = Hashing.md5().newHasher();
		hasher.putString(chaine, Charsets.UTF_8);
		byte[] md5Octets = hasher.hash().asBytes();
		
		StringBuilder md5 = new StringBuilder();
		for (int i = 0; i < md5Octets.length; i++) {
			String hex = Integer.toHexString(md5Octets[i]);
			if (1 == hex.length()) {
				md5.append('0');
				md5.append(hex.charAt(hex.length() - 1));
			} else {
				md5.append(hex.substring(hex.length() - 2));
			}
		}
		return md5.toString();
	}
	
}
