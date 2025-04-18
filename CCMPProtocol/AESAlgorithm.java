package Laboratoriska1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithm {
    public static Cipher getCipherInstance(byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key,"AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher;
    }
}
