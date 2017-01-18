package listeners;

public class ConcreteObservableArrayMap extends ObservableArrayMap {
    protected boolean hasChanges(byte[] oldValue, byte[] newValue) {
        if (oldValue == null && newValue == null)
            return false;
        if (oldValue == null)
            return true;
        if (oldValue.length != newValue.length)
            return true;
        for (int i = 0; i < oldValue.length; i++) {
            if (oldValue[i] != newValue[i]) {
                System.out.println("index: " + i + " old: " + oldValue[i] + " != new: " + newValue[i]);
                return true;
            }
        }
        return false;
    }

    public synchronized int size() {
        return valueMap.size();
    }
}
