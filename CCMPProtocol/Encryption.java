package Laboratoriska1;

import javax.crypto.Cipher;
public class Encryption {
    private final Cipher cipher;

    public Encryption(byte[] key) throws Exception {
        this.cipher = AESAlgorithm.getCipherInstance(key);
    }

    public byte[] encryptData(byte[] nonce,byte[] data,byte[] mic) throws Exception {
        int numOfBlocks = (data.length + 15)/16;
        byte[] ciphertext = new byte[numOfBlocks*16];

        for (int i=0; i<numOfBlocks; i++) {
            byte[] counterPreload = CounterPreload.createCounterPreload(nonce,i+1);
            byte[] encryptedCounter = cipher.doFinal(counterPreload);
            byte[] block = new byte[16];
            int rBytes = Math.min(16,data.length-(i*16));
            System.arraycopy(data,i*16,block,0,rBytes);

            for(int j=0; j<16; j++) {
                block[j] ^= encryptedCounter[j];
            }
            System.arraycopy(block,0,ciphertext,i*16,16);
        }
        byte[] output = new byte[ciphertext.length + mic.length];
        System.arraycopy(ciphertext,0,output,0,ciphertext.length);
        System.arraycopy(mic,0,output,ciphertext.length,mic.length);
        return output;
    }
}

