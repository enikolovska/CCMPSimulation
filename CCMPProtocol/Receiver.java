package Laboratoriska1;

public class Receiver {
    private final byte[] key;

    public Receiver(byte[] key) {
        this.key = key;
    }

    public String receiveFrame(ClearTextFrame clearTextFrame, EncryptedFrame encryptedFrame, Nonce nonce) throws Exception {
        byte[] frameHeader = clearTextFrame.getFrameHeader();
        byte[] encryptedData = encryptedFrame.getEncryptedData();
        byte[] receivedMic = encryptedFrame.getMic();
        int originalDataLength = clearTextFrame.getData().length;

        Decryption decryption = new Decryption(key);
        byte[] decryptedData = decryption.decryptData(nonce.getNonce(),encryptedData,receivedMic,originalDataLength);
        boolean isMicValid = decryption.verifyMIC(decryptedData,frameHeader,nonce.getNonce(),receivedMic);

        if(isMicValid) {
            System.out.println("Успешна верификација на MIC");
            return new String(decryptedData);
        } else{
            throw new DecryptionOrIntegrityException("Неуспешна верификација на MIC");
        }
    }
}
