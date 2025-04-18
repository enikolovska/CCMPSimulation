package Laboratoriska1;

public class DecryptionOrIntegrityException extends Exception {
    String message;
    public DecryptionOrIntegrityException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
