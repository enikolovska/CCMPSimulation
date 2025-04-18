package Laboratoriska1;

public class ClearTextFrame {
    private final byte[] frameHeader;
    private final byte[] packetNumber;
    private final byte[] data;

    public ClearTextFrame(byte[] frameHeader,byte[] packetNumber,byte[] data) {
        this.frameHeader = frameHeader;
        this.packetNumber = packetNumber;
        this.data = data;
    }

    public byte[] getFrameHeader() {
        return frameHeader;
    }

    public byte[] getPacketNumber() {
        return packetNumber;
    }

    public byte[] getData() {
        return data;
    }
}
