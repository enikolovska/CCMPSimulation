package Laboratoriska1;

public class Test {
    public static void main(String[] args) {
        try {
            byte[] key = new byte[16];
            byte[] packetNumber = new byte[6];
            byte[] sourceMac = new byte[6];
            byte qos = 0x01;

            Nonce nonce = new Nonce(packetNumber,sourceMac,qos);
            byte[] frameHeader = "Frame Header".getBytes();
            byte[] data = "Здраво,Како си? ".getBytes();
            ClearTextFrame clearTextFrame = new ClearTextFrame(frameHeader,packetNumber,data);

            Sender sender = new Sender(key);
            Receiver receiver = new Receiver(key);

            EncryptedFrame encryptedFrame = sender.sendFrame(clearTextFrame,nonce);
            String decryptedText = receiver.receiveFrame(clearTextFrame,encryptedFrame,nonce);
            System.out.println("Декриптираниот текст е: " + decryptedText);

            boolean isDataEqual = decryptedText.equals(new String(data));
            System.out.println("Декриптираниот data е точен: " + isDataEqual);
            if(isDataEqual) {
                System.out.println("CCMP е успешно имплементиран и тестиран.");
            } else{
                throw new DecryptionOrIntegrityException("Нарушен интегритет или грешка во декриптирање");
            }
        } catch (DecryptionOrIntegrityException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
