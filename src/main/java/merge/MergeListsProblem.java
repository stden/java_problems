package merge;

import java.util.*;

public class MergeListsProblem {
    private static Comparator<? super Item> sortById = Comparator.comparingInt(item -> item.ID);
    private static Comparator<? super Item> sortByWeight = Comparator.comparingDouble(item -> item.weight);

    static List<Item> merge(List<Item> list1, List<Item> list2) {
        // Сортируем первый список по id
        list1.sort(sortById);
        // Сортируем второй список по id
        list2.sort(sortById);
        // Merge lists
        List<Item> result = new ArrayList<>();
        Iterator<Item> i1 = list1.iterator();
        Iterator<Item> i2 = list2.iterator();
        while (i1.hasNext() || i2.hasNext()) {
            if (!i1.hasNext()) {
                while (i2.hasNext())
                    result.add(i2.next());
            } else if (!i2.hasNext()) {
                while (i1.hasNext())
                    result.add(i1.next());
            } else {
                Item k1 = i1.next();
                Item k2 = i2.next();
                while (true) {
                    if (k1.ID < k2.ID) {
                        result.add(k1);
                        if (!i1.hasNext())
                            break;
                        k1 = i1.next();
                    } else if (k1.ID > k2.ID) {
                        result.add(k2);
                        if (!i2.hasNext())
                            break;
                        k2 = i2.next();
                    } else if (k1.ID == k2.ID) {
                        result.add(new Item(k1.ID, k1.weight + k2.weight));
                        if (!i1.hasNext() || !i2.hasNext())
                            break;
                        k1 = i1.next();
                        k2 = i2.next();
                    }
                }
            }
        }
        // Sort
        result.sort(sortByWeight);
        return result;
    }

    static List<Item> toList(Item... array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
