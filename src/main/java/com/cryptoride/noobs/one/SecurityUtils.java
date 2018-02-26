package com.cryptoride.noobs.one;

import java.security.MessageDigest;

/**
 * 
 * @author Asad Ali
 *         
 *         *******************************************************************
 *         Since we need a way to generate a digital signature, there are many
 *         cryptographic algorithms you can choose from, however SHA256 fits
 *         just fine for this example. We can import
 *         java.security.MessageDigest; to get access to the SHA256 algorithm.
 * 
 *         We need to use SHA256 later down the line so lets create a handy
 *         helper method in a new SecurityUtils ‘utility’ class :
 *
 */

public class SecurityUtils {
	/**
	 * 
	 * @param input
	 * @return
	 * 
	 *         it takes a string as input and applies SHA256 algorithm to it,
	 *         and returns the generated signature as a string.
	 *         **************************************************************
	 */
	public static String encript(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// Applies sha256 to our input,
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
