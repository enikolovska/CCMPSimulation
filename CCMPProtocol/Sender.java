package Laboratoriska1;

public class Sender {
    private final byte[] key;

    public Sender(byte[] key) {
        this.key = key;
    }

    public EncryptedFrame sendFrame(ClearTextFrame clearTextFrame, Nonce nonce) throws Exception {
        byte[] frameHeader = clearTextFrame.getFrameHeader();
        byte[] data = clearTextFrame.getData();

        MICCalculator micCalculator = new MICCalculator(key, nonce.getNonce());
        micCalculator.processData(frameHeader);
        micCalculator.processData(data);
        byte[] mic = micCalculator.getFinalMIC();

        Encryption encryption = new Encryption(key);
        byte[] encryptedData = encryption.encryptData(nonce.getNonce(),data,mic);

        StringBuilder hexString = new StringBuilder();
        for (byte b : encryptedData) {
            hexString.append(String.format("%02X", b));
        }
        System.out.println("Енкриптираниот текст во хексадецимален формат: " + hexString);

        return new EncryptedFrame(encryptedData, mic);
    }
}
