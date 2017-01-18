package listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class ObservableArrayMap {
    Collection<Listener> listeners;
    Map<byte[], byte[]> valueMap = new HashMap<byte[], byte[]>();

    protected ObservableArrayMap() {
        listeners = createListenersContainer();
    }

    protected Collection<Listener> createListenersContainer() {
        return new ArrayList<Listener>();
    }

    public synchronized void addListener(Listener x) {
        listeners.add(x);
    }

    public synchronized void removeListener(Listener x) {
        listeners.remove(x);
    }

    public synchronized void put(byte[] key, byte[] value) {
        byte[] oldValue = valueMap.put(key, value);
        if (hasChanges(oldValue, value)) {
            for (Listener x : listeners) {
                x.onChange(key, value);
            }
        }
    }

    protected abstract boolean hasChanges(byte[] oldValue, byte[] newValue);
}