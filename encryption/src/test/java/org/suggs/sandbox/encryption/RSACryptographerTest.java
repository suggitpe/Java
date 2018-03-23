package org.suggs.sandbox.encryption;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

public class RSACryptographerTest {

    private static final Logger LOG = LoggerFactory.getLogger(RSACryptographerTest.class);
    private RSACryptographer cryptographer = new RSACryptographer();
    private static KeyPair KEY_PAIR;
    private static final String TEXT_TO_ENCRYPT = "Foo Bar Baz";

    @BeforeClass
    public static void buildKeyPair() throws NoSuchAlgorithmException {
        KEY_PAIR = RSACryptographer.createKeyPair();
    }

    @Test
    public void encryptsStringUsingPublicKey() throws Exception {
        String encrypted = cryptographer.encrypt(TEXT_TO_ENCRYPT, KEY_PAIR.getPublic());
        assertThat(encrypted).endsWith("==");
    }


}