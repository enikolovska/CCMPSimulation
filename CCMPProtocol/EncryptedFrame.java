package Laboratoriska1;

public class EncryptedFrame {
    private final byte[] encryptedData;
    private final byte[] mic;

    public EncryptedFrame(byte[] encryptedData, byte[] mic) {
        this.encryptedData = encryptedData;
        this.mic = mic;
    }

    public byte[] getEncryptedData() {
        return encryptedData;
    }

    public byte[] getMic() {
        return mic;
    }
}
