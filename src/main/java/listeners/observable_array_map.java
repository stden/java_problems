package listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class observable_array_map {
    Collection<_listener> listeners;
    Map<byte[], byte[]> valueMap = new HashMap<byte[], byte[]>();

    protected observable_array_map() {
        listeners = createListenersContainer();
    }

    protected Collection<_listener> createListenersContainer() {
        return new ArrayList<_listener>();
    }

    public synchronized void addListener(_listener x) {
        listeners.add(x);
    }

    public synchronized void removeListener(_listener x) {
        listeners.remove(x);
    }

    public synchronized void put(byte[] key, byte[] value) {
        byte[] oldValue = valueMap.put(key, value);
        if (!hasChanges(oldValue, value)) {
            for (_listener x : listeners) {
                x.onChange(key, value);
            }
        }
    }

    protected abstract boolean hasChanges(byte[] oldValue, byte[] newValue);
}