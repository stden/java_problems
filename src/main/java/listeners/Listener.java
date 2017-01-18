package listeners;

public interface Listener {
    void onChange(byte[] key, byte[] value);
}
