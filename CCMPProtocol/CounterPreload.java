package Laboratoriska1;

public class CounterPreload {

    public static byte[] createCounterPreload(byte[] nonce, int counter) {
        byte[] counterPreload = new byte[16];
        System.arraycopy(nonce,0,counterPreload,0,nonce.length);
        counterPreload[12] = (byte)(counter>>24);
        counterPreload[13] = (byte)(counter>>16);
        counterPreload[14] = (byte)(counter>>8);
        counterPreload[15] = (byte)counter;
        return counterPreload;
    }
}
