package Laboratoriska1;

import javax.crypto.Cipher;

public class MICCalculator {
    private final Cipher cipher;
    private byte[] previousBlock;

    public MICCalculator(byte[] key,byte[] nonce) throws Exception {
        this.cipher = AESAlgorithm.getCipherInstance(key);
        this.previousBlock = cipher.doFinal(nonce);
    }

    public void processData(byte[] data) throws Exception {
        for(int i=0; i<data.length; i+=16) {
            byte[] block = new byte[16];
            int remainingBytes = Math.min(16,data.length-i);
            System.arraycopy(data,i,block,0,remainingBytes);

            if(remainingBytes<16) {
                for(int j=remainingBytes; j<16; j++) {
                    block[j]=0;
                }
            }
            for (int j=0; j<16; j++) {
                block[j] ^= previousBlock[j];
            }
            previousBlock = cipher.doFinal(block);
        }
    }

    public byte[] getFinalMIC() {
        byte[] finalMIC = new byte[8];
        System.arraycopy(previousBlock,0,finalMIC,0,8);
        return finalMIC;
    }
}
