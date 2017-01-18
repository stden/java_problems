package listeners;

import java.util.List;

public interface Listener {
    void onChange(List<Byte> key, byte[] value);
}
