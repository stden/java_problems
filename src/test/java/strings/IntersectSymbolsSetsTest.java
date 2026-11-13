package strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

// Дано две строки.
// Напишите алгоритм который выдаст символы присутствующие в обеих строках
public class IntersectSymbolsSetsTest extends Assert {
    public static void main(String[] args) throws Exception {
        new IntersectSymbolsSetsTest().bigString();
    }

    @Test
    public void intersectSets() throws Exception {
        Set<Character> set1 = charSet('A', 'B', 'D', 'Z');
        Set<Character> set2 = charSet('A', 'B', 'C', 'Z');
        // Делаем копию
        Set<Character> result = new HashSet<>(set1);
        result.retainAll(set2);
        assertEquals(charSet('A', 'B', 'Z'), result);
    }

    private HashSet<Character> charSet(Character... chars) {
        return Stream.of(chars).collect(toCollection(HashSet::new));
    }

    @Test
    public void testStringToCharSet() throws Exception {
        assertEquals(charSet('a', 'b'), strToCharSet("aabbab"));
        assertEquals(charSet('t', 'e', 's', 't', ' ', 'i', 's', 'h'),
                strToCharSet("this is test"));
    }

    private Set<Character> strToCharSet(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set;
    }

    @Test
    public void name() throws Exception {
        Character[] array = intersect("abcde", "abxdf");
        System.out.println(Arrays.toString(array));
        intersect2("abcde", "abxdf");
    }

    @Test
    public void bigString() throws Exception {
        Random gen = new Random();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            s1.append((char) gen.nextInt(1000));
            s2.append((char) gen.nextInt(1000));
        }
        intersect(s1.toString(), s2.toString());
    }

    private Character[] intersect(String s1, String s2) {
        // Получаем множество символов первой строки
        Set<Character> result = strToCharSet(s1);
        // Пересекаем с множеством символов другой строки
        result.retainAll(strToCharSet(s2));
        // Преобразуем в массив
        Character[] array = result.toArray(new Character[result.size()]);
        // Сортируем массив символов для удобства восприятия
        Arrays.sort(array);
        // Возвращаем массив
        return array;
    }

    private void intersect2(String s1, String s2) {
        // Какие символы есть в первой строке?
        boolean[] exists1 = symbols(s1);
        // Какие символы есть во второй строке?
        boolean[] exists2 = symbols(s2);
        // Получили 2 массива по 26 элементов
        assertEquals(exists1.length, exists2.length);
        // Пересекаем множества
        for (int i = 0; i < exists1.length; i++)
            if (exists1[i] && exists2[i])
                System.out.print((char) (i + 'a') + " ");
        System.out.println();
    }

    // Какие символы есть в строке s?
    private boolean[] symbols(String s) {
        // Есть ли символ с номером i в строке s?
        boolean[] exists = new boolean[26]; // Сначала все false
        // Пробегаем по всей строке от начала до конца
        for (int i = 0; i < s.length(); i++) {
            // Берём очередной символ
            char c = s.charAt(i);
            // Если символ находится в нужном диапазоне
            if (c >= 'a' && c <= 'z')
                exists[c - 'a'] = true;
        }
        return exists;
    }
}
