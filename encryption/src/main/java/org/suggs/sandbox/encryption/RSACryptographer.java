package org.suggs.sandbox.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

public class RSACryptographer {

    private static final String ALGORYTHM = "RSA";
    private static final int KEY_SIZE = 2048;
    private static final String TRANSFORMATION = "RSA";

    public static final KeyPair createKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORYTHM);
        generator.initialize(KEY_SIZE, new SecureRandom());
        return generator.generateKeyPair();
    }

    public String encrypt(String toEncrypt, PublicKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher encryptCipher = Cipher.getInstance(TRANSFORMATION);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(encryptCipher.doFinal(toEncrypt.getBytes()));
    }

    public String decrypt(String toDecrypt, PrivateKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher decryptCipher = Cipher.getInstance(TRANSFORMATION);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        return new String(decryptCipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
    }



    /*provider = bouncy castle provider
    TRAN=SFORMATION = "RSA/None/QAEPWithSHA"
    ALGO = RSACryptographerCRYPR_PREFIX = "CRYPT:"
    */

}
