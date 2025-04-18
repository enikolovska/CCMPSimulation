package Laboratoriska1;
import javax.crypto.Cipher;
import java.util.Arrays;

public class Decryption {
    private final Cipher cipher;
    private final byte[] key;

    public Decryption(byte[] key) throws Exception {
        this.cipher = AESAlgorithm.getCipherInstance(key);
        this.key = key;
    }

    public byte[] decryptData(byte[] nonce,byte[] ciphertext,byte[] receivedMic,int originalLength) throws Exception {
        int numOfBlocks = (ciphertext.length-8)/16;
        byte[] data = new byte[numOfBlocks*16];

        for(int i=0; i<numOfBlocks; i++) {
            byte[] counterPreload =  CounterPreload.createCounterPreload(nonce,i+1);
            byte[] encryptedCounter = cipher.doFinal(counterPreload);
            byte[] ciphertextBlock = new byte[16];
            System.arraycopy(ciphertext,i*16,ciphertextBlock,0,16);

            for(int j=0; j<16; j++) {
                ciphertextBlock[j] ^= encryptedCounter[j];
            }
            System.arraycopy(ciphertextBlock,0,data,i*16,16);}

        byte[] finalData = new byte[originalLength];
        System.arraycopy(data,0,finalData,0,originalLength);
        return finalData;
    }

    public boolean verifyMIC(byte[] data,byte[] frameHeader,byte[] nonce,byte[] receivedMic) throws Exception {
        MICCalculator micCalculator = new MICCalculator(key, nonce);
        micCalculator.processData(frameHeader);
        micCalculator.processData(data);
        byte[] calculatedMic = micCalculator.getFinalMIC();
        return Arrays.equals(calculatedMic, receivedMic);
    }

}
