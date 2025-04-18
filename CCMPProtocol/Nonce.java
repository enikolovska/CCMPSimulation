package Laboratoriska1;

public class Nonce {
    private final byte[] nonce;

    public Nonce(byte[] pn, byte[] sourceMac, byte qos) {
        nonce = new byte[16];
        System.arraycopy(pn,0,nonce,0,pn.length);
        System.arraycopy(sourceMac,0,nonce,pn.length,sourceMac.length);
        nonce[15] = qos;
    }
    public byte[] getNonce() {
        return nonce;
    }
}
