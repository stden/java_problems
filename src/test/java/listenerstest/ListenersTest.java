package listenerstest;

import listeners.ConcreteObservableArrayMap;
import listeners.Listener;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ListenersTest extends Assert {
    @Test
    public void testObservableArrayMap() {
        ConcreteObservableArrayMap m = new ConcreteObservableArrayMap();
        Listener listener = (key, value) -> {
            System.out.print("[Changed] key:");
            for (byte b : key)
                System.out.print(" " + b);
            System.out.print(" value:");
            for (byte b : value)
                System.out.print(" " + b);
            System.out.println();
        };
        m.addListener(listener);

        ArrayList<Byte> key1 = new ArrayList<Byte>();
        key1.add((byte) 1);
        key1.add((byte) 2);

        ArrayList<Byte> key2 = new ArrayList<Byte>();
        key2.add((byte) 1);
        key2.add((byte) 2);

        m.put(key1, new byte[]{10});
        assertEquals(1, m.size());

        m.removeListener(listener);
        m.put(key2, new byte[]{20});
        assertEquals(1, m.size());

        // Добавляем 2 раза обработчик
        m.addListener(listener);
        m.addListener(listener);

        m.put(key1, new byte[]{30});
        assertEquals(1, m.size());
    }
}
